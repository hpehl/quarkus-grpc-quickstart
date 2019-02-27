package io.grpc.examples.routeguide;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import io.grpc.ManagedChannel;
import io.grpc.Server;
import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import io.grpc.stub.StreamObserver;
import io.quarkus.grpc.test.CleanupTimeout;
import io.quarkus.grpc.test.GrpcTestExtension;
import io.quarkus.grpc.test.ManagedChannelCleanupRegistry;
import io.quarkus.grpc.test.ServerCleanupRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

@ExtendWith(GrpcTestExtension.class)
@CleanupTimeout(value = 20L, unit = SECONDS)
class RouteGuideServiceTest {

    private Features features;
    private RouteGuideGrpc.RouteGuideBlockingStub blockingService;
    private RouteGuideGrpc.RouteGuideStub asyncService;

    @BeforeEach
    void setUp(TestInfo info, ServerCleanupRegistry serverRegistry, ManagedChannelCleanupRegistry channelRegistry)
            throws IOException {
        features = new Features();
        features.clear(); // we need an empty features for the tests

        RouteGuideService service = new RouteGuideService(features);
        Server server = serverRegistry.register(InProcessServerBuilder.forName(info.getDisplayName())
                .addService(service)
                .build());
        server.start();
        ManagedChannel channel = channelRegistry.register(InProcessChannelBuilder.forName(info.getDisplayName())
                .usePlaintext()
                .build());
        blockingService = RouteGuideGrpc.newBlockingStub(channel);
        asyncService = RouteGuideGrpc.newStub(channel);
    }

    @Test
    void getFeature() {
        Point point = Point.newBuilder().setLongitude(1).setLatitude(1).build();
        Feature unnamedFeature = Feature.newBuilder().setName("").setLocation(point).build();
        Feature feature = blockingService.getFeature(point);
        assertEquals(unnamedFeature, feature);

        // feature found in the server
        Feature namedFeature = Feature.newBuilder().setName("name").setLocation(point).build();
        features.add(namedFeature);
        feature = blockingService.getFeature(point);
        assertEquals(namedFeature, feature);
    }

    @Test
    void listFeatures() throws Exception {
        // setup
        Rectangle rect = Rectangle.newBuilder()
                .setLo(Point.newBuilder().setLongitude(0).setLatitude(0).build())
                .setHi(Point.newBuilder().setLongitude(10).setLatitude(10).build())
                .build();
        Feature f1 = Feature.newBuilder()
                .setLocation(Point.newBuilder().setLongitude(-1).setLatitude(-1).build())
                .setName("f1")
                .build(); // not inside rect
        Feature f2 = Feature.newBuilder()
                .setLocation(Point.newBuilder().setLongitude(2).setLatitude(2).build())
                .setName("f2")
                .build();
        Feature f3 = Feature.newBuilder()
                .setLocation(Point.newBuilder().setLongitude(3).setLatitude(3).build())
                .setName("f3")
                .build();
        Feature f4 = Feature.newBuilder()
                .setLocation(Point.newBuilder().setLongitude(4).setLatitude(4).build())
                .build(); // unamed
        features.add(f1);
        features.add(f2);
        features.add(f3);
        features.add(f4);

        Collection<Feature> result = new HashSet<Feature>();
        CountDownLatch latch = new CountDownLatch(1);
        StreamObserver<Feature> responseObserver =
                new StreamObserver<Feature>() {
                    @Override
                    public void onNext(Feature value) {
                        result.add(value);
                    }

                    @Override
                    public void onError(Throwable t) {
                        fail();
                    }

                    @Override
                    public void onCompleted() {
                        latch.countDown();
                    }
                };
        asyncService.listFeatures(rect, responseObserver);
        assertTrue(latch.await(1, TimeUnit.SECONDS));
        assertEquals(new HashSet<>(Arrays.asList(f2, f3)), result);
    }

    @Test
    void recordRoute() {
        Point p1 = Point.newBuilder().setLongitude(1000).setLatitude(1000).build();
        Point p2 = Point.newBuilder().setLongitude(2000).setLatitude(2000).build();
        Point p3 = Point.newBuilder().setLongitude(3000).setLatitude(3000).build();
        Point p4 = Point.newBuilder().setLongitude(4000).setLatitude(4000).build();
        Feature f1 = Feature.newBuilder().setLocation(p1).build(); // unamed
        Feature f2 = Feature.newBuilder().setLocation(p2).setName("f2").build();
        Feature f3 = Feature.newBuilder().setLocation(p3).setName("f3").build();
        Feature f4 = Feature.newBuilder().setLocation(p4).build(); // unamed
        features.add(f1);
        features.add(f2);
        features.add(f3);
        features.add(f4);

        @SuppressWarnings("unchecked")
        StreamObserver<RouteSummary> responseObserver = (StreamObserver<RouteSummary>) mock(StreamObserver.class);
        ArgumentCaptor<RouteSummary> routeSummaryCaptor = ArgumentCaptor.forClass(RouteSummary.class);

        StreamObserver<Point> requestObserver = asyncService.recordRoute(responseObserver);
        requestObserver.onNext(p1);
        requestObserver.onNext(p2);
        requestObserver.onNext(p3);
        requestObserver.onNext(p4);
        verify(responseObserver, never()).onNext(any(RouteSummary.class));
        requestObserver.onCompleted();

        // allow some ms to let client receive the response. Similar usage later on.
        verify(responseObserver, timeout(100)).onNext(routeSummaryCaptor.capture());
        RouteSummary summary = routeSummaryCaptor.getValue();
        assertEquals(45, summary.getDistance()); // 45 is the hard coded distance from p1 to p4.
        assertEquals(2, summary.getFeatureCount());
        verify(responseObserver, timeout(100)).onCompleted();
        verify(responseObserver, never()).onError(any(Throwable.class));
    }

    @Test
    void routeChat() {
        Point p1 = Point.newBuilder().setLongitude(1).setLatitude(1).build();
        Point p2 = Point.newBuilder().setLongitude(2).setLatitude(2).build();
        RouteNote n1 = RouteNote.newBuilder().setLocation(p1).setMessage("m1").build();
        RouteNote n2 = RouteNote.newBuilder().setLocation(p2).setMessage("m2").build();
        RouteNote n3 = RouteNote.newBuilder().setLocation(p1).setMessage("m3").build();
        RouteNote n4 = RouteNote.newBuilder().setLocation(p2).setMessage("m4").build();
        RouteNote n5 = RouteNote.newBuilder().setLocation(p1).setMessage("m5").build();
        RouteNote n6 = RouteNote.newBuilder().setLocation(p1).setMessage("m6").build();
        int timesOnNext = 0;

        @SuppressWarnings("unchecked")
        StreamObserver<RouteNote> responseObserver = (StreamObserver<RouteNote>) mock(StreamObserver.class);

        StreamObserver<RouteNote> requestObserver = asyncService.routeChat(responseObserver);
        verify(responseObserver, never()).onNext(any(RouteNote.class));

        requestObserver.onNext(n1);
        verify(responseObserver, never()).onNext(any(RouteNote.class));

        requestObserver.onNext(n2);
        verify(responseObserver, never()).onNext(any(RouteNote.class));

        requestObserver.onNext(n3);
        ArgumentCaptor<RouteNote> routeNoteCaptor = ArgumentCaptor.forClass(RouteNote.class);
        verify(responseObserver, timeout(100).times(++timesOnNext)).onNext(routeNoteCaptor.capture());
        RouteNote result = routeNoteCaptor.getValue();
        assertEquals(p1, result.getLocation());
        assertEquals("m1", result.getMessage());

        requestObserver.onNext(n4);
        routeNoteCaptor = ArgumentCaptor.forClass(RouteNote.class);
        verify(responseObserver, timeout(100).times(++timesOnNext)).onNext(routeNoteCaptor.capture());
        result = routeNoteCaptor.getAllValues().get(timesOnNext - 1);
        assertEquals(p2, result.getLocation());
        assertEquals("m2", result.getMessage());

        requestObserver.onNext(n5);
        routeNoteCaptor = ArgumentCaptor.forClass(RouteNote.class);
        timesOnNext += 2;
        verify(responseObserver, timeout(100).times(timesOnNext)).onNext(routeNoteCaptor.capture());
        result = routeNoteCaptor.getAllValues().get(timesOnNext - 2);
        assertEquals(p1, result.getLocation());
        assertEquals("m1", result.getMessage());
        result = routeNoteCaptor.getAllValues().get(timesOnNext - 1);
        assertEquals(p1, result.getLocation());
        assertEquals("m3", result.getMessage());

        requestObserver.onNext(n6);
        routeNoteCaptor = ArgumentCaptor.forClass(RouteNote.class);
        timesOnNext += 3;
        verify(responseObserver, timeout(100).times(timesOnNext)).onNext(routeNoteCaptor.capture());
        result = routeNoteCaptor.getAllValues().get(timesOnNext - 3);
        assertEquals(p1, result.getLocation());
        assertEquals("m1", result.getMessage());
        result = routeNoteCaptor.getAllValues().get(timesOnNext - 2);
        assertEquals(p1, result.getLocation());
        assertEquals("m3", result.getMessage());
        result = routeNoteCaptor.getAllValues().get(timesOnNext - 1);
        assertEquals(p1, result.getLocation());
        assertEquals("m5", result.getMessage());

        requestObserver.onCompleted();
        verify(responseObserver, timeout(100)).onCompleted();
        verify(responseObserver, never()).onError(any(Throwable.class));
    }
}
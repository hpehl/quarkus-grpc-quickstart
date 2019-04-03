package io.grpc.examples.routeguide;

import java.io.IOException;
import java.io.StringWriter;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.protobuf.util.JsonFormat;
import io.grpc.ManagedChannel;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.quarkus.grpc.client.Channel;

@Path("/get-feature/{lat}/{lon}")
public class GetFeatureResource {

    private static final Logger logger = Logger.getLogger(GetFeatureResource.class.getName());

    private RouteGuideGrpc.RouteGuideBlockingStub blockingStub;

    @Inject
    @Channel("route")
    ManagedChannel channel;

    @PostConstruct
    void init() {
        blockingStub = RouteGuideGrpc.newBlockingStub(channel);
    }

    @GET
    public Response feature(@PathParam("lat") int lat, @PathParam("lon") int lon) {
        Point request = Point.newBuilder().setLatitude(lat).setLongitude(lon).build();
        Feature feature;
        try {
            feature = blockingStub.getFeature(request);
            if (feature != null && !feature.getName().isEmpty()) {
                StringWriter writer = new StringWriter();
                JsonFormat.printer().appendTo(feature, writer);
                return Response.ok(writer.toString(), MediaType.APPLICATION_JSON).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (StatusRuntimeException e) {
            Status status = e.getStatus();
            logger.severe("RPC failed: " + status);
            return Response.status(status.getCode().value(), status.getDescription()).build();
        } catch (IOException e) {
            logger.severe("JSON failed: " + e.getMessage());
            return Response.status(500, e.getMessage()).build();
        }
    }
}

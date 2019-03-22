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
import io.grpc.StatusRuntimeException;
import io.quarkus.grpc.client.runtime.Channel;

@Path("/route")
public class RouteGuideResource {

    private static final Logger logger = Logger.getLogger(RouteGuideResource.class.getName());

    @Inject @Channel("route") ManagedChannel channel;
    private RouteGuideGrpc.RouteGuideBlockingStub blockingStub;

    @PostConstruct
    void init() {
        blockingStub = RouteGuideGrpc.newBlockingStub(channel);
    }

    @GET
    @Path("/feature/{lat}/{lon}")
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
            logger.severe("RPC failed: " + e.getStatus());
            return Response.status(e.getStatus().getCode().value(), e.getStatus().getDescription()).build();
        } catch (IOException e) {
            logger.severe("JSON failed: " + e.getMessage());
            return Response.status(500, e.getMessage()).build();
        }
    }
}

package io.grpc.examples.routeguide;

import java.io.IOException;
import java.io.StringWriter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import io.grpc.ManagedChannel;
import io.grpc.stub.StreamObserver;
import io.quarkus.grpc.client.Channel;

@ApplicationScoped
@ServerEndpoint("/list-features")
public class ListFeaturesSocket {

    private RouteGuideGrpc.RouteGuideStub asyncStub;

    @Inject
    @Channel("route")
    ManagedChannel channel;

    @PostConstruct
    void init() {
        asyncStub = RouteGuideGrpc.newStub(channel);
    }

    @OnMessage
    public void onMessage(String payload, Session session) {
        Rectangle.Builder builder = Rectangle.newBuilder();
        try {
            JsonFormat.parser().merge(payload, builder);
            asyncStub.listFeatures(builder.build(), new StreamObserver<Feature>() {
                @Override
                public void onNext(Feature feature) {
                    Latency.suspend();
                    StringWriter writer = new StringWriter();
                    try {
                        JsonFormat.printer().appendTo(feature, writer);
                        session.getAsyncRemote().sendText(writer.toString());
                    } catch (IOException e) {
                        session.getAsyncRemote().sendText(String.format("{\"error\": \"%s\"}", e.getMessage()));
                    }
                }

                @Override
                public void onError(Throwable t) {
                    session.getAsyncRemote().sendText(String.format("{\"error\": \"%s\"}", t.getMessage()));
                }

                @Override
                public void onCompleted() {
                    session.getAsyncRemote().sendText("{\"status\": \"completed\"}");
                }
            });
        } catch (InvalidProtocolBufferException e) {
            session.getAsyncRemote().sendText(String.format("{\"error\": \"%s\"}", e.getMessage()));
        }
    }
}

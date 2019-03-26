package io.grpc.examples.routeguide;

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
import io.quarkus.grpc.client.runtime.Channel;

@ApplicationScoped
@ServerEndpoint("/route-chat")
public class RouteChatSocket {

    @Inject @Channel("route") ManagedChannel channel;
    private RouteGuideGrpc.RouteGuideStub asyncStub;

    @PostConstruct
    void init() {
        asyncStub = RouteGuideGrpc.newStub(channel);
    }

    @OnMessage
    public void onMessage(String payload, Session session) {
        RouteNote.Builder builder = RouteNote.newBuilder();
        try {
            JsonFormat.parser().merge(payload, builder);
            // asyncStub.routeChat()
        } catch (InvalidProtocolBufferException e) {
            session.getAsyncRemote().sendText(String.format("{\"error\": \"%s\"}", e.getMessage()));
        }
    }


    static class RequestObserver implements StreamObserver<RouteNote> {
        @Override
        public void onNext(RouteNote note) {
        }

        @Override
        public void onError(Throwable t) {
        }

        @Override
        public void onCompleted() {
        }
    }
}

package io.grpc.examples.routeguide;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
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
    private Map<String, StreamObserver<RouteNote>> requestObservers;

    @PostConstruct
    void init() {
        requestObservers = new HashMap<>();
        asyncStub = RouteGuideGrpc.newStub(channel);
    }

    @OnOpen
    public void onOpen(Session session) {
        requestObservers.put(session.getId(), asyncStub.routeChat(new ResponseObserver(session)));
    }

    @OnMessage
    public void onMessage(String payload, Session session) {
        StreamObserver<RouteNote> observer = requestObservers.get(session.getId());
        if (observer != null) {
            try {
                RouteNote.Builder builder = RouteNote.newBuilder();
                JsonFormat.parser().merge(payload, builder);
                RouteNote routeNote = builder.build();
                observer.onNext(routeNote);
            } catch (InvalidProtocolBufferException e) {
                session.getAsyncRemote().sendText(String.format("{\"error\": \"%s\"}", e.getMessage()));
            }
        }
    }

    @OnClose
    public void onClose(Session session) {
        StreamObserver<RouteNote> observer = requestObservers.remove(session.getId());
        if (observer != null) {
            observer.onCompleted();
        }
    }


    static class ResponseObserver implements StreamObserver<RouteNote> {

        private final Session session;

        ResponseObserver(Session session) {
            this.session = session;
        }

        @Override
        public void onNext(RouteNote note) {
            Latency.suspend();
            StringWriter writer = new StringWriter();
            try {
                JsonFormat.printer().appendTo(note, writer);
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
            session.getAsyncRemote().sendText("{\"status\": \"DONE\"}");
        }
    }
}

package org.acme.grpc;

import io.grpc.stub.StreamObserver;
import org.jboss.shamrock.grpc.GrpcService;

@GrpcService
public class GreeterImpl extends GreeterGrpc.GreeterImplBase {

    @Override
    public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
        System.out.println("Receiving message from " + req);
        HelloReply reply = HelloReply.newBuilder().setMessage("Hello " + req.getName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}

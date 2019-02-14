package org.acme.grpc;

import java.util.logging.Logger;

import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import org.jboss.shamrock.grpc.GrpcInterceptor;

@GrpcInterceptor
public class RouteGuideInterceptor implements ServerInterceptor {

    private static final Logger logger = Logger.getLogger(RouteGuideInterceptor.class.getName());

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers,
            ServerCallHandler<ReqT, RespT> next) {
        logger.info("Intercepting call " + call.getMethodDescriptor());
        return next.startCall(call, headers);
    }
}

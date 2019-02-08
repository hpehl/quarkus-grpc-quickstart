# Shamrock gRPC Quickstart

Demo how to use gRPC with Protean. Uses the [gRPC extension](https://github.com/hpehl/shamrock-grpc-extension) and a very simple gRPC service:

```proto
package helloworld;

service Greeter {
    rpc SayHello (HelloRequest) returns (HelloReply) {}
}

message HelloRequest {
    string name = 1;
}

message HelloReply {
    string message = 1;
}
``` 

## Get Started

### Server

```
mvn clean package
java -jar target/grpc-quickstart-1.0-SNAPSHOT-runner.jar  
```

### Client

```
mvn exec:java -Dexec.mainClass=org.acme.grpc.GreeterClient
```

Please note this is **wip**!

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

### Server (JVM)

```
mvn clean package
java -jar target/grpc-quickstart-1.0-SNAPSHOT-runner.jar  
```

### Server (Native)

```
mvn clean package -P native
./target/grpc-quickstart-1.0-SNAPSHOT-runner  
```

### Client

```
mvn exec:java -Dexec.mainClass=org.acme.grpc.GreeterClient -P client
```

Please note this is **wip**!

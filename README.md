# Shamrock gRPC Quickstart

Demo how to use [gRPC](https://grpc.io/) with Protean. Uses the [gRPC extension](https://github.com/hpehl/shamrock-grpc-extension) and the [route guide example](https://github.com/grpc/grpc-java/tree/v1.18.0/examples#grpc-examples).

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

### Clients

```
mvn exec:java -Dexec.mainClass=org.acme.grpc.RouteGuideClient -P client
```

Please note this is **wip**!

# Quarkus gRPC Quickstart

Demo how to use [gRPC](https://grpc.io/) with [Quarkus](https://quarkus.io). Uses the [gRPC extension](https://github.com/hpehl/quarkus-grpc-extension) and the [route guide example](https://github.com/grpc/grpc-java/tree/v1.18.0/examples#grpc-examples) provided by [gRPC Java](https://github.com/grpc/grpc-java).

## Get Started

### Server (JVM)

```
mvn clean package
java -jar target/grpc-quickstart-1.0-runner.jar  
```

### Server (Native)

```
mvn clean package -P native
./target/grpc-quickstart-1.0-runner  
```

### Client

```
mvn exec:java -P client
```

Have fun!
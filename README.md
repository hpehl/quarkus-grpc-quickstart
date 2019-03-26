# Quarkus gRPC Quickstart

Demo how to use [gRPC](https://grpc.io/) with [Quarkus](https://quarkus.io). Uses the [Quarkus gRPC](https://github.com/hpehl/quarkus-grpc-extension) and the [Quarkus gRPC client](https://github.com/hpehl/quarkus-grpc-extension) extension to implement the [route guide example](https://github.com/grpc/grpc-java/tree/v1.18.0/examples#grpc-examples) provided by [gRPC Java](https://github.com/grpc/grpc-java).

## Get Started

```
mvn clean install
```

### Server

```
cd server
java -jar target/grpc-quickstart-server-1.0-runner.jar  
```

### Client

```
cd client
java -jar target/grpc-quickstart-client-1.0-runner.jar  
```

Open http://localhost:8080

Have fun!
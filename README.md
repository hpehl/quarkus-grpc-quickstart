# Quarkus gRPC Quickstart

Demo how to use [gRPC](https://grpc.io/) and [Quarkus](https://quarkus.io). Uses both the [gRPC](https://github.com/hpehl/quarkus-grpc-extension) and the [gRPC client](https://github.com/hpehl/quarkus-grpc-client-extension) extension to implement the [route guide example](https://github.com/grpc/grpc-java/tree/v1.18.0/examples#grpc-examples) provided by [gRPC Java](https://github.com/grpc/grpc-java).

## Get Started

```bash
mvn clean install
java -jar server/target/grpc-quickstart-server-1.0-runner.jar
java -jar client/target/grpc-quickstart-client-1.0-runner.jar
```

Open http://localhost:8080

Have fun!
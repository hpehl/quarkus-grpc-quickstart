# Shamrock gRPC Quickstart

Demo how to use gRPC with Protean. Uses the [gRPC extension](https://github.com/hpehl/shamrock-grpc-extension). 

This repository contains:

1. A gRPC service deployed in Protean
1. A gRPC client to test the service

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

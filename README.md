# REST vs gRPC Benchmark Project

This project compares the performance and usage of REST and gRPC services in Java using Spring Boot.

## Structure

- `server/` - Contains REST and gRPC service implementations.
- `client/` - Contains benchmarking clients for both REST and gRPC.

## Prerequisites

- Java 17+
- Gradle
- Protocol Buffers compiler (`protoc`)

## Setup

1. **Generate gRPC code:**
   ```bash
   cd server
   ./gradlew build
   ```

2. **Run the server:**
   ```bash
   cd server
   ./gradlew bootRun
   ```

3. **Run the client:**
   ```bash
   cd client
   ./gradlew bootRun
   ```

## Configuration

- REST server runs on port `8080`.
- gRPC server runs on port `9090`.
- Message size limits can be configured in `server/src/main/resources/application.yml`.

## Benchmarking

The client will invoke both REST and gRPC endpoints and print timing results to the console.


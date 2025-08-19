package com.example.client.service;

import com.example.proto.LargePayloadRequest;
import com.example.proto.LargePayloadResponse;
import com.example.proto.TestServiceGrpc;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class BenchmarkGrpcService {

    @GrpcClient("testService")
    private TestServiceGrpc.TestServiceBlockingStub testServiceStub;

    public long benchmarkGrpc(int sizeKb, int iterations) {
        long start = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            try {
                LargePayloadRequest req = LargePayloadRequest.newBuilder().setSize(sizeKb).build();
                LargePayloadResponse resp = testServiceStub.getLargePayload(req);
                // Optionally measure payload size: resp.getSerializedSize()
            } catch (StatusRuntimeException e) {
                e.printStackTrace();
            }
        }
        long end = System.nanoTime();
        return (end - start) / 1_000_000; // ms
    }

//    public long benchmarkGrpc(int sizeKb, int iterations) {
//        long start = System.nanoTime();
//        for (int i = 0; i < iterations; i++) {
//            byte[] responseBytes = webClient.get()
//                    .uri(uriBuilder -> uriBuilder.queryParam("sizeKb", sizeKb).build())
//                    .accept(MediaType.parseMediaType("application/x-protobuf"))
//                    .retrieve()
//                    .bodyToMono(byte[].class)
//                    .block();
//
//            try {
//                // Deserialize the protobuf bytes into LargeDataObjectList
//                LargeDataObjectList response = LargeDataObjectList.parseFrom(responseBytes);
//                // Optionally measure payload size: responseBytes.length
//            } catch (Exception e) {
//                throw new RuntimeException("Failed to parse protobuf response", e);
//            }
//        }
//        long end = System.nanoTime();
//        return (end - start) / 1_000_000; // ms
//    }
}

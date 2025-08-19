package com.example.server.grpc;

import com.example.proto.*;
import com.example.server.service.LargePayloadService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class TestServiceGrpcImpl extends TestServiceGrpc.TestServiceImplBase {

    @Autowired
    private LargePayloadService largePayloadService;

    @Override
    public void getLargePayload(LargePayloadRequest request, StreamObserver<LargePayloadResponse> responseObserver) {
        int sizeKb = request.getSize();
        var dataList = largePayloadService.generateLargePayload(sizeKb);
        LargePayloadResponse.Builder respBuilder = LargePayloadResponse.newBuilder();
        for (com.example.server.model.LargeDataObject obj : dataList) {
            respBuilder.addData(toProto(obj));
        }
        responseObserver.onNext(respBuilder.build());
        responseObserver.onCompleted();
    }

    private LargeDataObject toProto(com.example.server.model.LargeDataObject obj) {
        LargeDataObject.Builder builder = LargeDataObject.newBuilder()
                .setId(obj.getId())
                .setName(obj.getName())
                .setDescription(obj.getDescription())
                .addAllTags(obj.getTags())
                .setNested(NestedObject.newBuilder()
                        .setLevel(obj.getNested().getLevel())
                        .setInfo(obj.getNested().getInfo())
                        .build());
        return builder.build();
    }
}

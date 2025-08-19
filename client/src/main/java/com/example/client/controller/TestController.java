package com.example.client.controller;

import com.example.client.service.BenchmarkGrpcService;
import com.example.client.service.BenchmarkRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private BenchmarkRestService benchmarkRestService;

    @Autowired
    private BenchmarkGrpcService benchmarkGrpcService;

    @GetMapping("/api/test-rest")
    public String testRest(@RequestParam(defaultValue = "1024") int sizeKb, @RequestParam(defaultValue = "1") int iterations) {
        long ms = benchmarkRestService.benchmarkRest(sizeKb, iterations);
        return "REST: " + ms + " ms for " + iterations + " requests";
    }

    @GetMapping("/api/test-grpc")
    public String testGrpc(@RequestParam(defaultValue = "1024") int sizeKb, @RequestParam(defaultValue = "1") int iterations) {
        long ms = benchmarkGrpcService.benchmarkGrpc(sizeKb, iterations);
        return "gRPC: " + ms + " ms for " + iterations + " requests";
    }

}

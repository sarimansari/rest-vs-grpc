package com.example.server.controller;

import com.example.server.model.LargeDataObject;
import com.example.server.service.LargePayloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestRestController {

    @Autowired
    private LargePayloadService largePayloadService;

    @GetMapping("/api/rest")
    public List<LargeDataObject> getLargePayload(@RequestParam(defaultValue = "1024") int sizeKb) {
        return largePayloadService.generateLargePayload(sizeKb);
    }

}

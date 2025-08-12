package com.example.server.service;

import com.example.server.model.LargeDataObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class LargePayloadService {
    public List<LargeDataObject> generateLargePayload(int sizeKb) {
        int approxObjSize = 2 * 1024; // ~2KB per object
        int count = Math.max(1, (sizeKb * 1024) / approxObjSize);
        List<LargeDataObject> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(randomObject(i));
        }
        return list;
    }

    private LargeDataObject randomObject(long id) {
        LargeDataObject obj = new LargeDataObject();
        obj.setId(id);
        obj.setName(randomString(128));
        obj.setDescription(randomString(1024));
        obj.setTags(List.of(randomString(32), randomString(32), randomString(32)));
        LargeDataObject.NestedObject nested = new LargeDataObject.NestedObject();
        nested.setLevel(1);
        nested.setInfo(randomString(256));
        obj.setNested(nested);
        return obj;
    }

    private String randomString(int len) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append((char) ('a' + r.nextInt(26)));
        }
        return sb.toString();
    }
}

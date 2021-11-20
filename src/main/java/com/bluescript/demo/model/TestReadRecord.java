package com.bluescript.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.stereotype.Component;

import lombok.Data;

@RedisHash("TestRecord")
@Data
public class TestReadRecord {
    @Id
    private int id;
    private String testRecord;

}

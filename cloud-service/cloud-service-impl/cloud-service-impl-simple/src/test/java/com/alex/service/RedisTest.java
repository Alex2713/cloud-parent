package com.alex.service;

import cn.hutool.core.util.IdUtil;
import com.alex.common.core.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisService redisService;

    @BeforeEach
    public void before() {
        log.info(">>>>> >>>>> >>>>> >>>>> >>>>>");
        log.info("simpleService : redisTest start");
        log.info("simpleService : redisTest.before; init content");

        log.info("simpleService : redisTest.before; setting test:str for String");
        redisService.set("test:str", IdUtil.fastUUID());
    }

    @Test
    public void expire() {
        String key = "test:expire";
        redisService.set(key, IdUtil.fastUUID());
        boolean flag = redisService.expire(key, 2 * 60 * 1000);
        log.info("simpleService : redisTest.expire; key is {}, flag is {}", key, flag);
        redisService.del(key);
    }

    @Test
    public void set() {
        String key = "test:set";
        boolean flag = redisService.set(key, IdUtil.fastUUID());
        log.info("simpleService : redisTest.set; key is {}, flag is {}", key, flag);
        redisService.del(key);
    }

    @Test
    public void get() {
        String key = "test:str";
        Object value = redisService.get(key);
        log.info("simpleService : redisTest.get; key is {}, value is {}", key, value);
    }

    @AfterEach
    public void after() {
        log.info("simpleService : redisTest.after; clear content");
        log.info("simpleService : redisTest.after; clear test:str");
        redisService.del("test:str");
        log.info("simpleService : redisTest finish");
        log.info("<<<<< <<<<< <<<<< <<<<< <<<<<");
    }
}

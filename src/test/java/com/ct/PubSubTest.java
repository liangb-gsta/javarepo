package com.ct;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PubSubTest {
	public static final String TOPIC = "TEST";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void test01() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
        	redisTemplate.convertAndSend(TOPIC, "yunai:" + i);
            Thread.sleep(1000L);
        }
    }
}

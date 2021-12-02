package com.ct.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;


//https://zhuanlan.zhihu.com/p/92689757

public class RedisMessageListener implements MessageListener {
	private static final Logger LOG = LoggerFactory.getLogger(RedisMessageListener.class);
	@Autowired
    private DynamicRuleFactory dynamicRuleFactory;
	
	@Override
	public void onMessage(Message message, byte[] pattern) {
		System.out.println("收到 PatternTopic 消息：");
        System.out.println("线程编号：" + Thread.currentThread().getName());
        System.out.println("message：" + message);
        System.out.println("pattern：" + new String(pattern));
        dynamicRuleFactory.test();
	
	}

}

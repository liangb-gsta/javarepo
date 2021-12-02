package com.ct.redis;



import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;



@Configuration
@PropertySource(value = {"classpath:redis.properties"})
public class SoarRedisConfig{
	@Resource
    Environment environment;
	
	@Bean
    //public RedisTemplate<String, Serializable> 
	public RedisTemplate<String, Object>
            redisTemplate(LettuceConnectionFactory connectionFactory) {
        //RedisTemplate<String, Serializable> redisTemplate = new RedisTemplate<>();
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        //redisTemplate.setKeySerializer(new StringRedisSerializer());
        //redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }
	
	@Bean
	public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
	                                               MessageListenerAdapter listenerAdapter){
	    RedisMessageListenerContainer container = new RedisMessageListenerContainer();
	    container.setConnectionFactory(connectionFactory);
	    String topicName = environment.getProperty("spring.redis.topic", "testtopic");
	    container.addMessageListener(listenerAdapter,new PatternTopic(topicName));
	    return container;
	}

	/**
	 * 绑定消息监听者和接收监听的方法,必须要注入这个监听器，不然会报错
	 */
	
	@Bean
	public MessageListenerAdapter listenerAdapter(){
	 //这个地方 是给messageListenerAdapter 传入一个消息接受的处理器，利用反射的方法调用“receiveMessage”
	 //也有好几个重载方法，这边默认调用处理器的方法 叫handleMessage 
		System.out.println("..............new a listener");
		return new MessageListenerAdapter(getMessageListener(),"receiveMessage");
	}
	@Bean
	public RedisMessageListener getMessageListener() {
		return new RedisMessageListener();
	}
}

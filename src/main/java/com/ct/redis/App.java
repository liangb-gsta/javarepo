package com.ct.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication
@ServletComponentScan
public class App {
	private static Logger log = LoggerFactory.getLogger(App.class);
	
	public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args);
    }
}

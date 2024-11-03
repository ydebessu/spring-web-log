package com.example.spring_web_log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringWebLogApplication {
	static Logger logger = LoggerFactory.getLogger(SpringWebLogApplication.class);

	public static void main(String[] args) {
		logger.info("Some stuff A", TypeMarks.GRADEA);
		logger.info("Some stuff B {}", 2, TypeMarks.GRADEB);
		logger.info("Some stuff default {}", "extra default values");
		SpringApplication.run(SpringWebLogApplication.class, args);
	}

}

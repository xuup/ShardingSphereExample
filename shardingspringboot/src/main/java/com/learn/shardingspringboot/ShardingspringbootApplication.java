package com.learn.shardingspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@RequestMapping("/")
public class ShardingspringbootApplication {

	public static void main(String[] args) {

		SpringApplication.run(ShardingspringbootApplication.class, args);
	}

	@RequestMapping("hello")
	@ResponseBody
	public String sayHello(){
		return "Hello World";
	}

}

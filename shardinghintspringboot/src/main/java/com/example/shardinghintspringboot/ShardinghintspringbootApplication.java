package com.example.shardinghintspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@RequestMapping("/")
public class ShardinghintspringbootApplication {

	public static void main(String[] args) {

		SpringApplication.run(ShardinghintspringbootApplication.class, args);
	}

	@RequestMapping("hello")
	public void hello(){
		System.out.println("say hello");
	}

}

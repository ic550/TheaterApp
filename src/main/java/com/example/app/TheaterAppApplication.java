package com.example.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextListener;

@SpringBootApplication
public class TheaterAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheaterAppApplication.class, args);
	}
	
	 @Bean
	    public RequestContextListener requestContextListener() {
	        return new RequestContextListener();
	    }

}

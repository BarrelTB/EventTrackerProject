package com.skilldistillery.eventtracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SeshTrackerApplication extends SpringBootServletInitializer{
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	  return application.sources(SeshTrackerApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SeshTrackerApplication.class, args);
	}

}

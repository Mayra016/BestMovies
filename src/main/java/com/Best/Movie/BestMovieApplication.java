package com.Best.Movie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@ComponentScan(basePackages = "com.Best.Movie")
@EntityScan(basePackages = "com.Best.Movie.Entities")
public class BestMovieApplication {

	public static void main(String[] args) {
		SpringApplication.run(BestMovieApplication.class, args);
	}

}

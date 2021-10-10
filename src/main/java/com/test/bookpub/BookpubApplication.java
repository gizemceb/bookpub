package com.test.bookpub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BookpubApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookpubApplication.class, args);
	}

	@Bean
	public StartupRunner schedulerRunner() {
		return new StartupRunner();
	}
}

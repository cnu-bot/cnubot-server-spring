package com.cnubot.cnubotserver;

import com.cnubot.cnubotserver.domain.service.BoardCrawling;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CnubotServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CnubotServerApplication.class, args);
	}

	/**
	@Bean
	CommandLineRunner run(BoardCrawling crawling){
		return  args ->crawling.process();
	}*/

}

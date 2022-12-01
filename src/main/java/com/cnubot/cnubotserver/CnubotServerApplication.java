package com.cnubot.cnubotserver;

import com.cnubot.cnubotserver.appconfig.Crawling;
import com.cnubot.cnubotserver.board.service.BoardCrawling;
import com.cnubot.cnubotserver.foodcourt.service.crawling.FoodCrawling;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CnubotServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CnubotServerApplication.class, args);
    }


    @Bean
    CommandLineRunner run(Crawling crawling) {
        return args -> crawling.process();
    }

}

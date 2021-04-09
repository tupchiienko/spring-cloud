package com.intentsg.service.edge;

import com.intentsg.service.edge.filters.PostFilter;
import com.intentsg.service.edge.filters.PreFilter2;
import com.intentsg.service.edge.filters.PreFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
public class EdgeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EdgeServiceApplication.class, args);
    }


    @Bean
    public PreFilter preFilter () {
        return new PreFilter();
    }

    @Bean
    public PostFilter postFilter() {
        return new PostFilter();
    }

    @Bean
    PreFilter2 preFilter2() {
        return new PreFilter2();
    }
}

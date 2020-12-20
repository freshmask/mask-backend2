package com.mask.mask;

import com.mask.mask.service.AuditorAwareImpl;
import org.aspectj.lang.annotation.Before;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.TimeZone;

@SpringBootApplication
@ComponentScan
@EnableJpaAuditing(auditorAwareRef="auditorAware", modifyOnCreate = false)
@EnableAuthorizationServer
@EnableResourceServer
@EnableScheduling
@EnableConfigurationProperties({FileStorageProperties.class})
@Import({SpringFoxConfig.class})
public class MaskApplication {


    @Bean
    public AuditorAware<String> auditorAware() {
        return new AuditorAwareImpl();
    }


    public static void main(String[] args) {
        SpringApplication.run(MaskApplication.class, args);
    }

//    @PostConstruct
//    public void init(){
//        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Jakarta"));
//        System.out.println("Spring boot application running in GMT timezone :"+new Date());
//    }




}

package com.cx.question;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.cx.question.**.repository")
@ServletComponentScan
@EnableScheduling
@EnableAsync
public class QueApplication {

    public static void main(String[] args) {
        SpringApplication.run(QueApplication.class, args);
    }

}

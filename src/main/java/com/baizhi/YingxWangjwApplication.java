package com.baizhi;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.baizhi.dao")
@SpringBootApplication
public class YingxWangjwApplication {

    public static void main(String[] args) {
        SpringApplication.run(YingxWangjwApplication.class, args);
    }

}

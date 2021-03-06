package com.atguigu.serviceedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author XieShifeng
 * @create 2021-09-22 6:53
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.atguigu"})
public class EduApplicaiton {
    public static void main(String[] args) {
        SpringApplication.run(EduApplicaiton.class,args);
    }
}

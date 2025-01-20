package com.sunny;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CouponProjectSunnySpringBoot3Application {

    public static void main(String[] args) {
        SpringApplication.run(CouponProjectSunnySpringBoot3Application.class, args);
    }
}

package com.flabedu.peoplemeet;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackageClasses = PeopleMeetApplication.class)
@SpringBootApplication
public class PeopleMeetApplication {

	public static void main(String[] args) {
		SpringApplication.run(PeopleMeetApplication.class, args);
	}

}

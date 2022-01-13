package com.flabedu.peoplemeet;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan( annotationClass = Mapper.class)
@SpringBootApplication
public class PeopleMeetApplication {

	public static void main(String[] args) {
		SpringApplication.run(PeopleMeetApplication.class, args);
	}

}

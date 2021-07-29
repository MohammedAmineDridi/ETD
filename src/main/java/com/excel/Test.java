package com.excel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackageClasses = Home.class)
@SpringBootApplication
public class Test {


	public static void main(String[] args) {
		SpringApplication.run(Test.class, args);
	}
	
}

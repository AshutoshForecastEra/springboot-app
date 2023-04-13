package com.spring.demo.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * The Class SpringRestJpaDemoApplication.
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class }) //Disable DataSourceAutoConfiguration, 
//so that spring doesn't auto configure datasource via spring application.properties. MultitenantConfiguration will be setting up the data sources
@ComponentScan({ "com.spring.demo.controller", "com.spring.demo.model", "com.spring.demo.service",
		"com.spring.demo.config" }) //Scan for all component classes
@EntityScan("com.spring.demo.entity") //Scan for all entities
@EnableJpaRepositories("com.spring.demo.repo") //Scan for repository classes
public class SpringRestJpaDemoApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringRestJpaDemoApplication.class, args);
	}
}

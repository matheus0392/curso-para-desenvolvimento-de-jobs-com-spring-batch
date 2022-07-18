package com.udemy.primeiroprojetospringbatch.config;

import java.io.File;
import java.nio.file.Paths;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class PropsConfig {

	@Bean
	public PropertySourcesPlaceholderConfigurer config() {
		PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
		
		configurer.setLocation(new FileSystemResource(
				"C:/Users/Matheus da Silva Nas/Desktop/curso-para-desenvolvimento-de-jobs-com-spring-batch/application.properties"));
		return configurer;
	}

}

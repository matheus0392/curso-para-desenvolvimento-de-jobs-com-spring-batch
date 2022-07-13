package com.udemy.primeiroprojetospringbatch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PrimeiroJobSpringBatchConfig {

	@Autowired
	JobBuilderFactory jobBuilderFactory;

	@Bean
	public Job ImprimeOlaJob(Step ImprimeOlaStep) {
		return jobBuilderFactory.get("ImprimeOlaJob").start(ImprimeOlaStep).incrementer(new RunIdIncrementer()).build();
	}

}

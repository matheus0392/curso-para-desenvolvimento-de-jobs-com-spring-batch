package com.udemy.leitoresspringbatch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LeitoresSpringBatchJobConfig {

	@Autowired
	JobBuilderFactory jobBuilderFactory;

	@Bean
	public Job DemonstrativoOrcamentarioJob(Step DemonstrativoOrcamentarioStep) {
		return jobBuilderFactory.get("DemonstrativoOrcamentarioJob ")
				.start(DemonstrativoOrcamentarioStep)
				.incrementer(new RunIdIncrementer())
				.build();
	}

}

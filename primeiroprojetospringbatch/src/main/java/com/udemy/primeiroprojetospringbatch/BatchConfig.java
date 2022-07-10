package com.udemy.primeiroprojetospringbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchConfig {

	@Autowired
	JobBuilderFactory jobBuilderFactory;

	@Autowired
	StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job ImprimeOlaJob() {
		return jobBuilderFactory.get("ImprimeOlaJob").start(ImprimeOlaStep()).incrementer(new RunIdIncrementer())
				.build();
	}

	public Step ImprimeOlaStep() {
		return stepBuilderFactory.get("ImprimeOlaStep").tasklet(mprimeOlaTasklet(null)).build();
	}

	@Bean
	@StepScope
	public Tasklet mprimeOlaTasklet(@Value("#{jobParameters['nome']}") String string) {
		return new Tasklet() {

			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				System.out.println(String.format("Ola %s", string));
				return RepeatStatus.FINISHED;
			}
		};
	}
}

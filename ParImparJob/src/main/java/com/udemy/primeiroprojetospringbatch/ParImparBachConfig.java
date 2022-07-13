package com.udemy.primeiroprojetospringbatch;

import java.util.Arrays;
import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ParImparBachConfig {
	@Autowired
	JobBuilderFactory jobBuilderFactory;

	@Autowired
	StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job ImprimeParImparJob() {
		return jobBuilderFactory.get("ImprimeParImparJob").start(ImprimeParImparStep()).incrementer(new RunIdIncrementer())
				.build();
	}
	
	public Step ImprimeParImparStep() {
		return stepBuilderFactory
				.get("ImprimeParImparStep")
				.<Integer, String>chunk(10)
				.reader(contaAteDezReader())
				.processor(parImparProcessor())
				.writer(imprimeWriter()).build();
	}

	private ItemWriter imprimeWriter() {
		return itens -> itens.forEach(System.out::println);
	}

	private FunctionItemProcessor<Integer, String> parImparProcessor() {

		return new FunctionItemProcessor<Integer, String>(
				item -> item % 2 == 0 ? String.format("Item %s é par", item) : String.format("Item %s é impar", item));
	}

	private IteratorItemReader<Integer> contaAteDezReader() {
		List<Integer> numeroDeUmADez = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		return new IteratorItemReader<Integer>(numeroDeUmADez);
	}

}
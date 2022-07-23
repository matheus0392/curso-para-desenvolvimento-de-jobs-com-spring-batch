package com.udemy.leitoresspringbatch.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.udemy.leitoresspringbatch.model.Lancamento;

@Configuration
public class DemonstrativoOrcamentarioStepStepConfig {
	
	@Autowired
	StepBuilderFactory stepBuilderFactory;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public Step DemonstrativoOrcamentarioStep(MultiResourceItemReader<Lancamento> MultipleFlatsFileItemReader) {
		return stepBuilderFactory
				.get("DemonstrativoOrcamentarioStep")
				.chunk(1)
				.reader(MultipleFlatsFileItemReader)
				.writer(imprimeWriter())
				.build();
	}

	private ItemWriter imprimeWriter() {
		return itens -> itens.forEach(System.out::println);
	}
	
}

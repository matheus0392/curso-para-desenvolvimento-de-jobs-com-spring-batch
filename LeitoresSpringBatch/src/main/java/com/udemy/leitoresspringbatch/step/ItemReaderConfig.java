package com.udemy.leitoresspringbatch.step;



import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.MultiResourceItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.udemy.leitoresspringbatch.model.Lancamento;

@Configuration
public class ItemReaderConfig {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@StepScope
	@Bean
	public MultiResourceItemReader MultipleFlatsFileItemReader(@Value("#{jobParameters['arquivos']}") Resource[] files,
			FlatFileItemReader flatFileItemReader) {
		return new MultiResourceItemReaderBuilder()
				.name("MultipleFlatsFileItemReader")
				.resources(files)
				.delegate(new DemonstrativoDelegateItemReader(flatFileItemReader))
				.build();
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean 
	public FlatFileItemReader FlatsFileItemReader() {
		return new FlatFileItemReaderBuilder()
				.name("FlatsFileItemReader")
				.delimited()
				.names("codigo", "natureza", "nome", "data", "valor")
				.fieldSetMapper(new BeanWrapperFieldSetMapper() {{	setTargetType(Lancamento.class); }})
				.build();
	}
}

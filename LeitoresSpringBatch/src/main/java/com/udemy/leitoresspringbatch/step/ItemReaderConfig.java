package com.udemy.leitoresspringbatch.step;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.MultiResourceItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.udemy.leitoresspringbatch.model.Lancamento;

@Configuration
public class ItemReaderConfig {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@StepScope
	//@Bean
	public MultiResourceItemReader MultipleFlatsFileItemReader(@Value("#{jobParameters['arquivos']}") Resource[] files,
			FlatFileItemReader flatFileItemReader) {
		return new MultiResourceItemReaderBuilder()
				.name("MultipleFlatsFileItemReader")
				.resources(files)
				.delegate(new DemonstrativoDelegateItemReader(flatFileItemReader))
				.build();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	//@Bean
	public FlatFileItemReader FlatsFileItemReader() {
		return new FlatFileItemReaderBuilder()
				.name("FlatsFileItemReader")
				.delimited()
				.names("codigoNaturezaDespesa", "descricaoNaturezaDespesa", "descricaoLancamento", "dataLancamento", "valorLancamento")
				.fieldSetMapper(new BeanWrapperFieldSetMapper() {{ setTargetType(Lancamento.class); }})
				.build();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public JdbcCursorItemReader jdbcCursorItemReader(@Qualifier("appDataSourceConfig") DataSource datasource) {
		return new JdbcCursorItemReaderBuilder()
				.name("FlatsFileItemReader")
				.dataSource(datasource)
				.sql("Select * from lancamento")
				.rowMapper(new BeanPropertyRowMapper<Lancamento>(Lancamento.class))
				.build();
	}
}

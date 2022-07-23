package com.udemy.leitoresspringbatch.step;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.core.io.Resource;

import com.udemy.leitoresspringbatch.model.Demonstrativo;
import com.udemy.leitoresspringbatch.model.Lancamento;

public class DemonstrativoDelegateItemReader implements ResourceAwareItemReaderItemStream<Demonstrativo> {
	Object objAtual;
	private ItemReader itemReader;
	private Demonstrativo demonstrativo;

	public DemonstrativoDelegateItemReader(FlatFileItemReader flatFileItemReader) {
		this.itemReader = flatFileItemReader;
	}

	public DemonstrativoDelegateItemReader(JdbcCursorItemReader dataSourceItemReader) {
		this.itemReader = dataSourceItemReader;
	}

	@Override
	public Demonstrativo read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

		if (objAtual == null) {
			objAtual = this.itemReader.read();
		}

		if (!(objAtual instanceof Lancamento)) {
			return null;
		}

		demonstrativo = new Demonstrativo((Lancamento) objAtual);

		Object peek = peek();
		while (peek instanceof Lancamento
				&& ((Lancamento) peek).getCodigoNaturezaDespesa() == demonstrativo.getCodigoNaturezaDespesa()) {
			demonstrativo.addLancamento((Lancamento) objAtual);
			peek = peek();
		}

		return demonstrativo;
	}

	private Object peek() throws UnexpectedInputException, ParseException, Exception {
		objAtual = this.itemReader.read();
		return objAtual;
	}

	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {
		if (itemReader instanceof FlatFileItemReader) {
			((FlatFileItemReader) itemReader).open(executionContext);
		}

		if (itemReader instanceof JdbcCursorItemReader) {
			((JdbcCursorItemReader) itemReader).open(executionContext);
		}
	}

	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {
		if (itemReader instanceof FlatFileItemReader) {
			((FlatFileItemReader) itemReader).update(executionContext);
		}

		if (itemReader instanceof JdbcCursorItemReader) {
			((JdbcCursorItemReader) itemReader).update(executionContext);
		}
	}

	@Override
	public void close() throws ItemStreamException {
		if (itemReader instanceof FlatFileItemReader) {
			((FlatFileItemReader) itemReader).close();
		}

		if (itemReader instanceof JdbcCursorItemReader) {
			((JdbcCursorItemReader) itemReader).close();
		}
	}

	@Override
	public void setResource(Resource resource) {
		if (itemReader instanceof FlatFileItemReader) {
			((FlatFileItemReader) itemReader).setResource(resource);
		}
	}

}

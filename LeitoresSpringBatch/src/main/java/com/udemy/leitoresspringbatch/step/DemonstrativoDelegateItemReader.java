package com.udemy.leitoresspringbatch.step;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.core.io.Resource;

import com.udemy.leitoresspringbatch.model.Demonstrativo;
import com.udemy.leitoresspringbatch.model.Lancamento;

public class DemonstrativoDelegateItemReader implements ResourceAwareItemReaderItemStream<Demonstrativo> {
	Object objAtual;
	private FlatFileItemReader flatFileItemReader;
	private Demonstrativo demonstrativo;

	public DemonstrativoDelegateItemReader(FlatFileItemReader flatFileItemReader) {
		this.flatFileItemReader = flatFileItemReader;
	}

	@Override
	public Demonstrativo read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

		if (objAtual == null) {
			objAtual = this.flatFileItemReader.read();
		}

		if (!(objAtual instanceof Lancamento)) {
			return null;
		}

		demonstrativo = new Demonstrativo((Lancamento) objAtual);

		Object peek = peek();
		while (peek instanceof Lancamento && ((Lancamento) peek).getCodigo() == demonstrativo.getCodigo()) {
			demonstrativo.addLancamento((Lancamento) objAtual);
			peek = peek();
		}

		return demonstrativo;
	}

	private Object peek() throws UnexpectedInputException, ParseException, Exception {
		objAtual = this.flatFileItemReader.read();
		return objAtual;
	}

	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {
		flatFileItemReader.open(executionContext);
	}

	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {
		flatFileItemReader.update(executionContext);
	}

	@Override
	public void close() throws ItemStreamException {
		flatFileItemReader.close();
	}

	@Override
	public void setResource(Resource resource) {
		flatFileItemReader.setResource(resource);
	}

}
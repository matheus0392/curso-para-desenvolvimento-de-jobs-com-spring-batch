package com.udemy.leitoresspringbatch.model;

import java.util.ArrayList;
import java.util.List;

public class Demonstrativo {

	int codigo;
	String natureza;
	int total;
	List<Lancamento> lancamentos;

	public Demonstrativo(Lancamento lancamento) {
		super();
		this.lancamentos = new ArrayList<>();
		this.natureza = lancamento.natureza;
		this.codigo = lancamento.codigo;
		this.addLancamento(lancamento);
	}

	public void addLancamento(Lancamento currentLancamento) {
		this.lancamentos.add(currentLancamento);
		this.total = this.lancamentos.stream().map(e -> e.valor).reduce((t, u) -> t + u).orElse(0);
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNatureza() {
		return natureza;
	}

	public void setNatureza(String natureza) {
		this.natureza = natureza;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}


	private String LancamentostoString() {
		StringBuilder sb= new StringBuilder();
		for(Lancamento lancamento :this.lancamentos) {
			sb.append(lancamento.toString());
		}
		
		return sb.toString();
	}
	

	@Override
	public String toString() {
		return "---- Demonstrativo orçamentário ---- \n [" + codigo + "] " + natureza + " - R$ " + total + "\n"
				+ LancamentostoString();
	}
	
	
	

}

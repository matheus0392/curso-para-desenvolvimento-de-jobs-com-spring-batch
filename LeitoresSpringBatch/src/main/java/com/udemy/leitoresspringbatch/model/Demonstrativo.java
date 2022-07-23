package com.udemy.leitoresspringbatch.model;

import java.util.ArrayList;
import java.util.List;

public class Demonstrativo {

	int codigoNaturezaDespesa;
	String descricaoNaturezaDespesa;
	int total;
	List<Lancamento> lancamentos;

	public Demonstrativo(Lancamento lancamento) {
		super();
		this.lancamentos = new ArrayList<>();
		this.descricaoNaturezaDespesa = lancamento.descricaoNaturezaDespesa;
		this.codigoNaturezaDespesa = lancamento.codigoNaturezaDespesa;
		this.addLancamento(lancamento);
	}

	public void addLancamento(Lancamento currentLancamento) {
		this.lancamentos.add(currentLancamento);
		this.total = this.lancamentos.stream().map(e -> e.valorLancamento).reduce((t, u) -> t + u).orElse(0);
	}

	private String LancamentostoString() {
		StringBuilder sb = new StringBuilder();
		for (Lancamento lancamento : this.lancamentos) {
			sb.append(lancamento.toString());
		}

		return sb.toString();
	}

	public int getTotal() {
		return total;
	}

	public int getCodigoNaturezaDespesa() {
		return codigoNaturezaDespesa;
	}

	public String getDescricaoNaturezaDespesa() {
		return descricaoNaturezaDespesa;
	}

	@Override
	public String toString() {
		return "---- Demonstrativo orçamentário ---- \n [" + codigoNaturezaDespesa + "] " + descricaoNaturezaDespesa
				+ " - R$ " + total + "\n" + LancamentostoString();
	}

}

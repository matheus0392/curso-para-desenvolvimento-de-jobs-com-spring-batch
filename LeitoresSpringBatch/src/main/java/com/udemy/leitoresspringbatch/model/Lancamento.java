package com.udemy.leitoresspringbatch.model;

public class Lancamento {

	int codigoNaturezaDespesa;
	String descricaoNaturezaDespesa;
	String descricaoLancamento;
	String dataLancamento;
	int valorLancamento;

	public int getCodigoNaturezaDespesa() {
		return codigoNaturezaDespesa;
	}

	public void setCodigoNaturezaDespesa(int codigo) {
		this.codigoNaturezaDespesa = codigo;
	}

	public String getDescricaoNaturezaDespesa() {
		return descricaoNaturezaDespesa;
	}

	public void setDescricaoNaturezaDespesa(String natureza) {
		this.descricaoNaturezaDespesa = natureza;
	}

	public String getDescricaoLancamento() {
		return descricaoLancamento;
	}

	public void setDescricaoLancamento(String nome) {
		this.descricaoLancamento = nome;
	}

	public String getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(String data) {
		this.dataLancamento = data;
	}

	public int getValorLancamento() {
		return valorLancamento;
	}

	public void setValorLancamento(int valor) {
		this.valorLancamento = valor;
	}

	@Override
	public String toString() {
		return "\t[" + dataLancamento + "] " + descricaoLancamento + " - R$ " + valorLancamento + "\n";
	}
}

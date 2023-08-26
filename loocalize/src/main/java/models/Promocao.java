package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Promocao {

	//Atributos
	private String id;
	private String titulo;
	private int porcentagemDeDesconto;
	private int qtdMinimaDeDiarias;
	private int qtdMinimaDeLocacoes;
	private LocalDate dataDeCriacao;
	private LocalDate dataDeExpiracao;
	private boolean ativa;

	//Construtor
	public Promocao(String titulo, int porcentagemDeDesconto, int qtdMinimaDeDiarias, int qtdMinimaDeLocacoes, LocalDate dataDeCriacao, LocalDate dataDeExpiracao, boolean ativa) {
		this.titulo = titulo;
		this.porcentagemDeDesconto = porcentagemDeDesconto;
		this.qtdMinimaDeDiarias = qtdMinimaDeDiarias;
		this.qtdMinimaDeLocacoes = qtdMinimaDeLocacoes;
		this.dataDeCriacao = dataDeCriacao;
		this.dataDeExpiracao = dataDeExpiracao;
		this.ativa = ativa;
	}
	//Getts e Setts
	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getPorcentagemDeDesconto() {
		return porcentagemDeDesconto;
	}

	public void setPorcentagemDeDesconto(int porcentagemDeDesconto) {
		this.porcentagemDeDesconto = porcentagemDeDesconto;
	}

	public int getQtdMinimaDeDiarias() {
		return qtdMinimaDeDiarias;
	}

	public void setQtdMinimaDeDiarias(int qtdMinimaDeDiarias) {
		this.qtdMinimaDeDiarias = qtdMinimaDeDiarias;
	}

	public int getQtdMinimaDeLocacoes() {
		return qtdMinimaDeLocacoes;
	}

	public void setQtdMinimaDeLocacoes(int qtdMinimaDeLocacoes) {
		this.qtdMinimaDeLocacoes = qtdMinimaDeLocacoes;
	}

	public LocalDate getDataDeCriacao() {
		return dataDeCriacao;
	}

	public void setDataDeCriacao(LocalDate dataDeCriacao) {
		this.dataDeCriacao = dataDeCriacao;
	}

	public LocalDate getDataDeExpiracao() {
		return dataDeExpiracao;
	}

	public void setDataDeExpiracao(LocalDate dataDeExpiracao) {
		this.dataDeExpiracao = dataDeExpiracao;
	}

	public boolean isAtiva() {
		return ativa;
	}

	public void setAtiva(boolean ativa) {
		this.ativa = ativa;
	}

	@Override
	public String toString() {
		DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return String.format("<Promoção>\nID: %s\nTítulo: %s\nData de criação: %s\nData de expiração: %s\nPorcentagem de desconto: %d\nAtiva: %s", id, titulo, dataDeCriacao.format(formatoData), dataDeExpiracao.format(formatoData), porcentagemDeDesconto, ativa);
	}

}
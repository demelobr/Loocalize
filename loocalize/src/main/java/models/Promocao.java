package models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Promocao {

	//Atributos
	private String id;
	private String titulo;
	private int porcentagemDeDesconto;
	private LocalDate dataDeCriacao;
	private LocalDateTime dataDeExpiracao;
	private boolean ativa;
	
	//Construtor
	public Promocao(String titulo, int porcentagemDeDesconto, LocalDate dataDeCriacao, LocalDateTime dataDeExpiracao, boolean ativa) {
		this.titulo = titulo;
		this.porcentagemDeDesconto = porcentagemDeDesconto;
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

	public LocalDate getDataDeCriacao() {
		return dataDeCriacao;
	}

	public void setDataDeCriacao(LocalDate dataDeCriacao) {
		this.dataDeCriacao = dataDeCriacao;
	}

	public LocalDateTime getDataDeExpiracao() {
		return dataDeExpiracao;
	}

	public void setDataDeExpiracao(LocalDateTime dataDeExpiracao) {
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
		DateTimeFormatter formatoDataHora = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
		return String.format("<Promoção>\nID: %s\nTítulo: %s\nData de criação: %s\nData de expiração: %s\nPorcentagem de desconto: %d\nAtiva: %s", id, titulo, dataDeCriacao.format(formatoData), dataDeExpiracao.format(formatoDataHora), porcentagemDeDesconto, ativa);
	}

}

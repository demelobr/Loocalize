package models;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
	public Promocao(String titulo, int porcentagemDeDesconto, LocalDate dataDeCriacao,
			LocalDateTime dataDeExpiracao, boolean ativa) {
		this.id = this.gerarId();
		this.titulo = titulo;
		this.porcentagemDeDesconto = porcentagemDeDesconto;
		this.dataDeCriacao = dataDeCriacao;
		this.dataDeExpiracao = dataDeExpiracao;
		this.ativa = ativa;
	}
	//Getts e Setts

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
	
	private String gerarId() {
		String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(caracteres.length());
            char caractere = caracteres.charAt(index);
            sb.append(caractere);
        }

        return sb.toString();
	}
}

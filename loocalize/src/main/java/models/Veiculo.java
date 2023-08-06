package models;

import java.time.Year;
import java.util.Random;

public class Veiculo {

	//Atributos
	private String id;			
	private String modelo;
	private String marca;
	private String placa;
	private Year ano;
	private int quilometragem;
	private int qtdDeLocacao;
	private double valorDoKmRodadoAMais;
	private boolean disponivel;
	
	//Construtor
	public Veiculo( String modelo, String marca, String placa, Year ano, int quilometragem, int qtdDeLocacao, double valorDoKmRodadoAMais, boolean disponivel) {
		this.id = this.gerarId();
		this.modelo = modelo;
		this.marca = marca;
		this.placa = placa;
		this.ano = ano;
		this.quilometragem = quilometragem;
		this.qtdDeLocacao = qtdDeLocacao;
		this.valorDoKmRodadoAMais = valorDoKmRodadoAMais;
		this.disponivel = disponivel;
	}
	
	//Getts e Setts
	public String getId() {
		return id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Year getAno() {
		return ano;
	}

	public void setAno(Year ano) {
		this.ano = ano;
	}

	public int getQuilometragem() {
		return quilometragem;
	}

	public void setQuilometragem(int quilometragem) {
		this.quilometragem = quilometragem;
	}

	public int getQtdDeLocacao() {
		return qtdDeLocacao;
	}

	public void setQtdDeLocacao(int qtdDeLocacao) {
		this.qtdDeLocacao = qtdDeLocacao;
	}

	public double getValorDoKmRodadoAMais() {
		return valorDoKmRodadoAMais;
	}

	public void setValorDoKmRodadoAMais(double valorDoKmRodadoAMais) {
		this.valorDoKmRodadoAMais = valorDoKmRodadoAMais;
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}
	
	//Método para gerar valor por km rodado a mais.
	public double gerarValorPorKmAMais() {
       
        return 100.0;
    }
	//Gerar id
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
	
	//toString
	@Override
	public String toString(){
        return String.format("<Veiculo>\nID: %s\nModelo: %s\nMarca: %s\nAno: %s\nPlaca: %s\nQuilometragem: %d\nQtd de locações: %d\nValor/Km a mais: R$%.2f\nDisponível: %s", id, modelo, marca, ano, placa, quilometragem, qtdDeLocacao, valorDoKmRodadoAMais, disponivel);
    }
}
	


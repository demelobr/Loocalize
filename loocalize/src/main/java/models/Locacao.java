package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Locacao {

	//Atributos
	private String id;
	private Veiculo veiculo;
	private Cliente cliente;
	private Promocao promocao;
	private LocalDateTime dataHoraDaLocacao;
	private int qtdDeDiarias;
	private double valorDaLocacao;
	private boolean carroDevolvido;
	
	//Construtor
	public Locacao(Veiculo veiculo, Cliente cliente, Promocao promocao, LocalDateTime dataHoraDaLocacao, int qtdDeDiarias, boolean carroDevolvido) {
		this.veiculo = veiculo;
		this.cliente = cliente;
		this.promocao = promocao;
		this.dataHoraDaLocacao = dataHoraDaLocacao;
		this.qtdDeDiarias = qtdDeDiarias;
		this.gerarValorDaLocacao();
		this.carroDevolvido = carroDevolvido;
	}
	//Getts e Setts
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Promocao getPromocao() {
		return promocao;
	}

	public void setPromocao(Promocao promocao) {
		this.promocao = promocao;
	}

	public LocalDateTime getDataHoraDaLocacao() {
		return dataHoraDaLocacao;
	}

	public void setDataHoraDaLocacao(LocalDateTime dataHoraDaLocacao) {
		this.dataHoraDaLocacao = dataHoraDaLocacao;
	}

	public int getQtdDeDiarias() {
		return qtdDeDiarias;
	}

	public void setQtdDeDiarias(int qtdDeDiarias) {
		this.qtdDeDiarias = qtdDeDiarias;
	}

	public double getValorDaLocacao() {
		return valorDaLocacao;
	}

	public void setValorDaLocacao(double valorDaLocacao) {
		this.valorDaLocacao = valorDaLocacao;
	}

	public boolean isCarroDevolvido() {
		return carroDevolvido;
	}

	public void setCarroDevolvido(boolean carroDevolvido) {
		this.carroDevolvido = carroDevolvido;
	}

	 // Método para gerar o valor da locação
	 public void gerarValorDaLocacao() {
		this.setValorDaLocacao(this.getQtdDeDiarias()*this.getVeiculo().getValorDaDiaria());
	 }
		
	//toString
	@Override
	public String toString(){
		DateTimeFormatter formatoDataHora = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
		return String.format("<Locação>\nID: %s\nID do veículo: %s\nPlaca do veículo: %s\nCliente: %s\nPromoção: %s\nData e hora da Locação: %s\nDiárias: %d\nValor da locação: R$%.2f\nCarro devolvido: %s", id, this.getVeiculo().getId(), this.getVeiculo().getPlaca(), this.getCliente().getNome(), this.getPromocao().getTitulo(), dataHoraDaLocacao.format(formatoDataHora), qtdDeDiarias, valorDaLocacao, carroDevolvido);
	}

}

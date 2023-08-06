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
	private int limiteDeKmRodados;
	private double valorDaLocacao;
	private boolean carroDevolvido;
	
	//Construtor
	public Locacao(Veiculo veiculo, Cliente cliente, Promocao promocao, LocalDateTime dataHoraDaLocacao,
			int qtdDeDiarias, int limiteDeKmRodados, double valorDaLocacao, boolean carroDevolvido) {
		this.id = this.gerarId();
		this.veiculo = veiculo;
		this.cliente = cliente;
		this.promocao = promocao;
		this.dataHoraDaLocacao = dataHoraDaLocacao;
		this.qtdDeDiarias = qtdDeDiarias;
		this.limiteDeKmRodados = limiteDeKmRodados;
		this.valorDaLocacao = valorDaLocacao;
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

	public LocalDateTime getdataHoraDaLocacao() {
		return dataHoraDaLocacao;
	}

	public void setdataHoraDaLocacao(LocalDateTime dataHoraDaLocacao) {
		this.dataHoraDaLocacao = dataHoraDaLocacao;
	}

	public int getQtdDeDiarias() {
		return qtdDeDiarias;
	}

	public void setQtdDeDiarias(int qtdDeDiarias) {
		this.qtdDeDiarias = qtdDeDiarias;
	}

	public int getLimiteDeKmRodados() {
		return limiteDeKmRodados;
	}

	public void setLimiteDeKmRodados(int limiteDeKmRodados) {
		this.limiteDeKmRodados = limiteDeKmRodados;
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
	
	//Métodos
	// Método para aplicar promoção na locação
	public void aplicarPromocao() {
		
	}

	 // Método para gerar o valor da locação
	 public void gerarValorDaLocacao() {
		 
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
	        DateTimeFormatter formatoDataHora = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
	        return String.format("<Locação>\nID: %s\nPlaca do veículo: %s\nCliente: %s\nPromoção: %s\nData e hora da Locação: %s\nDiárias: %d\nLimites de Km: %d\nValor da locação: R$%.2f\nCarro devolvido: %s", id, this.getVeiculo().getPlaca(), this.getCliente().getNome(), this.getPromocao().getTitulo(), dataHoraDaLocacao.format(formatoDataHora), qtdDeDiarias, limiteDeKmRodados, valorDaLocacao, carroDevolvido);
	    }
}


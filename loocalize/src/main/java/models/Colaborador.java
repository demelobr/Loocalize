package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Colaborador extends Usuario{
	//Atributos
		private String nomeCompleto;
		private String cpf;
		private LocalDate dataNascimento;
		private String telefone;
		private String endereco;
		
		//Construtor
		public Colaborador(String usuario, String senha, String email, String nomeCompleto,
				String cpf, LocalDate dataNascimento, String telefone, String endereco) {
			super(usuario, senha, email, false);
			this.nomeCompleto = nomeCompleto;
			this.cpf = cpf;
			this.dataNascimento = dataNascimento;
			this.telefone = telefone;
			this.endereco = endereco;
		}

		//Métodos Getts e Setts
		public String getNomeCompleto() {
			return nomeCompleto;
		}

		public void setNomeCompleto(String nomeCompleto) {
			this.nomeCompleto = nomeCompleto;
		}

		public String getCpf() {
			return cpf;
		}

		public void setCpf(String cpf) {
			this.cpf = cpf;
		}

		public LocalDate getDataNascimento() {
			return dataNascimento;
		}

		public void setDataNascimento(LocalDate dataNascimento) {
			this.dataNascimento = dataNascimento;
		}

		public String getTelefone() {
			return telefone;
		}

		public void setTelefone(String telefone) {
			this.telefone = telefone;
		}

		public String getEndereco() {
			return endereco;
		}

		public void setEndereço(String endereco) {
			this.endereco = endereco;
		}
		//toString
		@Override
	    public String toString(){
	        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	        return String.format("<Colaborador>\nID: %s\nADM: %s\nUsuário: %s\nNome Completo: %s\nEmail: %s\nCPF: %s\nData de Nascimento: %s\nTelefone: %s\nEndereço: %s", this.getId(), this.isAdm(), this.getUsuario(), nomeCompleto, this.getEmail(), cpf, dataNascimento.format(formatoData), telefone, endereco);
	    }
}

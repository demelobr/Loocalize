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
		private boolean adm;

		//Construtor
		public Colaborador(String usuario, String senha, String email, String nomeCompleto, String cpf, LocalDate dataNascimento, String telefone, String endereco, boolean adm) {
			super(usuario, senha, email);
			this.nomeCompleto = nomeCompleto;
			this.cpf = cpf;
			this.dataNascimento = dataNascimento;
			this.telefone = telefone;
			this.endereco = endereco;
			this.adm = adm;
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

		public void setEndereco(String endereco) {
			this.endereco = endereco;
		}

		public boolean isAdm() {
			return adm;
		}

		//toString
		@Override
	    public String toString(){
	        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	        return String.format("<Colaborador>\nID: %s\nUsuário: %s\nNome Completo: %s\nEmail: %s\nCPF: %s\nData de Nascimento: %s\nTelefone: %s\nEndereço: %s\nAdm: %s", this.getId(), this.getUsuario(), nomeCompleto, this.getEmail(), cpf, dataNascimento.format(formatoData), telefone, endereco, adm);
	    }

}

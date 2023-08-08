package models;

import java.util.Random;

public abstract class Usuario {

	//Atributos
		private String id;
		private String usuario;
		private String senha;
		private String email;
		private boolean adm;
		
		//Construtor
		public Usuario(String usuario, String senha, String email, boolean adm) {
			this.id = this.gerarId();
			this.usuario = usuario;
			this.senha = senha;
			this.email = email;
			this.adm = adm;
		}
		
		//Métodos getts e setts
		public String getId() {
			return id;
		}

		public String getUsuario() {
			return usuario;
		}

		public void setUsuario(String usuario) {
			this.usuario = usuario;
		}

		public String getSenha() {
			return senha;
		}

		public void setSenha(String senha) {
			this.senha = senha;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public boolean isAdm() {
			return adm;
		}

		public void setAdm(boolean adm) {
			this.adm = adm;
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
		
		//toStirng
		@Override
		public String toString() {
			return "Usuario "+ usuario +" possui: \n Id= " + id + " e email " + email + "\n É administrador? " + adm;
		}
}

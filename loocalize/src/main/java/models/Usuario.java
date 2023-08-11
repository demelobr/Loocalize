package models;

import java.util.Random;

public abstract class Usuario {

		//Atributos
		private String id;
		private String usuario;
		private String senha;
		private String email;
		
		//Construtor
		public Usuario(String usuario, String senha, String email) {
			this.usuario = usuario;
			this.senha = senha;
			this.email = email;
		}
		
		//Métodos getts e setts
		public void setId(String id) {
			this.id = id;
		}
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

}

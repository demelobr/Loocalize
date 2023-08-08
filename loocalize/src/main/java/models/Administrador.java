package models;

public class Administrador extends Usuario{

	//Construtor herdado
		public Administrador(String usuario, String senha, String email) {
			super(usuario, senha, email, true);
		}

		//Método solitário para gerar administrador 
		public Administrador gerarAdministradorPadrao() {
			 return new Administrador("adm", "adm", "adm@gmail.com");
	    }
		
		@Override
	    public String toString(){
	        return String.format("<Administrador>\nNome: %s\nADM: %s", this.getUsuario(), this.isAdm());
	    }
}

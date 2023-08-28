package exception;

import models.Usuario;

public class UsuarioLogadoComSucessoException extends Exception{
    private Usuario usuario;
    private String fxml;
    private String title;

    public UsuarioLogadoComSucessoException(Usuario usuario, String fxml, String title){
        super("Login feito com sucesso!");
        this.usuario = usuario;
        this.fxml = fxml;
        this.title = title;
    }

    public String getFxml(){
        return fxml;
    }

    public void setFxml(String fxml){
        this.fxml = fxml;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

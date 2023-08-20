package exception;

public class UsuarioExisteException extends Exception{
    private String usuario;

    public UsuarioExisteException(String user){
        super("Usuário já existente!");
        this.usuario = user;
    }

    public String getUsuario(){
        return usuario;
    }

    public void setUsuario(String user){
        this.usuario = user;
    }
}

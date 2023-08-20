package exception;

public class UsuarioNaoExisteException extends Exception{
    private String usuario;

    public UsuarioNaoExisteException(String user){
        super("Usuário não existente!");
        this.usuario = user;
    }

    public String getUsuario(){
        return usuario;
    }

    public void setUsuario(String user){
        this.usuario = user;
    }
}

package exception;

public class UsuarioNuloException extends Exception{
    private String usuario;

    public UsuarioNuloException(String user){
        super("Usuário nulo!");
        this.usuario = user;
    }

    public String getUsuario(){
        return usuario;
    }

    public void setUsuario(String user){
        this.usuario = user;
    }
}

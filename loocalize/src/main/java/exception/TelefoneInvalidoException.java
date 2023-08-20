package exception;

public class TelefoneInvalidoException extends Exception{
    private String telefone;

    public TelefoneInvalidoException(String telefone){
        super("Telefone inválido!");
        this.telefone = telefone;
    }

    public String getTelefone(){
        return telefone;
    }

    public void setTelefone(String telefone){
        this.telefone = telefone;
    }
}

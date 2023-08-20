package exception;

public class EmailInvalidoException extends Exception{
    private String email;

    public EmailInvalidoException(String email){
        super("Email inválido!");
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }
}

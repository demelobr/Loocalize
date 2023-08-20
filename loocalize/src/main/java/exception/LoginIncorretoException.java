package exception;

public class LoginIncorretoException extends Exception{
    public LoginIncorretoException(){
        super("Usuário ou senha incorretos!");
    }
}

package exception;

public class SenhasDiferentesException extends Exception{
    public SenhasDiferentesException(){
        super("Confirmação de senhas inválida!");
    }
}

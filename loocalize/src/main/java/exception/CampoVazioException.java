package exception;

public class CampoVazioException extends Exception{
    public CampoVazioException(){
        super("Existe campo vazio!");
    }
}

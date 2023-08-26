package exception;

public class PromocaoNaoExisteException extends Exception{
    private String titulo;

    public PromocaoNaoExisteException(String titulo){
        super("Promoção não existente!");
        this.titulo = titulo;
    }

    public String getTitulo(){
        return titulo;
    }

    public void setTitulo(String titulo){
        this.titulo = titulo;
    }
}

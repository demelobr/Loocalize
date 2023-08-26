package exception;

public class PromocaoNulaException extends Exception{
    private String titulo;

    public PromocaoNulaException(String titulo){
        super("Promoção nula!");
        this.titulo = titulo;
    }

    public String getTitulo(){
        return titulo;
    }

    public void setTitulo(String titulo){
        this.titulo = titulo;
    }
}

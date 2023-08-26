package exception;

public class PromocaoExisteException extends Exception{
    private String id;
    public PromocaoExisteException(String id){
        super("Promoção já existe!");
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

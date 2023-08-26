package exception;

public class VeiculoExisteException extends Exception{
    private String id;
    public VeiculoExisteException(String id){
        super("Veículo já existe!");
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

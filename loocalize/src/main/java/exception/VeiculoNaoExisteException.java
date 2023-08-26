package exception;

public class VeiculoNaoExisteException extends Exception{
    private String placa;

    public VeiculoNaoExisteException(String placa){
        super("Veículo não existente!");
        this.placa = placa;
    }

    public String getPlaca(){
        return placa;
    }

    public void setPlaca(String placa){
        this.placa = placa;
    }
}

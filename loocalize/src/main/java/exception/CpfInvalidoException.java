package exception;

public class CpfInvalidoException extends Exception{
    private String cpf;

    public CpfInvalidoException(String cpf){
        super("CPF inválido!");
        this.cpf = cpf;
    }

    public String getCpf(){
        return cpf;
    }

    public void setCpf(String cpf){
        this.cpf = cpf;
    }
}

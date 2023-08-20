package exception;

import java.time.LocalDate;

public class DataDeNascimentoInvalidaException extends Exception{
    private LocalDate dataDeNascimento;

    public DataDeNascimentoInvalidaException(LocalDate dataDeNascimento){
        super("Data de nascimento inválida!");
        this.dataDeNascimento = dataDeNascimento;
    }

    public LocalDate getDataDeNascimento(){
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento){
        this.dataDeNascimento = dataDeNascimento;
    }
}

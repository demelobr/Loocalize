package exception;

import java.time.LocalDate;

public class DataDeHabilitacaoInvalidaException extends Exception{
    private LocalDate dataDeHabilitacao;

    public DataDeHabilitacaoInvalidaException(LocalDate dataDeHabilitacao){
        super("Data de habilitação inválida!");
        this.dataDeHabilitacao = dataDeHabilitacao;
    }

    public LocalDate getDataDeHabilitacao(){
        return dataDeHabilitacao;
    }

    public void setDataDeHabilitacao(LocalDate dataDeHabilitacao){
        this.dataDeHabilitacao = dataDeHabilitacao;
    }
}

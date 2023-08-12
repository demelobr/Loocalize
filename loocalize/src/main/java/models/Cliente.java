package models;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Cliente extends Usuario{
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private String telefone;
    private String endereco;
    private String cnh;
    private LocalDate dataDeHabilitacao;
    private int qtdDeLocacoes;
    private ArrayList<Locacao> locacoes;

    public Cliente(String usuario, String senha, String email, String nome, String cpf, LocalDate dataNascimento, String telefone, String endereco, String cnh, LocalDate dataDeHabilitacao, int qtdDeLocacoes){
        super(usuario, senha, email);
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.endereco = endereco;
        this.cnh = cnh;
        this.dataDeHabilitacao = dataDeHabilitacao;
        this.qtdDeLocacoes = qtdDeLocacoes;
        this.locacoes = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public LocalDate getDataDeHabilitacao() {
        return dataDeHabilitacao;
    }

    public void setDataDeHabilitacao(LocalDate dataDeHabilitacao) {
        this.dataDeHabilitacao = dataDeHabilitacao;
    }

    public int getQtdDeLocacoes() {
        return qtdDeLocacoes;
    }

    public void setQtdDeLocacoes(int qtdDeLocacoes) {
        this.qtdDeLocacoes = qtdDeLocacoes;
    }

    public ArrayList<Locacao> getLocacoes() {
        return locacoes;
    }

    public void setLocacoes(ArrayList<Locacao> locacoes) {
        this.locacoes = locacoes;
    }

    public void addLocacao(Locacao locacao){
        this.locacoes.add(locacao);
    }

    public int getIdade(LocalDate dataNascimento){
        LocalDate dataAtual = LocalDate.now();
        Period periodo = Period.between(dataNascimento, dataAtual);
        return periodo.getYears();
    }

    public int getAnosDeHabilitacao(LocalDate dataDeHabilitacao){
        LocalDate dataAtual = LocalDate.now();
        Period periodo = Period.between(dataDeHabilitacao, dataAtual);
        return periodo.getYears();
    }

    @Override
    public String toString(){
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("<Cliente>\nID: %s\nNome: %s\nCpf: %s\nEmail: %s\nData de Nasciemento: %s\nTelefone: %s\nEndereço: %s\nCNH: %s\nData de Habilitação: %s\nQuantidade de locações: %d\nLocações: %s", this.getId(), nome, cpf, this.getEmail(), dataNascimento.format(formatoData), telefone, endereco, cnh, dataDeHabilitacao.format(formatoData), qtdDeLocacoes, locacoes);
    }
}

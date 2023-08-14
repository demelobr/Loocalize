package business;

import data.IRepositorioLocacoes;
import data.IRepositorioUsuarios;
import data.RepositorioLocacoes;
import data.RepositorioUsuarios;
import models.Cliente;
import models.Locacao;
import models.Promocao;
import models.Veiculo;

import java.time.LocalDateTime;
import java.util.List;

public class ControladorLocacoes implements IControladorLocacoes{
    private static ControladorLocacoes instance;
    private IRepositorioLocacoes repLocacoes;

    public ControladorLocacoes(){
        this.repLocacoes = RepositorioLocacoes.getInstance();
    }

    public static ControladorLocacoes getInstance(){
        if(instance == null){
            instance = new ControladorLocacoes();
        }
        return instance;
    }

    @Override
    public void inserirLocacao(Locacao locacao) {
        if(locacao != null){
            if(!repLocacoes.existeLocacao(locacao.getId())){
                if(locacao.getVeiculo() != null && locacao.getCliente() != null && locacao.getPromocao() != null &&
                   !locacao.getDataHoraDaLocacao().isBefore(LocalDateTime.now()) && locacao.getQtdDeDiarias() > 0){
                    do{
                        locacao.setId(repLocacoes.gerarId());
                    }while (repLocacoes.existeLocacao(locacao.getId()));
                    repLocacoes.inserir(locacao);
                }
            }else{
                // Levantar exception de Locação não existente
            }
        }else{
            // Levantar exception de Locação nula
        }
    }

    @Override
    public void atualizarLocacao(Locacao locacao, Veiculo veiculo, Cliente cliente, Promocao promocao, LocalDateTime dataHoraDaLocacao, int qtdDeDiarias, boolean carroDevolvido) {
        if(locacao != null){
            if(repLocacoes.existeLocacao(locacao.getId())){
                if(veiculo == null){
                    veiculo = locacao.getVeiculo();
                }
                if(cliente == null){
                    cliente = locacao.getCliente();
                }
                if(promocao == null){
                    promocao = locacao.getPromocao();
                }
                if(dataHoraDaLocacao.isAfter(LocalDateTime.now())){
                    dataHoraDaLocacao = locacao.getDataHoraDaLocacao();
                }
                if(qtdDeDiarias <= 0){
                    qtdDeDiarias = locacao.getQtdDeDiarias();
                }
                repLocacoes.atualizar(locacao, veiculo, cliente, promocao, dataHoraDaLocacao, qtdDeDiarias, carroDevolvido);
            }else{
                // Levantar exception de Locação não existente
            }
        }else{
            // Levantar exception de Locação nula
        }
    }

    @Override
    public void deletarLocacao(Locacao locacao) {
        if(locacao != null){
            if(repLocacoes.existeLocacao(locacao.getId())){
                repLocacoes.deletar(locacao);
            }else{
                // Levantar exception de Locação não existente
            }
        }else{
            // Levantar exception de Locação nula
        }
    }

    @Override
    public void aplicarPromocao(Locacao locacao) {
        if(locacao != null){
            if(repLocacoes.existeLocacao(locacao.getId())){

            }else{
                // Levantar exception de Locação não existente
            }
        }else{
            // Levantar exception de Locação nula
        }
    }

    @Override
    public List<Locacao> listarTodasLocacoes() {
        return repLocacoes.listarTodasLocacoes();
    }

    @Override
    public boolean existeLocacao(String id) {
        return repLocacoes.existeLocacao(id);
    }

    @Override
    public Locacao buscarLocacao(String id) {
        return repLocacoes.buscarLocacao(id);
    }

    @Override
    public String gerarId() {
        return repLocacoes.gerarId();
    }
}

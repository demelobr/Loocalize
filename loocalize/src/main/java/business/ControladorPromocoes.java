package business;

import data.IRepositorioPromocoes;
import data.RepositorioPromocoes;
import models.Promocao;
import java.time.LocalDateTime;
import java.util.List;


public class ControladorPromocoes implements IControladorPromocoes {

    //singleton
    private static ControladorPromocoes instance;
    private IRepositorioPromocoes repPromocoes;

    private ControladorPromocoes() {
        this.repPromocoes = RepositorioPromocoes.getInstance();
    }

    public static ControladorPromocoes getInstance() {
        if (instance == null) {
            instance = new ControladorPromocoes();
        }
        return instance;
    }
    //end of singleton


    public void inserirPromocao(Promocao promocao) {
        repPromocoes.inserir(promocao);
    }

    public void atualizarPromocao(Promocao promocao, String titulo, int porcentagemDeDesconto, LocalDateTime dataDeExpiracao, boolean ativa) {

        if (promocao != null) {
            if (repPromocoes.existePromocao(promocao.getId())) {

                if (titulo.isEmpty() || promocao.getTitulo().equals(titulo)) {
                    titulo = promocao.getTitulo();
                }

                if (porcentagemDeDesconto < 0 || porcentagemDeDesconto > 100 || promocao.getPorcentagemDeDesconto() == porcentagemDeDesconto) {
                    porcentagemDeDesconto = promocao.getPorcentagemDeDesconto();

                }

                if (dataDeExpiracao.isBefore(LocalDateTime.now())) {
                    dataDeExpiracao = (promocao).getDataDeExpiracao();
                }
                repPromocoes.atualizar(promocao, titulo, porcentagemDeDesconto, dataDeExpiracao, ativa);

            }else{
                //exeption promocao nao existe
            }
        }else{
                //exeption promocao nula
        }
    }

    public void deletarPromocao(Promocao promocao){
        repPromocoes.deletar(promocao);
    }

    public List<Promocao> listarTodasPromocoes(){
        return repPromocoes.listarTodasPromocoes();
    }

    public boolean existePromocao(String id){
        return repPromocoes.existePromocao(id);
    }

    public Promocao buscarPromocao(String id){
        return repPromocoes.buscarPromocao(id);
    }

    public String gerarId(){
        return repPromocoes.gerarId();
    }
}
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
        if(promocao != null){
            if(!repPromocoes.existePromocao(promocao.getId())){
                if (!promocao.getTitulo().isEmpty() && promocao.getPorcentagemDeDesconto() >= 0 &&
                        promocao.getPorcentagemDeDesconto() <= 100 && promocao.getDataDeExpiracao().isAfter(LocalDateTime.now())){
                    do{
                        promocao.setId(repPromocoes.gerarId());
                    }while (repPromocoes.existePromocao(promocao.getId()));
                    repPromocoes.inserir(promocao);
                }

            }else{
                // Promoção já existe
            }
        }else{
            // Promoção nula
        }

    }

    public void atualizarPromocao(Promocao promocao, String titulo, int porcentagemDeDesconto, int qtdMinimaDeDiarias, int qtdMinimaDeLocacoes, LocalDateTime dataDeExpiracao, boolean ativa) {

        if (promocao != null) {
            if (repPromocoes.existePromocao(promocao.getId())) {

                if (titulo.isEmpty() || promocao.getTitulo().equals(titulo)) {
                    titulo = promocao.getTitulo();
                }

                if (porcentagemDeDesconto < 0 || porcentagemDeDesconto > 100 || promocao.getPorcentagemDeDesconto() == porcentagemDeDesconto) {
                    porcentagemDeDesconto = promocao.getPorcentagemDeDesconto();

                }

                if (dataDeExpiracao.isBefore(LocalDateTime.now())) {
                    dataDeExpiracao = promocao.getDataDeExpiracao();
                }
                if(qtdMinimaDeDiarias <= 0){
                    qtdMinimaDeDiarias = promocao.getQtdMinimaDeDiarias();
                }
                if(qtdMinimaDeLocacoes <= 0){
                    qtdMinimaDeLocacoes = promocao.getQtdMinimaDeLocacoes();
                }
                repPromocoes.atualizar(promocao, titulo, porcentagemDeDesconto, qtdMinimaDeDiarias, qtdMinimaDeLocacoes, dataDeExpiracao, ativa);

            }else{
                //exeption promocao nao existe
            }
        }else{
            //exeption promocao nula
        }
    }

    public void deletarPromocao(Promocao promocao){
        if(promocao != null){
            if (repPromocoes.existePromocao(promocao.getId())) {
                repPromocoes.deletar(promocao);
            }else{
                //Levantar exeception promocao ! existe
            }
        }else{
            //Levantar exception promocao nula
        }
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
package data;

import models.Cliente;
import models.Locacao;
import models.Promocao;
import models.Veiculo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RepositorioLocacoes implements IRepositorioLocacoes{

    private static IRepositorioLocacoes instance;
    private ArrayList<Locacao> locacoes;

    public RepositorioLocacoes() {
        this.locacoes = new ArrayList<>();
    }

    public static IRepositorioLocacoes getInstance(){
        if (instance == null) {
            instance = new RepositorioLocacoes();
        }
        return instance;
    }

    @Override
    public void inserir(Locacao locacao) {
        locacao.getVeiculo().setDisponivel(false);
        locacoes.add(locacao);
    }

    @Override
    public void atualizar(Locacao locacao, Veiculo veiculo, Cliente cliente, Promocao promocao, LocalDateTime dataHoraDaLocacao, int qtdDeDiarias, boolean carroDevolvido) {
        locacao.setVeiculo(veiculo);
        locacao.setCliente(cliente);
        locacao.setPromocao(promocao);
        locacao.setDataHoraDaLocacao(dataHoraDaLocacao);
        locacao.setQtdDeDiarias(qtdDeDiarias);
        locacao.gerarValorDaLocacao();
        locacao.setCarroDevolvido(carroDevolvido);
    }

    @Override
    public void deletar(Locacao locacao) {
        locacoes.remove(locacao);
    }

    @Override
    public List<Locacao> listarTodasLocacoes() {
        return locacoes;
    }

    @Override
    public boolean existeLocacao(String id) {
        boolean existe = false;
        for(Locacao locacao : locacoes){
            if(locacao.getId().equals(id)){
                existe = true;
            }
        }
        return existe;
    }

    @Override
    public Locacao buscarUsuario(String id) {
        Locacao locacaoAux = null;
        for(Locacao locacao : locacoes){
            if(locacao.getId().equals(id)){
                locacaoAux = locacao;
            }
        }
        return locacaoAux;
    }

    public String gerarId() {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(caracteres.length());
            char caractere = caracteres.charAt(index);
            sb.append(caractere);
        }

        return sb.toString();
    }

}

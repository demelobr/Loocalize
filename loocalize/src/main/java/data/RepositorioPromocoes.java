package data;

import models.Promocao;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RepositorioPromocoes implements IRepositorioPromocoes {

    private static IRepositorioPromocoes instance;
    private ArrayList<Promocao> promocoes;

    public RepositorioPromocoes() {
        this.promocoes = new ArrayList<>();
    }

    public static IRepositorioPromocoes getInstance(){
        if (instance == null) {
            instance = new RepositorioPromocoes();
        }
        return instance;
    }

    @Override
    public void inserir(Promocao promocao) {
        promocoes.add(promocao);
    }

    @Override
    public void atualizar(Promocao promocao, String titulo, int porcentagemDeDesconto, int qtdMinimaDeDiarias, int qtdMinimaDeLocacoes, LocalDateTime dataDeExpiracao, boolean ativa) {
        promocao.setTitulo(titulo);
        promocao.setPorcentagemDeDesconto(porcentagemDeDesconto);
        promocao.setQtdMinimaDeDiarias(qtdMinimaDeDiarias);
        promocao.setQtdMinimaDeLocacoes(qtdMinimaDeLocacoes);
        promocao.setDataDeExpiracao(dataDeExpiracao);
        promocao.setAtiva(ativa);
    }

    @Override
    public void deletar(Promocao promocao) {
        promocoes.remove(promocao);
    }

    @Override
    public List<Promocao> listarTodasPromocoes() {
        return promocoes;
    }

    @Override
    public boolean existePromocao(String id) {
        boolean existe = false;
        for(Promocao promocao : promocoes){
            if(promocao.getId().equals(id)) {
                existe = true;
            }
        }
        return existe;
    }

    @Override
    public Promocao buscarPromocao(String id) {
        Promocao prom = null;
        for(Promocao promocao : promocoes){
            if(promocao.getId().equals(id)) {
                prom = promocao;
            }
        }
        return prom;
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
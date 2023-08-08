package data;

import models.Promocao;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    public void atualizar(Promocao promocao, String titulo, int porcentagemDeDesconto, LocalDateTime dataDeExpiracao, boolean ativa) {
        promocao.setTitulo(titulo);
        promocao.setPorcentagemDeDesconto(porcentagemDeDesconto);
        promocao.setDataDeExpiracao(dataDeExpiracao);
        promocao.setAtiva(ativa);

    }

    @Override
    public void deletar(Promocao promocao) {
        promocoes.remove(promocao);
    }

    @Override
    public List<Promocao> listarTodasPromocoes() {
        return null;
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
}

package data;


import models.Promocao;

import java.time.LocalDate;
import java.util.List;

public interface IRepositorioPromocoes {

    void inserir(Promocao promocao);

    void atualizar(Promocao promocao, String titulo, int porcentagemDeDesconto, int qtdMinimaDeDiarias, int qtdMinimaDeLocacoes, LocalDate dataDeExpiracao, boolean ativa);

    void deletar(Promocao promocao);

    List<Promocao> listarTodasPromocoes();

    List<Promocao> listarTodasPromocoesAtivas();

    boolean existePromocao(String id);

    Promocao buscarPromocao(String id);

    String gerarId();

}
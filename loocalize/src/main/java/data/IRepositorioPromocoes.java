package data;


import models.Promocao;

import java.time.LocalDateTime;
import java.util.List;

public interface IRepositorioPromocoes {

    void inserir(Promocao promocao);

    void atualizar(Promocao promocao, String titulo, int porcentagemDeDesconto, int qtdMinimaDeDiarias, int qtdMinimaDeLocacoes, LocalDateTime dataDeExpiracao, boolean ativa);

    void deletar(Promocao promocao);

    List<Promocao> listarTodasPromocoes();

    boolean existePromocao(String id);

    Promocao buscarPromocao(String id);

    String gerarId();

}
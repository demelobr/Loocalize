package business;
import exception.*;
import models.Promocao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IControladorPromocoes {

    void inserirPromocao (Promocao promocao) throws PromocaoExisteException, PromocaoNulaException, PromocaoInseridaComSucessoException;

    void atualizarPromocao(Promocao promocao, String titulo, int porcentagemDeDesconto, int qtdMinimaDeDiarias, int qtdMinimaDeLocacoes, LocalDate dataDeExpiracao, boolean ativa) throws PromocaoNulaException, PromocaoNaoExisteException, PromocaoEditadaComSucessoException;

    public void deletarPromocao(Promocao promocao);

    public List<Promocao> listarTodasPromocoes();

    public boolean existePromocao(String id);

    public Promocao buscarPromocao(String id);

    public String gerarId();
}
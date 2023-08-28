package business;
import exception.*;
import models.Promocao;
import models.Usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IControladorPromocoes {

    void inserirPromocao (Promocao promocao) throws PromocaoExisteException, PromocaoNulaException, PromocaoInseridaComSucessoException;

    void atualizarPromocao(Promocao promocao, String titulo, int porcentagemDeDesconto, int qtdMinimaDeDiarias, int qtdMinimaDeLocacoes, LocalDate dataDeExpiracao, boolean ativa) throws PromocaoNulaException, PromocaoNaoExisteException, PromocaoEditadaComSucessoException;

    void deletarPromocao(Promocao promocao);

    List<Promocao> listarTodasPromocoes();

    boolean existePromocao(String id);

    Promocao buscarPromocao(String id);

    Promocao buscarPorPromocaoAplicavel(Usuario usuario, int qtdDeDiarias);


    public String gerarId();
}
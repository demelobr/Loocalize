package business;

import exception.LocacaoFeitaComSucessoException;
import models.Cliente;
import models.Locacao;
import models.Promocao;
import models.Veiculo;

import java.time.LocalDateTime;
import java.util.List;

public interface IControladorLocacoes {
    void inserirLocacao(Locacao locacao) throws LocacaoFeitaComSucessoException;

    void atualizarLocacao(Locacao locacao, Veiculo veiculo, Cliente cliente, Promocao promocao, LocalDateTime dataHoraDaLocacao, int qtdDeDiarias, boolean carroDevolvido);

    void deletarLocacao(Locacao locacao);

    void aplicarPromocao(Locacao locacao);

    List<Locacao> listarTodasLocacoes();

    boolean existeLocacao(String id);

    Locacao buscarLocacao(String id);

    String gerarId();
}

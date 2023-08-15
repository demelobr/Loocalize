package data;

import models.Cliente;
import models.Locacao;
import models.Promocao;
import models.Veiculo;

import java.time.LocalDateTime;
import java.util.List;

public interface IRepositorioLocacoes {

    void inserir(Locacao locacao);

    void atualizar(Locacao locacao, Veiculo veiculo, Cliente cliente, Promocao promocao, LocalDateTime dataHoraDaLocacao, int qtdDeDiarias, boolean carroDevolvido);

    void deletar(Locacao locacao);

    List<Locacao> listarTodasLocacoes();

    boolean existeLocacao(String id);

    Locacao buscarLocacao(String id);

    String gerarId();

}

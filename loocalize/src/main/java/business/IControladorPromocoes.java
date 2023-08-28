package business;
import models.Promocao;

import java.time.LocalDateTime;
import java.util.List;

public interface IControladorPromocoes {

    void inserirPromocao (Promocao promocao);

    void atualizarPromocao(Promocao promocao, String titulo, int porcentagemDeDesconto, int qtdMinimaDeDiarias, int qtdMinimaDeLocacoes, LocalDateTime dataDeExpiracao, boolean ativa);

    public void deletarPromocao(Promocao promocao);

    public List<Promocao> listarTodasPromocoes();

    public boolean existePromocao(String id);

    public Promocao buscarPromocao(String id);

    public String gerarId();
}
package data;

import models.Veiculo;

import java.time.Year;
import java.util.List;

public interface IRepositorioVeiculos {

    void inserir(Veiculo veiculo);

    void atualizar(Veiculo veiculo, String modelo, String marca, String placa, Year ano, int quilometragem, int qtdDeLocacoes, double valorPorKmRodado, String fotoDoVeiculo, boolean disponivel);

    void deletar(Veiculo veiculo);

    List<Veiculo> listarTodosVeiculos();

    boolean existeVeiculo(String id);

    Veiculo buscarVeiculo(String id);

    String gerarId();

}

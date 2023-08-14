package business;

import models.Veiculo;

import java.time.Year;
import java.util.List;

public interface IControladorVeiculos{

    //Métodos
    //Cadastra Veículos
    void inserirVeiculo(Veiculo veiculo);

    //Atualiza veículo
    void atualizarVeiculo(Veiculo veiculo, String novoModelo, String novaMarca, String novaPlaca, Year novoAno, int novaQuilometragem, int novaQtdDeLocacoes, double novoValorPorKmRodado, String novaFotoDoVeiculo, boolean novoDisponivel);

    //Exclui veículo
    void deletarVeiculo(Veiculo veiculo);

    //Lista todos os veículos
    List<Veiculo> listarTodosVeiculos();

    //Busca por id para os colaboradores usarem
    Veiculo buscarVeiculo(String id);

    //Busca por criterios
    List<Veiculo> buscarVeiculosAvancado(String modelo, Year ano, String marca, int quilometragem, boolean disponivel);
}

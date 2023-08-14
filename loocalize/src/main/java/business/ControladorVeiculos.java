package business;

import data.IRepositorioVeiculos;
import data.RepositorioVeiculos;
import models.Veiculo;

import java.time.Year;
import java.util.List;

public class ControladorVeiculos implements IControladorVeiculos{

    //Singleton
    private static ControladorVeiculos instance;
    private IRepositorioVeiculos repositorioVeiculos;

    public ControladorVeiculos (){
        this.repositorioVeiculos = RepositorioVeiculos.getInstance();
    }

    private ControladorVeiculos getInstance(){
        if (instance == null){
            instance = new ControladorVeiculos();
        }
        return instance;
    }

    //Métodos
    //Cadastrar Veículos
    @Override
    public void inserirVeiculo(Veiculo veiculo){
        if (veiculo.getModelo().isEmpty() || veiculo.getMarca().isEmpty() || veiculo.getId().isEmpty() || veiculo.getAno() == null || veiculo.getFotoDoVeiculo().isEmpty()) {
            //throw new IllegalArgumentException("Todos os campos obrigatórios devem ser preenchidos.");
        }
        if (veiculo.getQuilometragem() < 0 || veiculo.getValorDaDiaria() < 0) {
            //throw new IllegalArgumentException("Valores negativos não são permitidos para quilometragem ou valor por km rodado.");
        }
        if (!repositorioVeiculos.verificarPlacaExistente(veiculo.getPlaca())){
            veiculo.setId(repositorioVeiculos.gerarId());
            repositorioVeiculos.inserir(veiculo);
        } else {
            //throw new IllegalArgumentException("Já existe um veículo com essa placa. Tente novamente...");
        }
    }

    //Atualiza veículo
    @Override
    public void atualizarVeiculo(Veiculo veiculo, String novoModelo, String novaMarca, String novaPlaca, Year novoAno, int novaQuilometragem, int novaQtdDeLocacoes, double novoValorDaDiaria, String novaFotoDoVeiculo, boolean novoDisponivel){
        if (repositorioVeiculos.existeVeiculo(veiculo.getId())) {
            if (novoModelo.isEmpty() || veiculo.getModelo().equals(novoModelo)) {
                novoModelo = veiculo.getModelo();
            }
            if (novaMarca.isEmpty() || veiculo.getMarca().equals(novaMarca)) {
                novaMarca = veiculo.getMarca();
            }
            if (novaPlaca.isEmpty() || veiculo.getPlaca().equals(novaPlaca)) {
                novaPlaca = veiculo.getPlaca();
            }
            if (novoAno == null || veiculo.getAno().compareTo(novoAno) == 0) {
                novoAno = veiculo.getAno();
            }
            if (novaQuilometragem < 0 || veiculo.getQuilometragem() == novaQuilometragem) {
                novaQuilometragem = veiculo.getQuilometragem();
            }
            if (novaFotoDoVeiculo.isEmpty() || veiculo.getFotoDoVeiculo().equals(novaFotoDoVeiculo)) {
                novaFotoDoVeiculo = veiculo.getFotoDoVeiculo();
            }
            if (veiculo.isDisponivel() == novoDisponivel) {
                novoDisponivel = veiculo.isDisponivel();
            }
            repositorioVeiculos.atualizar(veiculo, novoModelo, novaMarca, novaPlaca, novoAno, novaQuilometragem, novaQtdDeLocacoes, novoValorDaDiaria, novaFotoDoVeiculo, novoDisponivel);
        }else{
            //Levantar exceção de que o veiculo não existe
        }
    }

    //Exclui veículo
    @Override
    public void deletarVeiculo(Veiculo veiculo) {
        if (veiculo != null){
            if (repositorioVeiculos.existeVeiculo(veiculo.getId())) {
                repositorioVeiculos.deletar(veiculo);
            } else {
                //Exceção que veículo não existe
            }
        }else{
            //Exceção de que o veículo é null
        }
    }

    //Lista todos os veículos
    @Override
    public List<Veiculo> listarTodosVeiculos(){
        return repositorioVeiculos.listarTodosVeiculos();
    }

    //Busca por id para os colaboradores usarem:
    @Override
    public Veiculo buscarVeiculo(String id) {
        return repositorioVeiculos.buscarVeiculo(id);
    }

    //Busca por criterios para os clientes usarem:
    @Override
    public List<Veiculo> buscarVeiculosAvancado(String modelo, Year ano, String marca, int quilometragem, boolean disponivel) {
        return repositorioVeiculos.buscarVeiculosAvancado(modelo, ano, marca, quilometragem, disponivel);
    }

}
package business;

import data.IRepositorioVeiculos;
import data.RepositorioVeiculos;
import exception.*;
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

    public static ControladorVeiculos getInstance(){
        if(instance == null){
            instance = new ControladorVeiculos();
        }
        return instance;
    }

    //Métodos
    //Cadastrar Veículos
    @Override
    public void inserirVeiculo(Veiculo veiculo) throws CampoVazioException, QuilometragemInvalidaException, ValorDaDiariaInvalidaException, VeiculoExisteException, VeiculoInseridoComSucessoException {
        if (veiculo.getModelo().isEmpty() || veiculo.getMarca().isEmpty() || veiculo.getAno() == null || veiculo.getFotoDoVeiculo().isEmpty()) {
            throw new CampoVazioException();
        }
        if (veiculo.getQuilometragem() < 0) {
            throw new QuilometragemInvalidaException();
        }
        if(veiculo.getValorDaDiaria() < 0){
            throw new ValorDaDiariaInvalidaException();
        }
        if (!repositorioVeiculos.verificarPlacaExistente(veiculo.getPlaca())){
            do{
                veiculo.setId(repositorioVeiculos.gerarId());
            }while (repositorioVeiculos.existeVeiculo(veiculo.getId()));
            System.out.println(veiculo);
            repositorioVeiculos.inserir(veiculo);
            throw new VeiculoInseridoComSucessoException();
        } else {
            throw new VeiculoExisteException(veiculo.getId());
        }
    }

    //Atualiza veículo
    @Override
    public void atualizarVeiculo(Veiculo veiculo, String novoModelo, String novaMarca, String novaPlaca, Year novoAno, int novaQuilometragem, int novaQtdDeLocacoes, double novoValorDaDiaria, String novaFotoDoVeiculo, boolean novoDisponivel) throws VeiculoNaoExisteException, VeiculoEditadoComSucessoException {
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
            throw new VeiculoEditadoComSucessoException();
        }else{
            throw new VeiculoNaoExisteException(veiculo.getPlaca());
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

    @Override
    public void setarVeiculoComoAlugado(Veiculo veiculo, boolean disponibilidade) throws VeiculoNaoExisteException, VeiculoEditadoComSucessoException {
        this.atualizarVeiculo(veiculo, veiculo.getModelo(), veiculo.getMarca(), veiculo.getPlaca(), veiculo.getAno(), veiculo.getQuilometragem(), veiculo.getQtdDeLocacao(), veiculo.getValorDaDiaria(), veiculo.getFotoDoVeiculo(), disponibilidade);
    }

    //Lista todos os veículos
    @Override
    public List<Veiculo> listarTodosVeiculos(){
        return repositorioVeiculos.listarTodosVeiculos();
    }

    @Override
    public boolean existeVeiculo(String id) {
        return repositorioVeiculos.existeVeiculo(id);
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
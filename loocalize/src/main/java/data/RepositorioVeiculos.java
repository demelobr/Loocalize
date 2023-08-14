package data;

import models.Veiculo;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RepositorioVeiculos implements IRepositorioVeiculos {

    private static IRepositorioVeiculos instance;
    private ArrayList<Veiculo> veiculos;

    public RepositorioVeiculos() {
        this.veiculos = new ArrayList<>();
    }

    public static IRepositorioVeiculos getInstance(){
        if (instance == null) {
            instance = new RepositorioVeiculos();
        }
        return instance;
    }

    @Override
    public void inserir(Veiculo veiculo) {
        veiculos.add(veiculo);
    }

    @Override
    public void atualizar(Veiculo veiculo, String modelo, String marca, String placa, Year ano, int quilometragem, int qtdDeLocacoes, double valorDaDiaria, String fotoDoVeiculo, boolean disponivel) {
        veiculo.setModelo(modelo);
        veiculo.setMarca(marca);
        veiculo.setPlaca(placa);
        veiculo.setAno(ano);
        veiculo.setQuilometragem(quilometragem);
        veiculo.setQtdDeLocacao(qtdDeLocacoes);
        veiculo.setValorDaDiaria(valorDaDiaria);
        veiculo.setFotoDoVeiculo(fotoDoVeiculo);
        veiculo.setDisponivel(disponivel);
    }

    @Override
    public void deletar(Veiculo veiculo) {
        veiculos.remove(veiculo);
    }

    @Override
    public List<Veiculo> listarTodosVeiculos() {
        return veiculos;
    }

    @Override
    public  List<Veiculo> listarVeiculosDisponiveis() {
        ArrayList<Veiculo> listaDeVeiculosDisponiveis = new ArrayList<>();
        for(Veiculo veiculo : veiculos){
            if(veiculo.isDisponivel()){
                listaDeVeiculosDisponiveis.add(veiculo);
            }
        }
        return listaDeVeiculosDisponiveis;
    }

    //Método de verificar se a placa existe
    @Override
    public boolean verificarPlacaExistente(String placa) {
        boolean verifica = false;
        for(Veiculo veiculo : veiculos){
            if(veiculo.getPlaca().equals(placa)){
                verifica = true;
            }
        }
        return verifica;
    }

    @Override
    public boolean existeVeiculo(String id) {
        boolean existe = false;
        for(Veiculo veiculo : veiculos){
            if(veiculo.getId().equals(id)){
                existe = true;
            }
        }
        return existe;
    }

    @Override
    public Veiculo buscarVeiculo(String id) {
        Veiculo veiculoAux = null;
        for(Veiculo veiculo : veiculos){
            if(veiculo.getId().equals(id)){
                veiculoAux = veiculo;
            }
        }
        return veiculoAux;
    }

    //Busca que pode ser realizada pelos clientes para facilitar a procura ao carro.
    @Override
    public List<Veiculo> buscarVeiculosAvancado(String modelo, Year ano, String marca, int quilometragem, boolean disponivel) {
        List<Veiculo> veiculosEncontrados = new ArrayList<>();

        for (Veiculo veiculo : veiculos) {
            if (veiculo.getModelo().equalsIgnoreCase(modelo) &&
                    veiculo.getAno().compareTo(ano) == 0 &&
                    veiculo.getMarca().equalsIgnoreCase(marca) &&
                    veiculo.getQuilometragem() == quilometragem &&
                    veiculo.isDisponivel() == disponivel) {
                veiculosEncontrados.add(veiculo);
            }
        }
        return veiculosEncontrados;
    }

    public String gerarId() {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(caracteres.length());
            char caractere = caracteres.charAt(index);
            sb.append(caractere);
        }

        return sb.toString();
    }

}

package business;

import exception.*;
import models.Colaborador;
import models.Promocao;
import models.Usuario;
import models.Veiculo;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Year;
import java.util.List;

public class ServidorLoocalize {
    private static ServidorLoocalize instance;
    private IControladorUsuarios controladorUsuarios;
    private IControladorVeiculos controladorVeiculos;
    private IControladorPromocoes controladorPromocoes;
    private IControladorLocacoes controladorLocacoes;

    public ServidorLoocalize(){
        this.controladorUsuarios = ControladorUsuarios.getInstance();
        this.controladorVeiculos = ControladorVeiculos.getInstance();
        this.controladorPromocoes = ControladorPromocoes.getInstance();
        this.controladorLocacoes = ControladorLocacoes.getInstance();
    }

    public static ServidorLoocalize getInstance(){
        if(instance == null){
            instance = new ServidorLoocalize();
        }
        return instance;
    }

    public void criarAdm(){
        if(!controladorUsuarios.existeUsuario("admin")){
            try {
//                controladorUsuarios.inserirUsuario((Usuario) new Colaborador("demelobr", "12345", "demelobr@gmail.com", "Bruno Melo", "123.456.789-09", LocalDate.of(1997, 2, 24), "(81) 99999-9999", "Rua Fictícia", true));
                controladorUsuarios.inserirUsuario((Usuario) new Colaborador("demelobr", "12345", "demelobr@gmail.com", "Bruno Melo", "123.456.789-09", LocalDate.of(1997, 2, 24), "(81) 99999-9999", "Rua Fictícia", false));
            } catch (UsuarioNuloException e) {
                throw new RuntimeException(e);
            } catch (UsuarioExisteException e) {
                throw new RuntimeException(e);
            } catch (CampoVazioException e) {
                throw new RuntimeException(e);
            } catch (DataDeNascimentoInvalidaException e) {
                throw new RuntimeException(e);
            } catch (UsuarioMenorDeIdadeException e) {
                throw new RuntimeException(e);
            } catch (AnosDeHabilitacaoInsuficientesException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void checarLogin(String usuario, String senha) throws IOException, UsuarioNaoExisteException, UsuarioLogadoComSucessoException, LoginIncorretoException {
        controladorUsuarios.checarLogin(usuario, senha);
    }

    public void checarDadosDaNovaConta(String usuario, String email, String senha, String senhaRepetida) throws UsuarioExisteException, EmailInvalidoException, SenhasDiferentesException {
        controladorUsuarios.checarDadosDaNovaConta(usuario, email, senha, senhaRepetida);
    }

    public void checarDadosPessoais(String nome, LocalDate dataDeNascimento, String telefone, String endereco, String cpf, String cnh, LocalDate dataDeHabilitacao) throws TelefoneInvalidoException, DataDeHabilitacaoInvalidaException, CpfInvalidoException, DataDeNascimentoInvalidaException, AnosDeHabilitacaoInsuficientesException, UsuarioExisteException, CampoVazioException, UsuarioNuloException, UsuarioMenorDeIdadeException {
        controladorUsuarios.checarDadosPessoais(nome, dataDeNascimento, telefone, endereco, cpf, cnh, dataDeHabilitacao);
    }

    public void inserirVeiculo(Veiculo veiculo) throws VeiculoExisteException, CampoVazioException, ValorDaDiariaInvalidaException, QuilometragemInvalidaException, VeiculoInseridoComSucessoException {
        controladorVeiculos.inserirVeiculo(veiculo);
    }

    public void editarVeiculo(Veiculo veiculo, String novoModelo, String novaMarca, String novaPlaca, Year novoAno, int novaQuilometragem, int novaQtdDeLocacoes, double novoValorDaDiaria, String novaFotoDoVeiculo, boolean novoDisponivel) throws VeiculoNaoExisteException, VeiculoEditadoComSucessoException {
        controladorVeiculos.atualizarVeiculo(veiculo, novoModelo, novaMarca, novaPlaca, novoAno, novaQuilometragem, novaQtdDeLocacoes, novoValorDaDiaria, novaFotoDoVeiculo, novoDisponivel);
    }

    public void deletarVeiculo(Veiculo veiculo){
        controladorVeiculos.deletarVeiculo(veiculo);
    }

    public List<Veiculo> listarTodosVeiculos(){
        return controladorVeiculos.listarTodosVeiculos();
    }

    public void inserirPromocao(Promocao promocao) throws PromocaoNulaException, PromocaoExisteException, PromocaoInseridaComSucessoException {
        controladorPromocoes.inserirPromocao(promocao);
    }

    public void editarPromocao(Promocao promocao, String titulo, int porcentagemDeDesconto, int qtdMinimaDeDiarias, int qtdMinimaDeLocacoes, LocalDate dataDeExpiracao, boolean ativa) throws PromocaoNulaException, PromocaoNaoExisteException, PromocaoEditadaComSucessoException {
        controladorPromocoes.atualizarPromocao(promocao, titulo, porcentagemDeDesconto, qtdMinimaDeDiarias, qtdMinimaDeLocacoes, dataDeExpiracao, ativa);
    }

    public void deletarPromocao(Promocao promocao){
        controladorPromocoes.deletarPromocao(promocao);
    }

    public List<Promocao> listarTodasPromocoes(){
        return controladorPromocoes.listarTodasPromocoes();
    }

}

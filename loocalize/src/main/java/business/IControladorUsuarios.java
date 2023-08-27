package business;

import exception.*;
import models.Cliente;
import models.Colaborador;
import models.Usuario;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface IControladorUsuarios {
    void inserirUsuario(Usuario usuario) throws UsuarioNuloException, UsuarioExisteException, CampoVazioException, DataDeNascimentoInvalidaException, UsuarioMenorDeIdadeException, AnosDeHabilitacaoInsuficientesException, UsuarioInseridoComSucessoException;

    void atualizarColaborador(Usuario usuario, String user, String senha, String email, String nomeCompleto, String cpf, LocalDate dataDeNascimento, String telefone, String endereco) throws UsuarioNaoExisteException, UsuarioNuloException, UsuarioEditadoComSucessoException;

    void atualizarCliente(Usuario usuario, String user, String senha, String email, String nome, String cpf, LocalDate dataDeNascimento, String telefone, String endereco, String cnh, LocalDate dataDeHabilitacao, int qntDeLocacoes) throws UsuarioNaoExisteException, UsuarioNuloException;

    void deletarUsuario(Usuario usuario) throws UsuarioNaoExisteException, UsuarioNuloException;

    void checarLogin(String usuario, String senha) throws IOException, UsuarioNaoExisteException, UsuarioLogadoComSucessoException, LoginIncorretoException;

    void checarDadosDaNovaConta(String usuario, String email, String senha, String senhaRepetida) throws UsuarioExisteException, EmailInvalidoException, SenhasDiferentesException;

    void checarDadosPessoais(String nome, LocalDate dataDeNascimento, String telefone, String endereco, String cpf, String cnh, LocalDate dataDeHabilitacao) throws CpfInvalidoException, TelefoneInvalidoException, DataDeHabilitacaoInvalidaException, DataDeNascimentoInvalidaException, AnosDeHabilitacaoInsuficientesException, UsuarioExisteException, CampoVazioException, UsuarioNuloException, UsuarioMenorDeIdadeException, UsuarioInseridoComSucessoException;

    List<Usuario> listarTodosUsuarios();

    List<Colaborador> listarColaboradores();

    List<Usuario> listarAdministradores();

    List<Cliente> listarClientes();

    boolean existeUsuario(String usuario);

    Usuario buscarUsuario(String usuario);

    boolean emailValido(String email);

    boolean telefoneValido(String telefone);

    boolean cpfValido(String cpf);

    String gerarId();

}

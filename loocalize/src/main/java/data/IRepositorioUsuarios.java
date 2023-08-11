package data;

import models.Usuario;

import java.time.LocalDate;
import java.util.List;

public interface IRepositorioUsuarios {

    void inserir(Usuario usuario);

    void atualizarColaborador(Usuario usuario, String user, String senha, String email, String nomeCompleto, String cpf, LocalDate dataDeNascimento, String telefone, String endereco);

    void atualizarCliente(Usuario usuario, String user, String senha, String email, String nome, String cpf, LocalDate dataDeNascimento, String telefone, String endereco, String cnh, LocalDate dataDeHabilitacao, int qntDeLocacoes);

    void deletar(Usuario usuario);

    List<Usuario> listarTodosUsuarios();

    boolean existeUsuario(String usuario);

    Usuario buscarUsuario(String usuario);

    String gerarId();
}

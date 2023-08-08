package data;

import models.Usuario;
import java.util.List;

public interface IRepositorioUsuarios {

    void inserir(Usuario usuario);

    void atualizar(Usuario usuario);

    void deletar(Usuario usuario);

    List<Usuario> listarTodosUsuarios();

    boolean existeUsuario(String usuario);

    Usuario buscarUsuario(String usuario);
}

package data;

import models.Cliente;
import models.Colaborador;
import models.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RepositorioUsuarios implements IRepositorioUsuarios{

    private static IRepositorioUsuarios instance;
    private ArrayList<Usuario> usuarios;

    public RepositorioUsuarios() {
        this.usuarios = new ArrayList<>();
    }

    public static IRepositorioUsuarios getInstance(){
        if (instance == null) {
            instance = new RepositorioUsuarios();
        }
        return instance;
    }

    @Override
    public void inserir(Usuario usuario) {
        usuarios.add(usuario);
    }

    @Override
    public void atualizarColaborador(Usuario usuario, String user, String senha, String email, String nomeCompleto, String cpf, LocalDate dataDeNascimento, String telefone, String endereco) {
        usuario.setUsuario(user);
        usuario.setSenha(senha);
        usuario.setEmail(email);
        ((Colaborador) usuario).setNomeCompleto(nomeCompleto);
        ((Colaborador) usuario).setCpf(cpf);
        ((Colaborador) usuario).setDataNascimento(dataDeNascimento);
        ((Colaborador) usuario).setTelefone(telefone);
        ((Colaborador) usuario).setEndereco(endereco);
    }

    @Override
    public void atualizarCliente(Usuario usuario, String user, String senha, String email, String nome, String cpf, LocalDate dataDeNascimento, String telefone, String endereco, String cnh, LocalDate dataDeHabilitacao, int qntDeLocacoes) {
        usuario.setUsuario(user);
        usuario.setSenha(senha);
        usuario.setEmail(email);
        ((Cliente) usuario).setNome(nome);
        ((Cliente) usuario).setCpf(cpf);
        ((Cliente) usuario).setDataNascimento(dataDeNascimento);
        ((Cliente) usuario).setTelefone(telefone);
        ((Cliente) usuario).setEndereco(endereco);
        ((Cliente) usuario).setCnh(cnh);
        ((Cliente) usuario).setDataDeHabilitacao(dataDeHabilitacao);
        ((Cliente) usuario).setQtdDeLocacoes(qntDeLocacoes);
    }

    @Override
    public void deletar(Usuario usuario) {
        usuarios.remove(usuario);
    }

    @Override
    public List<Usuario> listarTodosUsuarios() {
        return usuarios;
    }

    @Override
    public boolean existeUsuario(String usuario) {
        boolean existe = false;
        for(Usuario user : usuarios){
            if(user.getUsuario().equals(usuario)){
                existe = true;
            }
        }
        return existe;
    }

    @Override
    public Usuario buscarUsuario(String usuario) {
        Usuario usuarioAux = null;
        for(Usuario user : usuarios){
            if(user.getUsuario().equals(usuario)){
                usuarioAux = user;
            }
        }
        return usuarioAux;
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

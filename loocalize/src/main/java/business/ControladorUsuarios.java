package business;

import data.IRepositorioUsuarios;
import data.RepositorioUsuarios;
import models.Cliente;
import models.Colaborador;
import models.Usuario;

import java.time.LocalDate;
import java.util.List;

public class ControladorUsuarios implements IControladorUsuarios{
    private static ControladorUsuarios instance;
    private IRepositorioUsuarios repUsuarios;

    public ControladorUsuarios(){
        this.repUsuarios = RepositorioUsuarios.getInstance();
    }

    public static ControladorUsuarios getInstance(){
        if(instance == null){
            instance = new ControladorUsuarios();
        }
        return instance;
    }

    @Override
    public void cadastrarUsuario(Usuario usuario) {
        if(usuario != null){
            if(!repUsuarios.existeUsuario(usuario.getUsuario())){
                if(usuario instanceof Colaborador){
                    if(!usuario.getUsuario().isEmpty() && !usuario.getEmail().isEmpty() && !usuario.getSenha().isEmpty()){
                        if(!((Colaborador) usuario).getNomeCompleto().isEmpty() && !((Colaborador) usuario).getCpf().isEmpty() &&
                           !((Colaborador) usuario).getTelefone().isEmpty() &&
                           !((Colaborador) usuario).getEndereco().isEmpty()){
                           if(!((Colaborador) usuario).getDataNascimento().isBefore(LocalDate.now())){
                               repUsuarios.inserir(usuario);
                           }else{
                               //levantar exception "data de nascimento inválida"
                           }
                        }else{
                            //levantar exception "todos os campos precisam ser preenchidos"
                        }
                    }else{
                        //levantar exception "todos os campos precisam ser preenchidos"
                    }
                }else if(usuario instanceof Cliente){
                    if(!usuario.getUsuario().isEmpty() && !usuario.getEmail().isEmpty() && !usuario.getSenha().isEmpty()){
                        if(!((Cliente) usuario).getNome().isEmpty() && !((Cliente) usuario).getCpf().isEmpty() &&
                           !((Cliente) usuario).getTelefone().isEmpty() && !((Cliente) usuario).getEndereco().isEmpty() && !((Cliente) usuario).getCnh().isEmpty() &&
                            ((Cliente) usuario).getQtdDeLocacoes() >= 0){
                            if(!((Cliente) usuario).getDataNascimento().isBefore(LocalDate.now())){
                                if(((Cliente) usuario).getIdade(((Cliente) usuario).getDataNascimento()) >= 18){
                                    if(((Cliente) usuario).getAnosDeHabilitacao(((Cliente) usuario).getDataDeHabilitacao()) >= 3){
                                        repUsuarios.inserir(usuario);
                                    }else{
                                        //levantar exception "usuário precisar ter pelo menos 3 anos de habilitação"
                                    }
                                }else{
                                    //levantar exception "usuário precisa ser maior de idade"
                                }
                            }else{
                                //levantar exception "data de nascimento inválida"
                            }
                        }else{
                            //levantar exception "todos os campos precisam ser preenchidos"
                        }
                    }else{
                        //levantar exception "todos os campos precisam ser preenchidos"
                    }
                }
            }else{
                //levantar exception "usuário já existe"
            }
        }else{
            //levantar exception "usuário nulo"
        }
    }

    @Override
    public void atualizarColaborador(Usuario usuario, String user, String senha, String email, String nomeCompleto, String cpf, LocalDate dataDeNascimento, String telefone, String endereco) {
        if(user.isEmpty() || usuario.getUsuario().equals(user)){
            user = usuario.getUsuario();
        }
        if(senha.isEmpty() || usuario.getSenha().equals(senha)){
            senha = usuario.getSenha();
        }
        if(email.isEmpty() || usuario.getEmail().equals(email)){
            email = usuario.getEmail();
        }
        if(nomeCompleto.isEmpty() || ((Colaborador) usuario).getNomeCompleto().equals(nomeCompleto)){
            nomeCompleto = ((Colaborador) usuario).getNomeCompleto();
        }
        if(cpf.isEmpty() || ((Colaborador) usuario).getCpf().equals(cpf)){
            cpf = ((Colaborador) usuario).getCpf();
        }
        if(dataDeNascimento.isAfter(LocalDate.now())){
            dataDeNascimento = ((Colaborador) usuario).getDataNascimento();
        }
        if(telefone.isEmpty() || ((Colaborador) usuario).getTelefone().equals(telefone)){
            telefone = ((Colaborador) usuario).getTelefone();
        }
        if(endereco.isEmpty() || ((Colaborador) usuario).getEndereco().equals(endereco)){
            endereco = ((Colaborador) usuario).getEndereco();
        }
        repUsuarios.atualizarColaborador(usuario, user, senha, email, nomeCompleto, cpf, dataDeNascimento, telefone, endereco);
    }

    @Override
    public void atualizarCliente(Usuario usuario, String user, String senha, String email, String nome, String cpf, LocalDate dataDeNascimento, String telefone, String endereco, String cnh, LocalDate dataDeHabilitacao, int qntDeLocacoes) {
        if(user.isEmpty() || usuario.getUsuario().equals(user)){
            user = usuario.getUsuario();
        }
        if(senha.isEmpty() || usuario.getSenha().equals(senha)){
            senha = usuario.getSenha();
        }
        if(email.isEmpty() || usuario.getEmail().equals(email)){
            email = usuario.getEmail();
        }
        if(nome.isEmpty() || ((Cliente) usuario).getNome().equals(nome)){
            nome = ((Cliente) usuario).getNome();
        }
        if(cpf.isEmpty() || ((Cliente) usuario).getCpf().equals(cpf)){
            cpf = ((Cliente) usuario).getCpf();
        }
        if(dataDeNascimento.isAfter(LocalDate.now()) || ((Cliente) usuario).getIdade(dataDeNascimento) < 18){
            dataDeNascimento = ((Cliente) usuario).getDataNascimento();
        }
        if(telefone.isEmpty() || ((Cliente) usuario).getTelefone().equals(telefone)){
            telefone = ((Cliente) usuario).getTelefone();
        }
        if(endereco.isEmpty() || ((Cliente) usuario).getEndereco().equals(endereco)){
            endereco = ((Cliente) usuario).getEndereco();
        }
        if(cnh.isEmpty() || ((Cliente) usuario).getCnh().equals(cnh)){
            cnh = ((Cliente) usuario).getCnh();
        }
        if(dataDeHabilitacao.isAfter(LocalDate.now()) || ((Cliente) usuario).getAnosDeHabilitacao(((Cliente) usuario).getDataDeHabilitacao()) < 3){
            dataDeHabilitacao = ((Cliente) usuario).getDataDeHabilitacao();
        }
        if(qntDeLocacoes < 0){
            qntDeLocacoes = ((Cliente) usuario).getQtdDeLocacoes();
        }
        repUsuarios.atualizarCliente(usuario, user, senha, email, nome, cpf, dataDeNascimento, telefone, endereco, cnh, dataDeHabilitacao, qntDeLocacoes);
    }

    @Override
    public void removerUsuario(Usuario usuario) {
        if(usuario != null){
            if(repUsuarios.existeUsuario(usuario.getUsuario())){
                repUsuarios.deletar(usuario);
            }else{
                //levantar exception "usuário não existe"
            }
        }else{
            //levantar exception "usuário nulo"
        }
    }

    @Override
    public List<Usuario> listarTodosUsuarios() {
        return repUsuarios.listarTodosUsuarios();
    }

    @Override
    public List<Usuario> listarColaboradores() {
        return repUsuarios.listarColaboradores();
    }

    @Override
    public List<Usuario> listarAdministradores() {
        return repUsuarios.listarAdministradores();
    }

    @Override
    public List<Usuario> listarClientes() {
        return repUsuarios.listarClientes();
    }

    @Override
    public boolean existeUsuario(String usuario) {
        return repUsuarios.existeUsuario(usuario);
    }

    @Override
    public Usuario buscarUsuario(String usuario) {
        return repUsuarios.buscarUsuario(usuario);
    }

    @Override
    public String gerarId() {
        return repUsuarios.gerarId();
    }
}

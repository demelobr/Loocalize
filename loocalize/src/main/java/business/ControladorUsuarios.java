package business;

import data.IRepositorioUsuarios;
import data.RepositorioUsuarios;
import exception.*;
import gui.CadastroClientePessoalController;
import models.Cliente;
import models.Colaborador;
import models.Usuario;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControladorUsuarios implements IControladorUsuarios{
    private static ControladorUsuarios instance;
    private IRepositorioUsuarios repUsuarios;
    private static final String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final Pattern patternEmail = Pattern.compile(emailRegex);
    private static final String telefoneRegex = "^\\([1-9]{2}\\) (?:[2-8]|9[1-9])[0-9]{3}\\-[0-9]{4}$";
    private static final Pattern patternTelefone = Pattern.compile(telefoneRegex);

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
    public void inserirUsuario(Usuario usuario) throws UsuarioNuloException, UsuarioExisteException, CampoVazioException, DataDeNascimentoInvalidaException, UsuarioMenorDeIdadeException, AnosDeHabilitacaoInsuficientesException {
        if(usuario != null){
            if(!repUsuarios.existeUsuario(usuario.getUsuario())){
                if(usuario instanceof Colaborador){
                    if(!usuario.getUsuario().isEmpty() && !usuario.getEmail().isEmpty() && !usuario.getSenha().isEmpty()){
                        if(!((Colaborador) usuario).getNomeCompleto().isEmpty() && !((Colaborador) usuario).getCpf().isEmpty() &&
                           !((Colaborador) usuario).getTelefone().isEmpty() &&
                           !((Colaborador) usuario).getEndereco().isEmpty()){
                           if(((Colaborador) usuario).getDataNascimento().isBefore(LocalDate.now())){
                               do{
                                   usuario.setId(repUsuarios.gerarId());
                               }while (repUsuarios.existeUsuario(usuario.getId()));
                               repUsuarios.inserir(usuario);
                           }else{
                               throw new DataDeNascimentoInvalidaException(((Colaborador) usuario).getDataNascimento());
                           }
                        }else{
                            throw new CampoVazioException();
                        }
                    }else{
                        throw new CampoVazioException();
                    }
                }else if(usuario instanceof Cliente){
                    if(!usuario.getUsuario().isEmpty() && !usuario.getEmail().isEmpty() && !usuario.getSenha().isEmpty()){
                        if(!((Cliente) usuario).getNome().isEmpty() && !((Cliente) usuario).getCpf().isEmpty() &&
                           !((Cliente) usuario).getTelefone().isEmpty() && !((Cliente) usuario).getEndereco().isEmpty() && !((Cliente) usuario).getCnh().isEmpty() &&
                            ((Cliente) usuario).getQtdDeLocacoes() >= 0){
                            if(((Cliente) usuario).getDataNascimento().isBefore(LocalDate.now())){
                                if(((Cliente) usuario).getIdade(((Cliente) usuario).getDataNascimento()) >= 18){
                                    if(((Cliente) usuario).getAnosDeHabilitacao(((Cliente) usuario).getDataDeHabilitacao()) >= 3){
                                        repUsuarios.inserir(usuario);
                                    }else{
                                        throw new AnosDeHabilitacaoInsuficientesException();
                                    }
                                }else{
                                    throw new UsuarioMenorDeIdadeException();
                                }
                            }else{
                                throw new DataDeNascimentoInvalidaException(((Cliente) usuario).getDataNascimento());
                            }
                        }else{
                            throw new CampoVazioException();
                        }
                    }else{
                        throw new CampoVazioException();
                    }
                }
            }else{
                throw new UsuarioExisteException(usuario.getUsuario());
            }
        }else{
            throw new UsuarioNuloException(usuario.getUsuario());
        }
    }

    @Override
    public void atualizarColaborador(Usuario usuario, String user, String senha, String email, String nomeCompleto, String cpf, LocalDate dataDeNascimento, String telefone, String endereco) throws UsuarioNaoExisteException, UsuarioNuloException {
        if(usuario != null){
            if(this.existeUsuario(usuario.getUsuario())){
                if(user.isEmpty() || usuario.getUsuario().equals(user) || this.existeUsuario(user)){
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
            }else{
                throw new UsuarioNaoExisteException(usuario.getUsuario());
            }
        }else{
            throw new UsuarioNuloException(usuario.getUsuario());
        }
    }

    @Override
    public void atualizarCliente(Usuario usuario, String user, String senha, String email, String nome, String cpf, LocalDate dataDeNascimento, String telefone, String endereco, String cnh, LocalDate dataDeHabilitacao, int qntDeLocacoes) throws UsuarioNaoExisteException, UsuarioNuloException {
        if(usuario != null){
            if(this.existeUsuario(usuario.getUsuario())){
                if(user.isEmpty() || usuario.getUsuario().equals(user) || this.existeUsuario(user)){
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
            }else{
                throw new UsuarioNaoExisteException(usuario.getUsuario());
            }
        }else{
            throw new UsuarioNuloException(usuario.getUsuario());
        }
    }

    @Override
    public void deletarUsuario(Usuario usuario) throws UsuarioNaoExisteException, UsuarioNuloException {
        if(usuario != null){
            if(repUsuarios.existeUsuario(usuario.getUsuario())){
                repUsuarios.deletar(usuario);
            }else{
                throw new UsuarioNaoExisteException(usuario.getUsuario());
            }
        }else{
            throw new UsuarioNuloException(usuario.getUsuario());
        }
    }

    public void checarLogin(String usuario, String senha) throws IOException, UsuarioNaoExisteException, UsuarioLogadoComSucessoException, LoginIncorretoException {
        if(this.existeUsuario(usuario)){
            Usuario user = this.buscarUsuario(usuario);

            if(user.getUsuario().equals(usuario) && user.getSenha().equals(senha)){
                if(user instanceof Colaborador && ((Colaborador) user).isAdm()){
                    throw new UsuarioLogadoComSucessoException("telaPrincipalColaboradorAdm.fmxl", "Loocalize - Colaborador Adm");
                }else if(user instanceof Colaborador && !((Colaborador) user).isAdm()){
                    throw new UsuarioLogadoComSucessoException("telaPrincipalColaborador.fmxl", "Loocalize - Colaborador");
                }else{
                    throw new UsuarioLogadoComSucessoException("telaPrincipalCliente.fmxl", "Loocalize - Home");
                }
            }else{
                throw new LoginIncorretoException();
            }
        }else{
            throw new UsuarioNaoExisteException(usuario);
        }
    }

    public void checarDadosDaNovaConta(String usuario, String email, String senha, String senhaRepetida) throws UsuarioExisteException, EmailInvalidoException, SenhasDiferentesException {
        if(!this.existeUsuario(usuario)){
            if(this.emailValido(email)){
                if(senha.equals(senhaRepetida)){
                    CadastroClientePessoalController ccpc = CadastroClientePessoalController.getInstance();
                    ccpc.setNovoCliente(new Cliente(usuario, senha, email));
                }else{
                    throw new SenhasDiferentesException();
                }
            }else{
                throw new EmailInvalidoException(email);
            }
        }else{
            throw new UsuarioExisteException(usuario);
        }
    }

    public void checarDadosPessoais(String nome, LocalDate dataDeNascimento, String telefone, String endereco, String cpf, String cnh, LocalDate dataDeHabilitacao) throws CpfInvalidoException, TelefoneInvalidoException, DataDeHabilitacaoInvalidaException, DataDeNascimentoInvalidaException, AnosDeHabilitacaoInsuficientesException, UsuarioExisteException, CampoVazioException, UsuarioNuloException, UsuarioMenorDeIdadeException {
        if(dataDeNascimento.isBefore(LocalDate.now())){
            if(this.telefoneValido(telefone)){
                if(this.cpfValido(cpf)){
                    if(dataDeHabilitacao.isBefore(LocalDate.now())){
                        CadastroClientePessoalController ccpc = CadastroClientePessoalController.getInstance();
                        ccpc.getNovoCliente().setNome(nome);
                        ccpc.getNovoCliente().setDataNascimento(dataDeNascimento);
                        ccpc.getNovoCliente().setTelefone(telefone);
                        ccpc.getNovoCliente().setEndereco(endereco);
                        ccpc.getNovoCliente().setCpf(cpf);
                        ccpc.getNovoCliente().setCnh(cnh);
                        ccpc.getNovoCliente().setDataDeHabilitacao(dataDeHabilitacao);
                        this.inserirUsuario(ccpc.getNovoCliente());
                    }else{
                        throw new DataDeHabilitacaoInvalidaException(dataDeHabilitacao);
                    }
                }else{
                    throw new CpfInvalidoException(cpf);
                }
            }else{
                throw new TelefoneInvalidoException(telefone);
            }
        }else{
            throw new DataDeNascimentoInvalidaException(dataDeNascimento);
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
    public boolean emailValido(String email) {
        Matcher matcher = patternEmail.matcher(email);
        return matcher.matches();
    }

    @Override
    public boolean telefoneValido(String telefone){
        Matcher matcher = patternTelefone.matcher(telefone);
        return matcher.matches();
    }

    @Override
    public boolean cpfValido(String cpf){
        boolean valido = false;
        cpf = cpf.replaceAll("[^0-9]", "");
        if(cpf.length() == 11){
            int[] digitos = new int[11];
            for(int i = 0; i < 11; i++){
                digitos[i] = Character.digit(cpf.charAt(i), 10);
            }
            int primeiroDigito = 0;
            int segundoDigito = 0;
            for(int i = 0; i < 9; i++){
                primeiroDigito += digitos[i] * (10 - i);
                segundoDigito += digitos[i] * (11 - i);
            }
            primeiroDigito = 11 - (primeiroDigito % 11);
            if(primeiroDigito > 9){
                primeiroDigito = 0;
            }
            segundoDigito += primeiroDigito * 2;
            segundoDigito = 11 - (segundoDigito % 11);
            if(segundoDigito > 9){
                segundoDigito = 0;
            }
            valido = primeiroDigito == digitos[9] && segundoDigito == digitos[10];
        }
        return valido;
    }

    @Override
    public String gerarId() {
        return repUsuarios.gerarId();
    }
}

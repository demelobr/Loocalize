package testes;

import data.*;
import models.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;

public class TesteCRUD {
    private IRepositorioUsuarios repUsuarios;
    private IRepositorioVeiculos repVeiculos;
    private IRepositorioPromocoes repPromocoes;
    private IRepositorioLocacoes repLocacoes;

    public TesteCRUD(){
        this.repUsuarios = RepositorioUsuarios.getInstance();
        this.repVeiculos = RepositorioVeiculos.getInstance();
        this.repPromocoes = RepositorioPromocoes.getInstance();
        this.repLocacoes = RepositorioLocacoes.getInstance();
    }

    public static void main(String[] args) {
        TesteCRUD testeCRUD = new TesteCRUD();

        //Usuários
        Administrador adm = new Administrador("adm", "adm", "adm@gmail.com");
        Colaborador colaborador = new Colaborador("colab", "colab", "colab@gmail.com", "Bruno Melo", "11122233344", LocalDate.of(1997, 2, 24), "81999998888", "Rua do Colaborador");
        Cliente cliente = new Cliente("client", "client", "client@gmail.com", "Alex", "22211133355", LocalDate.of(1999, 5, 8), "8199887766", "Rua do cliente", "000999888", LocalDate.of(2022, 3, 9), 0);

        adm.setId(testeCRUD.repUsuarios.gerarId());
        colaborador.setId(testeCRUD.repUsuarios.gerarId());
        cliente.setId(testeCRUD.repUsuarios.gerarId());

        //Veiculo
        Veiculo veiculo = new Veiculo("Onix", "Chevrolet", "PEO1234", Year.of(2019), 40000, 10, 100.0, "/home/demelobr/Pictures/celular/Projeto kart/Screenshot_20170404-231439.png", true);
        veiculo.setId(testeCRUD.repVeiculos.gerarId());
        Veiculo veiculo1 = new Veiculo("Tracker", "Chevrolet", "OXE6798", Year.of(2022), 23000, 4, 200.0, "/home/demelobr/Pictures/celular/Projeto kart/Screenshot_20170404-231439.png", true);
        veiculo1.setId(testeCRUD.repVeiculos.gerarId());

        //Promocao
        Promocao promocao = new Promocao("DiaDosPais50OFF", 50, LocalDate.now(), LocalDateTime.of(2023, 8, 15, 23, 59), true);
        promocao.setId(testeCRUD.repPromocoes.gerarId());
        Promocao promocao1 = new Promocao("PrimeiroAluguel15OFF", 15, LocalDate.now(), LocalDateTime.of(2023, 8, 15, 23, 59), true);
        promocao1.setId(testeCRUD.repPromocoes.gerarId());

        //Locacao
        Locacao locacao = new Locacao(veiculo, cliente, promocao, LocalDateTime.now(), 10, false);
        locacao.setId(testeCRUD.repLocacoes.gerarId());
        Locacao locacao1 = new Locacao(veiculo1, cliente, promocao1, LocalDateTime.now(), 5, false);
        locacao1.setId(testeCRUD.repLocacoes.gerarId());

        //Instâncias
        System.out.println(adm);
        System.out.println("===============\n");
        System.out.println(colaborador);
        System.out.println("===============\n");
        System.out.println(cliente);
        System.out.println("===============\n");
        System.out.println(veiculo);
        System.out.println("===============\n");
        System.out.println(veiculo1);
        System.out.println("===============\n");
        System.out.println(promocao);
        System.out.println("===============\n");
        System.out.println(promocao1);
        System.out.println("===============\n");
        System.out.println(locacao);
        System.out.println("===============\n");
        System.out.println(locacao1);

        //Testes dos repositórios das classes básicas

        //Obs1: Para efeito de testes, use apenas um bloco de cada vez para validar
        //     o funcionamento dos models e seus respectivos repositórios de forma isolada.
        //Obs2: Comente os blocos que não estarão em testes.

        //Bloco de Usuario
        testeCRUD.repUsuarios.inserir(adm);
        testeCRUD.repUsuarios.inserir(colaborador);
        testeCRUD.repUsuarios.inserir(cliente);
        System.out.println(testeCRUD.repUsuarios.existeUsuario("adm"));
        System.out.println("===============\n");
        System.out.println(testeCRUD.repUsuarios.buscarUsuario("client"));
        System.out.println("===============\n");
        testeCRUD.repUsuarios.atualizarUsuario(adm, "admin", "admin", "admin@gmail.com");
        testeCRUD.repUsuarios.atualizarColaborador(colaborador, "Colaborador", "12345678900", LocalDate.of(1997, 2, 24), "81998855665", "Rua Nova do Colaborador");
        testeCRUD.repUsuarios.atualizarCliente(cliente, "Cliente", "22211133355", LocalDate.of(1999, 5, 8), "8199887766", "Rua do cliente", "000999888", LocalDate.of(2022, 3, 9), 5);
        System.out.println(testeCRUD.repUsuarios.listarTodosUsuarios());
        System.out.println("===============\n");
        testeCRUD.repUsuarios.deletar(colaborador);
        System.out.println(testeCRUD.repUsuarios.listarTodosUsuarios());

        //Bloco de Veiculo
//        testeCRUD.repVeiculos.inserir(veiculo);
//        testeCRUD.repVeiculos.inserir(veiculo1);
//        System.out.println(testeCRUD.repVeiculos.existeVeiculo(veiculo.getId()));
//        System.out.println("===============\n");
//        System.out.println(testeCRUD.repVeiculos.buscarVeiculo(veiculo1.getId()));
//        System.out.println("===============\n");
//        testeCRUD.repVeiculos.atualizar(veiculo, "Siena", "Fiat", "QWE3627", Year.of(2018), 55000, 15, 70.0, "/home/demelobr/Pictures/celular/Projeto kart/Screenshot_20170404-231439.png", true);
//        testeCRUD.repVeiculos.deletar(veiculo1);
//        System.out.println(testeCRUD.repVeiculos.listarTodosVeiculos());

        //Bloco de Promocao
//        testeCRUD.repPromocoes.inserir(promocao);
//        testeCRUD.repPromocoes.inserir(promocao1);
//        System.out.println(testeCRUD.repPromocoes.existePromocao(promocao.getId()));
//        System.out.println("===============\n");
//        System.out.println(testeCRUD.repPromocoes.buscarPromocao(promocao1.getId()));
//        System.out.println("===============\n");
//        testeCRUD.repPromocoes.atualizar(promocao1, "PlayNasFerias10OFF", 10, LocalDateTime.of(2023, 8, 15, 23, 59), true);
//        testeCRUD.repPromocoes.deletar(promocao);
//        System.out.println(testeCRUD.repPromocoes.listarTodasPromocoes());

        //Bloco de Locacao
//        testeCRUD.repLocacoes.inserir(locacao);
//        testeCRUD.repLocacoes.inserir(locacao1);
//        System.out.println(testeCRUD.repLocacoes.existeLocacao(locacao.getId()));
//        System.out.println(testeCRUD.repLocacoes.buscarUsuario(locacao1.getId()));
//        testeCRUD.repLocacoes.atualizar(locacao1, veiculo, cliente, promocao1, LocalDateTime.now(), 7, false);
//        testeCRUD.repLocacoes.deletar(locacao);
//        System.out.println(testeCRUD.repLocacoes.listarTodasLocacoes());

    }
}

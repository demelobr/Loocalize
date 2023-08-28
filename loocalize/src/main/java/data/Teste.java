package data;

import models.Cliente;
import models.Colaborador;
import models.Promocao;
import models.Veiculo;

import java.time.LocalDate;
import java.time.Year;

public class Teste {
    IRepositorioVeiculos repositorioVeiculos;
    IRepositorioUsuarios repositorioUsuarios;
    IRepositorioPromocoes repositorioPromocoes;
    public Teste(){
        repositorioVeiculos = RepositorioVeiculos.getInstance();
        repositorioUsuarios = RepositorioUsuarios.getInstance();
        repositorioPromocoes = RepositorioPromocoes.getInstance();
    }

    public void motandoCatalogo(){
        Veiculo veiculo1 = new Veiculo("Logan", "Renault", "OXE1234", Year.of(2019), 23000, 10, 100,"/home/demelobr/Documents/My Codes/Java/Loocalize/loocalize/src/main/resources/gui/images/veiculos/logan.jpg",true);
        veiculo1.setId("00000");
        repositorioVeiculos.inserir(veiculo1);

        Veiculo veiculo2 = new Veiculo("Siena", "Fiat", "EAP1234", Year.of(2018), 25000, 10, 200,"/home/demelobr/Documents/My Codes/Java/Loocalize/loocalize/src/main/resources/gui/images/veiculos/siena.jpg",true);
        veiculo2.setId("11111");
        repositorioVeiculos.inserir(veiculo2);

        Veiculo veiculo3 = new Veiculo("Peugeot 208", "Peugeot", "QWE3214", Year.of(2023), 13000, 10, 200,"/home/demelobr/Documents/My Codes/Java/Loocalize/loocalize/src/main/resources/gui/images/veiculos/peugeot-208-2023.jpg",true);
        veiculo3.setId("22222");
        repositorioVeiculos.inserir(veiculo3);

        Veiculo veiculo4 = new Veiculo("Voyage", "Volkswagen", "VLK1234", Year.of(2022), 10000, 10, 300,"/home/demelobr/Documents/My Codes/Java/Loocalize/loocalize/src/main/resources/gui/images/veiculos/volkswagen-voyage.jpg",true);
        veiculo4.setId("33333");
        repositorioVeiculos.inserir(veiculo4);

        Veiculo veiculo5 = new Veiculo("Cronos", "Fiat", "CRN0321", Year.of(2023), 20000, 10, 400,"/home/demelobr/Documents/My Codes/Java/Loocalize/loocalize/src/main/resources/gui/images/veiculos/fiat-cronos-2023.jpg",true);
        veiculo5.setId("44444");
        repositorioVeiculos.inserir(veiculo5);

        Veiculo veiculo6 = new Veiculo("Kwid", "Renault", "KWD7894", Year.of(2020), 00100, 10, 200,"/home/demelobr/Documents/My Codes/Java/Loocalize/loocalize/src/main/resources/gui/images/veiculos/renault-kwid.jpg",true);
        veiculo6.setId("55555");
        repositorioVeiculos.inserir(veiculo6);
        /////// fiz mais daqui para frente para ficar mais diverso

        Veiculo veiculo7 = new Veiculo("Argo", "Fiat", "RGO4561", Year.of(2022), 23500, 10, 100,"/home/demelobr/Documents/My Codes/Java/Loocalize/loocalize/src/main/resources/gui/images/veiculos/fiat-argo-2022.jpg",true);
        veiculo7.setId("66666");
        repositorioVeiculos.inserir(veiculo7);

        Veiculo veiculo8 = new Veiculo("Gol", "Volkswagen", "GLP1234", Year.of(2019), 24000, 10, 100,"/home/demelobr/Documents/My Codes/Java/Loocalize/loocalize/src/main/resources/gui/images/veiculos/volkswagen-gol.jpg",true);
        veiculo8.setId("77777");
        repositorioVeiculos.inserir(veiculo8);

        Veiculo veiculo9 = new Veiculo("HB20", "Hyundai", "HBV6549", Year.of(2020), 26000, 10, 100,"/home/demelobr/Documents/My Codes/Java/Loocalize/loocalize/src/main/resources/gui/images/veiculos/hb20-2013.jpg",true);
        veiculo9.setId("88888");
        repositorioVeiculos.inserir(veiculo9);

        Veiculo veiculo10 = new Veiculo("Strada", "Fiat", "STR6549", Year.of(2020), 27000, 10, 200,"/home/demelobr/Documents/My Codes/Java/Loocalize/loocalize/src/main/resources/gui/images/veiculos/fiat-strada.jpg",true);
        veiculo10.setId("00001");
        repositorioVeiculos.inserir(veiculo10);

        Veiculo veiculo11 = new Veiculo("Mobi", "Fiat", "NFO6549", Year.of(2020), 33000, 10, 100,"/home/demelobr/Documents/My Codes/Java/Loocalize/loocalize/src/main/resources/gui/images/veiculos/fiat-mobi.jpg",true);
        veiculo11.setId("00002");
        repositorioVeiculos.inserir(veiculo11);

        Veiculo veiculo12 = new Veiculo("Civic", "Honda", "CVC6549", Year.of(2021), 13000, 10, 100,"/home/demelobr/Documents/My Codes/Java/Loocalize/loocalize/src/main/resources/gui/images/veiculos/honda-civic.jpg",true);
        veiculo12.setId("00003");
        repositorioVeiculos.inserir(veiculo12);

        Veiculo veiculo13 = new Veiculo("Grand Siena", "Fiat", "GRS6509", Year.of(2021), 13000, 10, 100,"/home/demelobr/Documents/My Codes/Java/Loocalize/loocalize/src/main/resources/gui/images/veiculos/fiat-grand-siena-2021.jpg",true);
        veiculo13.setId("00004");
        repositorioVeiculos.inserir(veiculo13);

    }

    public void criarClienteTeste(){
        Cliente cliente = new Cliente("demelobr", "12345", "demelobr@gmail.com", "Bruno Melo", "751.451.570-45", LocalDate.of(1997,2,24), "(81) 9998877-9982", "Rua São João", "32142345", LocalDate.of(2019,5,21), 0);
        cliente.setId("12345");
        repositorioUsuarios.inserir(cliente);
    }

    public void criarColaboradorTeste(){
        Colaborador colaborador = new Colaborador("colab", "colab", "colab@gmail.com", "José Felipe", "799.657.310-71", LocalDate.of(1995,5,25), "(81) 98723-4938", "Rua José Pinho", false);
        colaborador.setId("09876");
        repositorioUsuarios.inserir(colaborador);
    }

    public void criarPromocoesTeste(){
        Promocao promocao1 = new Promocao("diadospais10off", 10, 0, 0, LocalDate.now(), LocalDate.of(2023, 8, 31), true);
        promocao1.setId("00121");
        repositorioPromocoes.inserir(promocao1);

        Promocao promocao2 = new Promocao("membrobronze15off", 15, 0, 3, LocalDate.now(), LocalDate.of(2023, 9, 20), true);
        promocao2.setId("20621");
        repositorioPromocoes.inserir(promocao2);

        Promocao promocao3 = new Promocao("viagemdomes20off", 20, 15, 0, LocalDate.now(), LocalDate.of(2023, 9, 20), true);
        promocao3.setId("20361");
        repositorioPromocoes.inserir(promocao3);
    }
}

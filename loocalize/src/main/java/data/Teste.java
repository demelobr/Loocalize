package data;

import models.Cliente;
import models.Colaborador;
import models.Veiculo;

import java.time.LocalDate;
import java.time.Year;

public class Teste {
    IRepositorioVeiculos repositorioVeiculos;
    IRepositorioUsuarios repositorioUsuarios;
    public Teste(){
        repositorioVeiculos = RepositorioVeiculos.getInstance();
        repositorioUsuarios = RepositorioUsuarios.getInstance();
    }

    public void motandoCatalogo(){
        Veiculo veiculo1 = new Veiculo("Logan", "Renault", "OXE1234", Year.of(2019), 23000, 10, 100,"/home/demelobr/Pictures/logan.jpg",true);
        veiculo1.setId("00000");
        repositorioVeiculos.inserir(veiculo1);

        Veiculo veiculo2 = new Veiculo("Siena", "Fiat", "EAP1234", Year.of(2020), 23000, 10, 100,"/home/demelobr/Pictures/siena.jpg",true);
        veiculo2.setId("11111");
        repositorioVeiculos.inserir(veiculo2);

        Veiculo veiculo3 = new Veiculo("Siena", "Fiat", "EAP1234", Year.of(2020), 23000, 10, 100,"/home/demelobr/Pictures/siena.jpg",true);
        veiculo3.setId("11111");
        repositorioVeiculos.inserir(veiculo3);

        Veiculo veiculo4 = new Veiculo("Siena", "Fiat", "EAP1234", Year.of(2020), 23000, 10, 100,"/home/demelobr/Pictures/siena.jpg",true);
        veiculo4.setId("11111");
        repositorioVeiculos.inserir(veiculo4);

        Veiculo veiculo5 = new Veiculo("Siena", "Fiat", "EAP1234", Year.of(2020), 23000, 10, 100,"/home/demelobr/Pictures/siena.jpg",true);
        veiculo5.setId("11111");
        repositorioVeiculos.inserir(veiculo5);

        Veiculo veiculo6 = new Veiculo("Siena", "Fiat", "EAP1234", Year.of(2020), 23000, 10, 100,"/home/demelobr/Pictures/siena.jpg",true);
        veiculo6.setId("11111");
        repositorioVeiculos.inserir(veiculo6);
    }

    public void criarClienteTeste(){
        Cliente cliente = new Cliente("demelobr", "12345", "demelobr@gmail.com", "Bruno Melo", "10950986402", LocalDate.of(1997,2,24), "(81) 9998877-9982", "Rua São João", "32142345", LocalDate.of(2019,5,21), 0);
        cliente.setId("12345");
        repositorioUsuarios.inserir(cliente);
    }

    public void criarColaboradorTeste(){
        Colaborador colaborador = new Colaborador("colab", "colab", "colab@gmail.com", "José Felipe", "10950986402", LocalDate.of(1995,5,25), "(81) 98723-4938", "Rua José Pinho", false);
        colaborador.setId("09876");
        repositorioUsuarios.inserir(colaborador);
    }
}

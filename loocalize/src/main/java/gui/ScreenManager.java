package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ScreenManager {
    private static ScreenManager instance;
    private static Stage stg;

    private Scene loginScene;
    private Scene cadastroContaScene;
    private Scene cadastroPessoalScene;
    private Scene colaboradorAbaVeiculosScene;
    private Scene colaboradorCadastroVeiculoScene;
    private Scene colaboradorFichaVeiculoScene;
    private Scene colaboradorAbaPromocoesScene;
    private Scene colaboradorCadastroPromocaoScene;
    private Scene colaboradorFichaPromocaoScene;
    private Scene admAbaColaboradoresScene;
    private Scene admCadastroColaboradorScene;
    private Scene admFichaColaboradorScene;
    private Scene admAbaClientesScene;
    private Scene admCadastroClienteScene;
    private Scene clienteAbaCatalogoScene;
    private Scene clienteAbaMinhasLocacoesScene;

    private LoginController loginController;
    private CadastroClienteContaController cadastroClienteContaController;
    private CadastroClientePessoalController cadastroClientePessoalController;
    private ColaboradorAbaVeiculosController colaboradorAbaVeiculosController;
    private ColaboradorCadastroVeiculoController colaboradorCadastroVeiculoController;
    private ColaboradorFichaVeiculoController colaboradorFichaVeiculoController;
    private ColaboradorAbaPromocoesController colaboradorAbaPromocoesController;
    private ColaboradorCadastroPromocaoController colaboradorCadastroPromocaoController;
    private ColaboradorFichaPromocaoController colaboradorFichaPromocaoController;
    private AdmAbaColaboresController admAbaColaboresController;
    private AdmCadastroColaboradorController admCadastroColaboradorController;
    private AdmFichaColaboradorController admFichaColaboradorController;
    private AdmAbaClientesController admAbaClientesController;
    private AdmCadastroClienteController admCadastroClienteController;
    private ClienteAbaCatalogoController clienteAbaCatalogoController;
    private ClienteAbaMinhasLocacoesController clienteAbaMinhasLocacoesController;


    public ScreenManager(){
        this.carregarTelas();
    }
    public static ScreenManager getInstance(){
        if(instance == null){
            instance = new ScreenManager();
        }
        return instance;
    }

    public static Stage getStg() {
        return stg;
    }

    public static void setStg(Stage stg) {
        ScreenManager.stg = stg;
    }

    //Métodos GET das Scenes

    public Scene getLoginScene() {
        return loginScene;
    }

    public Scene getCadastroContaScene() {
        return cadastroContaScene;
    }

    public Scene getCadastroPessoalScene() {
        return cadastroPessoalScene;
    }

    public Scene getColaboradorAbaVeiculosScene() {
        return colaboradorAbaVeiculosScene;
    }

    public Scene getColaboradorCadastroVeiculoScene() {
        return colaboradorCadastroVeiculoScene;
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public Scene getColaboradorFichaVeiculoScene() {
        return colaboradorFichaVeiculoScene;
    }

    public Scene getColaboradorAbaPromocoesScene() {
        return colaboradorAbaPromocoesScene;
    }

    public Scene getColaboradorCadastroPromocaoScene() {
        return colaboradorCadastroPromocaoScene;
    }

    public Scene getColaboradorFichaPromocaoScene() {
        return colaboradorFichaPromocaoScene;
    }

    public Scene getAdmAbaColaboradoresScene() {
        return admAbaColaboradoresScene;
    }

    public Scene getAdmCadastroColaboradorScene() {
        return admCadastroColaboradorScene;
    }

    public Scene getAdmFichaColaboradorScene() {
        return admFichaColaboradorScene;
    }

    public Scene getAdmAbaClientesScene() {
        return admAbaClientesScene;
    }

    public Scene getAdmCadastroClienteScene() {
        return admCadastroClienteScene;
    }

    public Scene getClienteAbaCatalogoScene() {
        return clienteAbaCatalogoScene;
    }

    public Scene getClienteAbaMinhasLocacoesScene() {
        return clienteAbaMinhasLocacoesScene;
    }

    //Métodos GET dos Controllers

    public CadastroClienteContaController getCadastroClienteContaController() {
        return cadastroClienteContaController;
    }

    public CadastroClientePessoalController getCadastroClientePessoalController() {
        return cadastroClientePessoalController;
    }

    public ColaboradorAbaVeiculosController getColaboradorAbaVeiculosController() {
        return colaboradorAbaVeiculosController;
    }

    public ColaboradorCadastroVeiculoController getColaboradorCadastroVeiculoController() {
        return colaboradorCadastroVeiculoController;
    }

    public ColaboradorFichaVeiculoController getColaboradorFichaVeiculoController() {
        return colaboradorFichaVeiculoController;
    }

    public ColaboradorAbaPromocoesController getColaboradorAbaPromocoesController() {
        return colaboradorAbaPromocoesController;
    }

    public ColaboradorCadastroPromocaoController getColaboradorCadastroPromocaoController() {
        return colaboradorCadastroPromocaoController;
    }

    public ColaboradorFichaPromocaoController getColaboradorFichaPromocaoController() {
        return colaboradorFichaPromocaoController;
    }

    public AdmAbaColaboresController getAdmAbaColaboresController() {
        return admAbaColaboresController;
    }

    public AdmCadastroColaboradorController getAdmCadastroColaboradorController() {
        return admCadastroColaboradorController;
    }

    public AdmFichaColaboradorController getAdmFichaColaboradorController() {
        return admFichaColaboradorController;
    }

    public AdmAbaClientesController getAdmAbaClientesController() {
        return admAbaClientesController;
    }

    public AdmCadastroClienteController getAdmCadastroClienteController() {
        return admCadastroClienteController;
    }

    public ClienteAbaCatalogoController getClienteAbaCatalogoController() {
        return clienteAbaCatalogoController;
    }

    public ClienteAbaMinhasLocacoesController getClienteAbaMinhasLocacoesController() {
        return clienteAbaMinhasLocacoesController;
    }

    private void carregarTelas(){
        try {

            FXMLLoader loginPane = new FXMLLoader(getClass().getResource("login.fxml"));
            this.loginScene = new Scene(loginPane.load());
            this.loginController = loginPane.getController();

            FXMLLoader cadastroContaPane = new FXMLLoader(getClass().getResource("cadastro-conta.fxml"));
            this.cadastroContaScene = new Scene(cadastroContaPane.load());
            this.cadastroClienteContaController = cadastroContaPane.getController();

            FXMLLoader cadastroPessoalPane = new FXMLLoader(getClass().getResource("cadastro-pessoal.fxml"));
            this.cadastroPessoalScene = new Scene(cadastroPessoalPane.load());
            this.cadastroClientePessoalController = cadastroPessoalPane.getController();

            FXMLLoader colaboradorAbaVeiculosPane = new FXMLLoader(getClass().getResource("colaborador-aba-veiculos.fxml"));
            this.colaboradorAbaVeiculosScene = new Scene(colaboradorAbaVeiculosPane.load());
            this.colaboradorAbaVeiculosController = colaboradorAbaVeiculosPane.getController();

            FXMLLoader colaboradorCadastroVeiculoPane = new FXMLLoader(getClass().getResource("colaborador-cadastro-veiculo.fxml"));
            this.colaboradorCadastroVeiculoScene = new Scene(colaboradorCadastroVeiculoPane.load());
            this.colaboradorCadastroVeiculoController = colaboradorCadastroVeiculoPane.getController();

            FXMLLoader colaboradorFichaVeiculoPane = new FXMLLoader(getClass().getResource("colaborador-ficha-veiculo.fxml"));
            this.colaboradorFichaVeiculoScene = new Scene(colaboradorFichaVeiculoPane.load());
            this.colaboradorFichaVeiculoController = colaboradorFichaVeiculoPane.getController();

            FXMLLoader colaboradorAbaPromocoesPane = new FXMLLoader(getClass().getResource("colaborador-aba-promocoes.fxml"));
            this.colaboradorAbaPromocoesScene = new Scene(colaboradorAbaPromocoesPane.load());
            this.colaboradorAbaPromocoesController = colaboradorAbaPromocoesPane.getController();

            FXMLLoader colaboradorCadastroPromocaoPane = new FXMLLoader(getClass().getResource("colaborador-cadastro-promocao.fxml"));
            this.colaboradorCadastroPromocaoScene = new Scene(colaboradorCadastroPromocaoPane.load());
            this.colaboradorCadastroPromocaoController = colaboradorCadastroPromocaoPane.getController();

            FXMLLoader colaboradorFichaPromocaoPane = new FXMLLoader(getClass().getResource("colaborador-ficha-promocao.fxml"));
            this.colaboradorFichaPromocaoScene = new Scene(colaboradorFichaPromocaoPane.load());
            this.colaboradorFichaPromocaoController = colaboradorFichaPromocaoPane.getController();

            FXMLLoader admAbaColaboradoresPane = new FXMLLoader(getClass().getResource("adm-aba-colaboradores.fxml"));
            this.admAbaColaboradoresScene = new Scene(admAbaColaboradoresPane.load());
            this.admAbaColaboresController = admAbaColaboradoresPane.getController();

            FXMLLoader admCadastroColaboradorPane = new FXMLLoader(getClass().getResource("adm-cadastro-colaborador.fxml"));
            this.admCadastroColaboradorScene = new Scene(admCadastroColaboradorPane.load());
            this.admCadastroColaboradorController = admCadastroColaboradorPane.getController();

            FXMLLoader admFichaColaboradorPane = new FXMLLoader(getClass().getResource("adm-ficha-colaborador.fxml"));
            this.admFichaColaboradorScene = new Scene(admFichaColaboradorPane.load());
            this.admFichaColaboradorController = admFichaColaboradorPane.getController();

            FXMLLoader admAbaClientesPane = new FXMLLoader(getClass().getResource("adm-aba-clientes.fxml"));
            this.admAbaClientesScene = new Scene(admAbaClientesPane.load());
            this.admAbaClientesController = admAbaClientesPane.getController();

            FXMLLoader admCadastroClientePane = new FXMLLoader(getClass().getResource("adm-cadastro-cliente.fxml"));
            this.admCadastroClienteScene = new Scene(admCadastroClientePane.load());
            this.admCadastroClienteController = admCadastroClientePane.getController();

            FXMLLoader clienteAbaCatalogoPane = new FXMLLoader(getClass().getResource("cliente-aba-catalogo.fxml"));
            this.clienteAbaCatalogoScene = new Scene(clienteAbaCatalogoPane.load());
            this.clienteAbaCatalogoController = clienteAbaCatalogoPane.getController();

            FXMLLoader clienteAbaMinhasLocacoesPane = new FXMLLoader(getClass().getResource("cliente-aba-locacoes.fxml"));
            this.clienteAbaMinhasLocacoesScene = new Scene(clienteAbaMinhasLocacoesPane.load());
            this.clienteAbaMinhasLocacoesController = clienteAbaMinhasLocacoesPane.getController();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void changeScene(String fxml, String title) {
        switch (fxml){
            case "login.fxml" -> stg.setScene(loginScene);
            case "cadastro-conta.fxml" -> stg.setScene(cadastroContaScene);
            case "cadastro-pessoal.fxml" -> stg.setScene(cadastroPessoalScene);
            case "colaborador-aba-veiculos.fxml" -> stg.setScene(colaboradorAbaVeiculosScene);
            case "colaborador-cadastro-veiculo.fxml" -> stg.setScene(colaboradorCadastroVeiculoScene);
            case "colaborador-ficha-veiculo.fxml" -> stg.setScene(colaboradorFichaVeiculoScene);
            case "colaborador-aba-promocoes.fxml" -> stg.setScene(colaboradorAbaPromocoesScene);
            case "colaborador-cadastro-promocao.fxml" -> stg.setScene(colaboradorCadastroPromocaoScene);
            case "colaborador-ficha-promocao.fxml" -> stg.setScene(colaboradorFichaPromocaoScene);
            case "adm-aba-colaboradores.fxml" -> stg.setScene(admAbaColaboradoresScene);
            case "adm-cadastro-colaborador.fxml" -> stg.setScene(admCadastroColaboradorScene);
            case "adm-ficha-colaborador.fxml" -> stg.setScene(admFichaColaboradorScene);
            case "adm-aba-clientes.fxml" -> stg.setScene(admAbaClientesScene);
            case "adm-cadastro-cliente.fxml" -> stg.setScene(admCadastroClienteScene);
            case "cliente-aba-catalogo.fxml" -> stg.setScene(clienteAbaCatalogoScene);
            case "cliente-aba-locacoes.fxml" -> stg.setScene(clienteAbaMinhasLocacoesScene);
        }

        stg.setTitle(title);
    }

}

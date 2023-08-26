package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ScreenManager {
    private static ScreenManager instance;
    private static Stage stg;

    private Scene loginScene;
    private Scene cadastroContaScene;
    private Scene cadastroPessoalScene;
    private Scene colaboradorAbaVeiculosScene;
    private Scene colaboradorCadastroVeiculoScene;
    private Scene colaboradorFichaVeiculoScene;

    private LoginController loginController;
    private CadastroClienteContaController cadastroClienteContaController;
    private CadastroClientePessoalController cadastroClientePessoalController;
    private ColaboradorAbaVeiculosController colaboradorAbaVeiculosController;
    private ColaboradorCadastroVeiculoController colaboradorCadastroVeiculoController;
    private ColaboradorFichaVeiculoController colaboradorFichaVeiculoController;


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
        }

        stg.setTitle(title);
    }

}

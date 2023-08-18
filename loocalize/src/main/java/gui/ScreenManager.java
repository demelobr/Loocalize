package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

public class ScreenManager {
    private static ScreenManager instance;
    private static Stage stg;
    private Parent loginPane;
    private Parent cadastroContaPane;
    private Parent cadastroPessoalPane;

    public ScreenManager(){
        this.carregarTelas();
    }

    public static ScreenManager getInstance(){
        if(instance == null){
            instance = new ScreenManager();
        }
        return instance;
    }

    public static void setStg(Stage stg) {
        ScreenManager.stg = stg;
    }

    private void carregarTelas() {
        try {
            loginPane = FXMLLoader.load(getClass().getResource("login.fxml"));
            cadastroContaPane = FXMLLoader.load(getClass().getResource("cadastro-conta.fxml"));
            cadastroPessoalPane = FXMLLoader.load(getClass().getResource("cadastro-pessoal.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void changeScene(String fxml, String title) {
        Parent pane = this.getPane(fxml);
        stg.setTitle(title);
        stg.getScene().setRoot(pane);
    }

    public Parent getPane(String fxml){
        Parent pane = null;
        switch (fxml) {
            case "login.fxml" -> pane = loginPane;
            case "cadastro-conta.fxml" -> pane = cadastroContaPane;
            case "cadastro-pessoal.fxml" -> pane = cadastroPessoalPane;
            default -> {
            }
        }
        return pane;
    }

}

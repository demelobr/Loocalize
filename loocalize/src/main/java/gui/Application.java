package gui;

import business.ServidorLoocalize;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    private ServidorLoocalize server;

    public Application(){
        this.server = ServidorLoocalize.getInstance();
    }

    public ServidorLoocalize getServer() {
        return server;
    }

    @Override
    public void start(Stage stage) throws IOException {
        ScreenManager.setStg(stage);
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Loocalize - Login");
        stage.setMinWidth(1000);
        stage.setMinHeight(600);
        stage.setResizable(true);
        stage.show();
    }
    public static void main(String[] args) {
        Application app = new Application();
        app.server.criarAdm();
        launch();
    }

}

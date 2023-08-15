package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class loginController {
    @FXML
    private Button btnEntrar;
    @FXML
    private Button btnCadastro;
    @FXML
    private TextField tfUsuario;

    // Atributos para troca de cena
    private Stage stage;
    private Scene scene;
    private Parent root;

    // String usuarioLogin = tfUsuario.getText();


    public void TrocarTelaCadastro(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("cadastro-pessoal.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}

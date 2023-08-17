package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class loginController {
    @FXML
    private Button btnEntrarLogin;

    @FXML
    private HBox btnFecharLogin;

    @FXML
    private HBox hbPushMsgLogin;

    @FXML
    private Label lbCadastraseLogin;

    @FXML
    private Label lbPushMsgLogin;

    @FXML
    private PasswordField pfSenhaLogin;

    @FXML
    private TextField tfUsuarioLogin;

    // Atributos para troca de cena
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void initialize(){
        hbPushMsgLogin.setVisible(false);
    }

    @FXML
    public void trocarTelaPrincipal(ActionEvent event) throws IOException{
        String usuario = tfUsuarioLogin.getText();
        String senha = pfSenhaLogin.getText();

        System.out.println(usuario);
        System.out.println(senha);
    }

    @FXML
    public void trocarTelaCadastro(MouseEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("cadastro-pessoal.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}

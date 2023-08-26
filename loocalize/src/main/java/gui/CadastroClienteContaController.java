package gui;

import exception.EmailInvalidoException;
import exception.SenhasDiferentesException;
import exception.UsuarioExisteException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class CadastroClienteContaController {
    Application app;

    @FXML
    private HBox btnFecharCadastroConta;

    @FXML
    private Button btnProximoCadastroConta;

    @FXML
    private Label btnVoltarCadastroConta;

    @FXML
    private HBox hbPushMsgCadastroConta;

    @FXML
    private Label lbPushMsgCadastroConta;

    @FXML
    private PasswordField pfRepetirSenhaCadastroConta;

    @FXML
    private PasswordField pfSenhaCadastroConta;

    @FXML
    private TextField tfEmailCadastroConta;

    @FXML
    private TextField tfUsuarioCadastroConta;

    public CadastroClienteContaController(){
        this.app = new Application();
    }

    @FXML
    public void initialize(){
        hbPushMsgCadastroConta.setVisible(false);
    }

    @FXML
    public void fecharPushMsg(MouseEvent event){
        hbPushMsgCadastroConta.setVisible(false);
    }

    @FXML
    public void trocarTelaCadastroPessoal(ActionEvent event) throws IOException {
        String usuario = tfUsuarioCadastroConta.getText();
        String email = tfEmailCadastroConta.getText();
        String senha = pfSenhaCadastroConta.getText();
        String senhaRepetida = pfRepetirSenhaCadastroConta.getText();

        if(usuario.isEmpty() || email.isEmpty() || senha.isEmpty() || senhaRepetida.isEmpty()){
            lbPushMsgCadastroConta.setText("Preencha todos os campos!");
            hbPushMsgCadastroConta.getStyleClass().setAll("push-msg-error");
            hbPushMsgCadastroConta.setVisible(true);
        }else{
            try {
                app.getServer().checarDadosDaNovaConta(usuario, email, senha, senhaRepetida);
                ScreenManager sm = ScreenManager.getInstance();
                sm.changeScene("cadastro-pessoal.fxml", "Loocalize - Cadastro");
            } catch (UsuarioExisteException e) {
                lbPushMsgCadastroConta.setText(e.getMessage());
                hbPushMsgCadastroConta.getStyleClass().setAll("push-msg-error");
                hbPushMsgCadastroConta.setVisible(true);
            } catch (EmailInvalidoException e) {
                lbPushMsgCadastroConta.setText(e.getMessage());
                hbPushMsgCadastroConta.getStyleClass().setAll("push-msg-error");
                hbPushMsgCadastroConta.setVisible(true);
            } catch (SenhasDiferentesException e) {
                lbPushMsgCadastroConta.setText(e.getMessage());
                hbPushMsgCadastroConta.getStyleClass().setAll("push-msg-error");
                hbPushMsgCadastroConta.setVisible(true);
            }
        }

    }

    @FXML
    public void trocarTelaLogin(MouseEvent event){
        this.resetarTela();
        ScreenManager sm = ScreenManager.getInstance();
        sm.changeScene("login.fxml", "Loocalize - Login");
    }

    public void resetarTela(){
        hbPushMsgCadastroConta.setVisible(false);
        tfUsuarioCadastroConta.setText("");
        tfEmailCadastroConta.setText("");
        pfSenhaCadastroConta.setText("");
        pfRepetirSenhaCadastroConta.setText("");
    }

}

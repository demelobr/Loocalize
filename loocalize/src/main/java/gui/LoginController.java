package gui;

import exception.LoginIncorretoException;
import exception.UsuarioLogadoComSucessoException;
import exception.UsuarioNaoExisteException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class LoginController {
    private Application app;

    @FXML
    private Button btnEntrarLogin;

    @FXML
    private HBox btnFecharLogin;

    @FXML
    private HBox hbPushMsgLogin;

    @FXML
    private ImageView imgFecharLogin;

    @FXML
    private Label lbCadastraseLogin;

    @FXML
    private Label lbPushMsgLogin;

    @FXML
    private PasswordField pfSenhaLogin;

    @FXML
    private TextField tfUsuarioLogin;

    public LoginController(){
        this.app = new Application();
    }

    @FXML
    public void initialize(){
        hbPushMsgLogin.setVisible(false);
    }
    @FXML
    public void fecharPushMsg(MouseEvent event){
        hbPushMsgLogin.setVisible(false);
    }

    @FXML
    public void trocarTelaPrincipal(ActionEvent event) throws IOException {
        String usuario = tfUsuarioLogin.getText();
        String senha = pfSenhaLogin.getText();

        if(usuario.isEmpty() || senha.isEmpty()){
            lbPushMsgLogin.setText("Preencha todos os campos!");
            hbPushMsgLogin.getStyleClass().setAll("push-msg-error");
            hbPushMsgLogin.setVisible(true);
        }else{
            try {
                app.getServer().checarLogin(usuario, senha);
            } catch (IOException e) {
                lbPushMsgLogin.setText("Erro ao buscar usuário...");
                hbPushMsgLogin.getStyleClass().setAll("push-msg-error");
                hbPushMsgLogin.setVisible(true);
            } catch (UsuarioNaoExisteException e) {
                lbPushMsgLogin.setText(e.getMessage());
                hbPushMsgLogin.getStyleClass().setAll("push-msg-error");
                hbPushMsgLogin.setVisible(true);
            } catch (LoginIncorretoException e) {
                lbPushMsgLogin.setText(e.getMessage());
                hbPushMsgLogin.getStyleClass().setAll("push-msg-error");
                hbPushMsgLogin.setVisible(true);
            } catch (UsuarioLogadoComSucessoException e) {
                lbPushMsgLogin.setText(e.getMessage());
                hbPushMsgLogin.getStyleClass().setAll("push-msg-success");
                hbPushMsgLogin.setVisible(true);

                ScreenManager sm = ScreenManager.getInstance();

                if(e.getFxml().equals("cliente-aba-catalogo.fxml")){
                    sm.getClienteAbaCatalogoController().setUsuarioLogado(e.getUsuario());
                }

                new Thread(() -> {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ev) {
                        ev.printStackTrace();
                    }
                    Platform.runLater(() -> {
                        this.resetarTela();
                        System.out.println(e.getFxml());
                        sm.changeScene(e.getFxml(), e.getTitle());
                    });
                }).start();

            }
        }
    }

    @FXML
    public void trocarTelaCadastroConta(MouseEvent event){
        this.resetarTela();
        ScreenManager sm = ScreenManager.getInstance();
        sm.changeScene("cadastro-conta.fxml", "Loocalize - Cadastro");
    }

    public void resetarTela(){
        hbPushMsgLogin.setVisible(false);
        tfUsuarioLogin.setText("");
        pfSenhaLogin.setText("");
    }

}

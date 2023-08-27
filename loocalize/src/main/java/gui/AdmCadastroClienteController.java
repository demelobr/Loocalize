package gui;

import exception.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.time.LocalDate;

public class AdmCadastroClienteController {
    Application app;

    @FXML
    private HBox btnFecharCadastroCliente;

    @FXML
    private Button btnSalvarCadastroCliente;

    @FXML
    private Label btnVoltarCadastroCliente;

    @FXML
    private DatePicker dpDataDeHabilitacaoCadastroCliente;

    @FXML
    private DatePicker dpDataDeNascimentoCadastroCliente;

    @FXML
    private HBox hbPushMsgCadastroCliente;

    @FXML
    private Label lbPushMsgCadastroCliente;

    @FXML
    private PasswordField pfRepetirSenhaCadastroCliente;

    @FXML
    private PasswordField pfSenhaCadastroCliente;

    @FXML
    private TextField tfCnhCadastroCliente;

    @FXML
    private TextField tfCpfCadastroCliente;

    @FXML
    private TextField tfEmailCadastroCliente;

    @FXML
    private TextField tfEnderecoCadastroCliente;

    @FXML
    private TextField tfNomeCadastroCliente;

    @FXML
    private TextField tfTelefoneCadastroCliente;

    @FXML
    private TextField tfUsuarioCadastroCliente;


    public AdmCadastroClienteController(){
        this.app = new Application();
    }

    @FXML
    public void initialize(){
        hbPushMsgCadastroCliente.setVisible(false);
        dpDataDeNascimentoCadastroCliente.setValue(LocalDate.now());
        dpDataDeHabilitacaoCadastroCliente.setValue(LocalDate.now());
    }

    @FXML
    public void fecharPushMsg(MouseEvent event) {
        hbPushMsgCadastroCliente.setVisible(false);
    }

    @FXML
    public void cadastrarCliente(){
        String usuario = tfUsuarioCadastroCliente.getText();
        String email = tfEmailCadastroCliente.getText();
        String senha = pfSenhaCadastroCliente.getText();
        String senhaRepitida = pfRepetirSenhaCadastroCliente.getText();
        String nome = tfNomeCadastroCliente.getText();
        LocalDate dataDeNascimento = dpDataDeNascimentoCadastroCliente.getValue();
        String endereco = tfEnderecoCadastroCliente.getText();
        String telefone = tfTelefoneCadastroCliente.getText();
        String cpf = tfCpfCadastroCliente.getText();
        String cnh = tfCnhCadastroCliente.getText();
        LocalDate dataDeHabilitacao = dpDataDeHabilitacaoCadastroCliente.getValue();

        if(!usuario.isEmpty() && !email.isEmpty() && !senha.isEmpty() && !senhaRepitida.isEmpty() && !nome.isEmpty() && dataDeNascimento != null && !endereco.isEmpty() && !telefone.isEmpty() && !cpf.isEmpty() && !cnh.isEmpty() && dataDeHabilitacao != null){
            try {
                app.getServer().checarDadosDaNovaConta(usuario, email, senha, senhaRepitida);
                app.getServer().checarDadosPessoais(nome, dataDeNascimento, telefone, endereco, cpf, cnh, dataDeHabilitacao);
            } catch (UsuarioExisteException e) {
                lbPushMsgCadastroCliente.setText(e.getMessage());
                hbPushMsgCadastroCliente.getStyleClass().setAll("push-msg-error");
                hbPushMsgCadastroCliente.setVisible(true);
            } catch (EmailInvalidoException e) {
                lbPushMsgCadastroCliente.setText(e.getMessage());
                hbPushMsgCadastroCliente.getStyleClass().setAll("push-msg-error");
                hbPushMsgCadastroCliente.setVisible(true);
            } catch (SenhasDiferentesException e) {
                lbPushMsgCadastroCliente.setText(e.getMessage());
                hbPushMsgCadastroCliente.getStyleClass().setAll("push-msg-error");
                hbPushMsgCadastroCliente.setVisible(true);
            } catch (AnosDeHabilitacaoInsuficientesException e) {
                lbPushMsgCadastroCliente.setText(e.getMessage());
                hbPushMsgCadastroCliente.getStyleClass().setAll("push-msg-error");
                hbPushMsgCadastroCliente.setVisible(true);
            } catch (CampoVazioException e) {
                lbPushMsgCadastroCliente.setText(e.getMessage());
                hbPushMsgCadastroCliente.getStyleClass().setAll("push-msg-error");
                hbPushMsgCadastroCliente.setVisible(true);
            } catch (TelefoneInvalidoException e) {
                lbPushMsgCadastroCliente.setText(e.getMessage());
                hbPushMsgCadastroCliente.getStyleClass().setAll("push-msg-error");
                hbPushMsgCadastroCliente.setVisible(true);
            } catch (DataDeHabilitacaoInvalidaException e) {
                lbPushMsgCadastroCliente.setText(e.getMessage());
                hbPushMsgCadastroCliente.getStyleClass().setAll("push-msg-error");
                hbPushMsgCadastroCliente.setVisible(true);
            } catch (UsuarioNuloException e) {
                lbPushMsgCadastroCliente.setText(e.getMessage());
                hbPushMsgCadastroCliente.getStyleClass().setAll("push-msg-error");
                hbPushMsgCadastroCliente.setVisible(true);
            } catch (UsuarioMenorDeIdadeException e) {
                lbPushMsgCadastroCliente.setText(e.getMessage());
                hbPushMsgCadastroCliente.getStyleClass().setAll("push-msg-error");
                hbPushMsgCadastroCliente.setVisible(true);
            } catch (UsuarioInseridoComSucessoException e) {
                lbPushMsgCadastroCliente.setText(e.getMessage());
                hbPushMsgCadastroCliente.getStyleClass().setAll("push-msg-success");
                hbPushMsgCadastroCliente.setVisible(true);

                new Thread(() -> {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ev) {
                        ev.printStackTrace();
                    }
                    Platform.runLater(() -> {
                        this.resetarTela();
                        ScreenManager sm = ScreenManager.getInstance();
                        sm.getAdmAbaClientesController().atualizaColaboradoresDaTabela();
                        sm.changeScene("adm-aba-clientes.fxml", "Loocalize - Adm");
                    });
                }).start();
            } catch (CpfInvalidoException e) {
                lbPushMsgCadastroCliente.setText(e.getMessage());
                hbPushMsgCadastroCliente.getStyleClass().setAll("push-msg-error");
                hbPushMsgCadastroCliente.setVisible(true);
            } catch (DataDeNascimentoInvalidaException e) {
                lbPushMsgCadastroCliente.setText(e.getMessage());
                hbPushMsgCadastroCliente.getStyleClass().setAll("push-msg-error");
                hbPushMsgCadastroCliente.setVisible(true);
            }
        }else{
            lbPushMsgCadastroCliente.setText("Preencha todos os campos!");
            hbPushMsgCadastroCliente.getStyleClass().setAll("push-msg-error");
            hbPushMsgCadastroCliente.setVisible(true);
        }
    }

    @FXML
    public void trocarAdmAbaClientes(){
        this.resetarTela();
        ScreenManager sm = ScreenManager.getInstance();
        sm.changeScene("adm-aba-clientes.fxml", "Loocalize - Adm");
    }

    public void resetarTela(){
        hbPushMsgCadastroCliente.setVisible(false);
        tfUsuarioCadastroCliente.setText("");
        tfEmailCadastroCliente.setText("");
        pfSenhaCadastroCliente.setText("");
        pfRepetirSenhaCadastroCliente.setText("");
        tfNomeCadastroCliente.setText("");
        dpDataDeNascimentoCadastroCliente.setValue(LocalDate.now());
        tfEnderecoCadastroCliente.setText("");
        tfTelefoneCadastroCliente.setText("");
        tfCpfCadastroCliente.setText("");
        tfCnhCadastroCliente.setText("");
        dpDataDeHabilitacaoCadastroCliente.setValue(LocalDate.now());
    }

}

package gui;

import exception.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import models.Colaborador;

import java.time.LocalDate;

public class AdmCadastroColaboradorController {
    Application app;

    @FXML
    private HBox btnFecharCadastroColaborador;

    @FXML
    private Button btnSalvarCadastroColaborador;

    @FXML
    private Label btnVoltarCadastroColaborador;

    @FXML
    private DatePicker dpDataDeNascimentoCadastroColaborador;

    @FXML
    private HBox hbPushMsgCadastroColaborador;

    @FXML
    private Label lbPushMsgCadastroColaborador;

    @FXML
    private PasswordField pfRepetirSenhaCadastroColaborador;

    @FXML
    private PasswordField pfSenhaCadastroColaborador;

    @FXML
    private TextField tfCpfCadastroColaborador;

    @FXML
    private TextField tfEmailCadastroColaborador;

    @FXML
    private TextField tfEnderecoCadastroColaborador;

    @FXML
    private TextField tfNomeCadastroColaborador;

    @FXML
    private TextField tfTelefoneCadastroColaborador;

    @FXML
    private TextField tfUsuarioCadastroColaborador;

    public AdmCadastroColaboradorController(){
        this.app = new Application();
    }

    @FXML
    public void initialize(){
        hbPushMsgCadastroColaborador.setVisible(false);
        dpDataDeNascimentoCadastroColaborador.setValue(LocalDate.now());
    }

    @FXML
    public void fecharPushMsg(MouseEvent event) {
        hbPushMsgCadastroColaborador.setVisible(false);
    }

    @FXML
    public void cadastrarColaborador(){
        String usuario = tfUsuarioCadastroColaborador.getText();
        String email = tfEmailCadastroColaborador.getText();
        String senha = pfSenhaCadastroColaborador.getText();
        String senhaRepetida = pfRepetirSenhaCadastroColaborador.getText();
        String nome = tfNomeCadastroColaborador.getText();
        LocalDate dataDeNascimento = dpDataDeNascimentoCadastroColaborador.getValue();
        String endereco = tfEnderecoCadastroColaborador.getText();
        String telefone = tfTelefoneCadastroColaborador.getText();
        String cpf = tfCpfCadastroColaborador.getText();

        if(!usuario.isEmpty() && !email.isEmpty() && !senha.isEmpty() && !senhaRepetida.isEmpty() && !nome.isEmpty() && dataDeNascimento != null && !endereco.isEmpty() && !telefone.isEmpty() && !cpf.isEmpty()){
            try {
                app.getServer().checarDadosDaNovaConta(usuario, email, senha, senhaRepetida);
                if(dataDeNascimento.isBefore(LocalDate.now())){
                    if(app.getServer().telefoneValido(telefone)){
                        if(app.getServer().cpfValido(cpf)){
                            app.getServer().inserirColaborador(new Colaborador(usuario, senha, email, nome, cpf, dataDeNascimento, telefone, endereco, false));
                        }else{
                            lbPushMsgCadastroColaborador.setText("CPF inválido!");
                            hbPushMsgCadastroColaborador.getStyleClass().setAll("push-msg-error");
                            hbPushMsgCadastroColaborador.setVisible(true);
                        }
                    }else{
                        lbPushMsgCadastroColaborador.setText("Telefone inválido!");
                        hbPushMsgCadastroColaborador.getStyleClass().setAll("push-msg-error");
                        hbPushMsgCadastroColaborador.setVisible(true);
                    }
                }else{
                    lbPushMsgCadastroColaborador.setText("Data de nascimento inválida!");
                    hbPushMsgCadastroColaborador.getStyleClass().setAll("push-msg-error");
                    hbPushMsgCadastroColaborador.setVisible(true);
                }
            } catch (UsuarioExisteException e) {
                lbPushMsgCadastroColaborador.setText(e.getMessage());
                hbPushMsgCadastroColaborador.getStyleClass().setAll("push-msg-error");
                hbPushMsgCadastroColaborador.setVisible(true);
            } catch (EmailInvalidoException e) {
                lbPushMsgCadastroColaborador.setText(e.getMessage());
                hbPushMsgCadastroColaborador.getStyleClass().setAll("push-msg-error");
                hbPushMsgCadastroColaborador.setVisible(true);
            } catch (SenhasDiferentesException e) {
                lbPushMsgCadastroColaborador.setText(e.getMessage());
                hbPushMsgCadastroColaborador.getStyleClass().setAll("push-msg-error");
                hbPushMsgCadastroColaborador.setVisible(true);
            } catch (AnosDeHabilitacaoInsuficientesException e) {
                lbPushMsgCadastroColaborador.setText(e.getMessage());
                hbPushMsgCadastroColaborador.getStyleClass().setAll("push-msg-error");
                hbPushMsgCadastroColaborador.setVisible(true);
            } catch (CampoVazioException e) {
                lbPushMsgCadastroColaborador.setText(e.getMessage());
                hbPushMsgCadastroColaborador.getStyleClass().setAll("push-msg-error");
                hbPushMsgCadastroColaborador.setVisible(true);
            } catch (UsuarioNuloException e) {
                lbPushMsgCadastroColaborador.setText(e.getMessage());
                hbPushMsgCadastroColaborador.getStyleClass().setAll("push-msg-error");
                hbPushMsgCadastroColaborador.setVisible(true);
            } catch (UsuarioMenorDeIdadeException e) {
                lbPushMsgCadastroColaborador.setText(e.getMessage());
                hbPushMsgCadastroColaborador.getStyleClass().setAll("push-msg-error");
                hbPushMsgCadastroColaborador.setVisible(true);
            } catch (DataDeNascimentoInvalidaException e) {
                lbPushMsgCadastroColaborador.setText(e.getMessage());
                hbPushMsgCadastroColaborador.getStyleClass().setAll("push-msg-error");
                hbPushMsgCadastroColaborador.setVisible(true);
            } catch (UsuarioInseridoComSucessoException e) {
                lbPushMsgCadastroColaborador.setText(e.getMessage());
                hbPushMsgCadastroColaborador.getStyleClass().setAll("push-msg-success");
                hbPushMsgCadastroColaborador.setVisible(true);

                System.out.println(e.getMessage());

                new Thread(() -> {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ev) {
                        ev.printStackTrace();
                    }
                    Platform.runLater(() -> {
                        this.resetarTela();
                        ScreenManager sm = ScreenManager.getInstance();
                        sm.getAdmAbaColaboresController().atualizaColaboradoresDaTabela();
                        sm.changeScene("adm-aba-colaboradores.fxml", "Loocalize - Adm");
                    });
                }).start();
            }
        }else{
            lbPushMsgCadastroColaborador.setText("Preencha todos os campos!");
            hbPushMsgCadastroColaborador.getStyleClass().setAll("push-msg-error");
            hbPushMsgCadastroColaborador.setVisible(true);
        }
    }

    @FXML
    public void trocarAdmAbaColaboradores(){
        this.resetarTela();
        ScreenManager sm = ScreenManager.getInstance();
        sm.changeScene("adm-aba-colaboradores.fxml", "Loocalize - Adm");
    }

    public void resetarTela(){
        hbPushMsgCadastroColaborador.setVisible(false);
        tfUsuarioCadastroColaborador.setText("");
        tfEmailCadastroColaborador.setText("");
        pfSenhaCadastroColaborador.setText("");
        pfRepetirSenhaCadastroColaborador.setText("");
        tfNomeCadastroColaborador.setText("");
        dpDataDeNascimentoCadastroColaborador.setValue(LocalDate.now());
        tfEnderecoCadastroColaborador.setText("");
        tfTelefoneCadastroColaborador.setText("");
        tfCpfCadastroColaborador.setText("");
    }

}

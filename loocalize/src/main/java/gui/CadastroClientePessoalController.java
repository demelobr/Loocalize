package gui;

import business.ControladorUsuarios;
import exception.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import models.Cliente;

import java.time.LocalDate;

public class CadastroClientePessoalController {
    private static CadastroClientePessoalController instance;
    Application app;
    private Cliente novoCliente;

    @FXML
    private Button btnCadastrarCadastroPessoal;

    @FXML
    private HBox btnFecharCadastroPessoal;

    @FXML
    private Button btnVoltar;

    @FXML
    private DatePicker dpDataDeHabilitacaoCadastroPessoal;

    @FXML
    private DatePicker dpDataDeNascimentoCadastroPessoal;

    @FXML
    private HBox hbPushMsgCadastroPessoal;

    @FXML
    private Label lbPushMsgCadastroPessoal;

    @FXML
    private TextField tfCpfCadastroPessoal;

    @FXML
    private TextField tfEnderecoCadastroPessoal;

    @FXML
    private TextField tfNomeCadastroPessoal;

    @FXML
    private TextField tfTelefoneCadastroPessoal;

    @FXML
    private TextField tfCnhCadastroPessoal;

    public CadastroClientePessoalController(){
        this.app = new Application();
    }

    public static CadastroClientePessoalController getInstance(){
        if(instance == null){
            instance = new CadastroClientePessoalController();
        }
        return instance;
    }

    public Cliente getNovoCliente() {
        return novoCliente;
    }

    public void setNovoCliente(Cliente novoCliente) {
        this.novoCliente = novoCliente;
    }

    @FXML
    public void initialize(){
        hbPushMsgCadastroPessoal.setVisible(false);
        dpDataDeNascimentoCadastroPessoal.setValue(LocalDate.now());
        dpDataDeHabilitacaoCadastroPessoal.setValue(LocalDate.now());
    }

    @FXML
    public void fecharPushMsg(MouseEvent event){
        hbPushMsgCadastroPessoal.setVisible(false);
    }

    @FXML
    public void trocarTelaCadastroConta(MouseEvent event) {
        ScreenManager sm = ScreenManager.getInstance();
        sm.changeScene("cadastro-conta.fxml", "Loocalize - Cadastro");
    }

    @FXML void trocarTelaLogin(ActionEvent event) {
        String nome = tfNomeCadastroPessoal.getText();
        LocalDate dataDeNascimento = dpDataDeNascimentoCadastroPessoal.getValue();
        String telefone = tfTelefoneCadastroPessoal.getText();
        String endereco = tfEnderecoCadastroPessoal.getText();
        String cpf = tfCpfCadastroPessoal.getText();
        String cnh = tfCnhCadastroPessoal.getText();
        LocalDate dataDeHabilitacao = dpDataDeHabilitacaoCadastroPessoal.getValue();

        if(nome.isEmpty() || telefone.isEmpty() || endereco.isEmpty() || cpf.isEmpty() || cnh.isEmpty()){
            lbPushMsgCadastroPessoal.setText("Preencha todos os campos!");
            hbPushMsgCadastroPessoal.getStyleClass().setAll("push-msg-error");
            hbPushMsgCadastroPessoal.setVisible(true);
        }else{
            try {
                app.getServer().checarDadosPessoais(nome, dataDeNascimento, telefone, endereco, cpf, cnh, dataDeHabilitacao);
                ScreenManager sm = ScreenManager.getInstance();
                sm.changeScene("login.fxml", "Loocalize - Login");
            } catch (TelefoneInvalidoException e) {
                lbPushMsgCadastroPessoal.setText(e.getMessage());
                hbPushMsgCadastroPessoal.getStyleClass().setAll("push-msg-error");
                hbPushMsgCadastroPessoal.setVisible(true);
            } catch (DataDeHabilitacaoInvalidaException e) {
                lbPushMsgCadastroPessoal.setText(e.getMessage());
                hbPushMsgCadastroPessoal.getStyleClass().setAll("push-msg-error");
                hbPushMsgCadastroPessoal.setVisible(true);
            } catch (CpfInvalidoException e) {
                lbPushMsgCadastroPessoal.setText(e.getMessage());
                hbPushMsgCadastroPessoal.getStyleClass().setAll("push-msg-error");
                hbPushMsgCadastroPessoal.setVisible(true);
            } catch (DataDeNascimentoInvalidaException e) {
                lbPushMsgCadastroPessoal.setText(e.getMessage());
                hbPushMsgCadastroPessoal.getStyleClass().setAll("push-msg-error");
                hbPushMsgCadastroPessoal.setVisible(true);
            } catch (AnosDeHabilitacaoInsuficientesException e) {
                lbPushMsgCadastroPessoal.setText(e.getMessage());
                hbPushMsgCadastroPessoal.getStyleClass().setAll("push-msg-error");
                hbPushMsgCadastroPessoal.setVisible(true);
            } catch (UsuarioExisteException e) {
                lbPushMsgCadastroPessoal.setText(e.getMessage());
                hbPushMsgCadastroPessoal.getStyleClass().setAll("push-msg-error");
                hbPushMsgCadastroPessoal.setVisible(true);
            } catch (CampoVazioException e) {
                lbPushMsgCadastroPessoal.setText(e.getMessage());
                hbPushMsgCadastroPessoal.getStyleClass().setAll("push-msg-error");
                hbPushMsgCadastroPessoal.setVisible(true);
            } catch (UsuarioNuloException e) {
                lbPushMsgCadastroPessoal.setText(e.getMessage());
                hbPushMsgCadastroPessoal.getStyleClass().setAll("push-msg-error");
                hbPushMsgCadastroPessoal.setVisible(true);
            } catch (UsuarioMenorDeIdadeException e) {
                lbPushMsgCadastroPessoal.setText(e.getMessage());
                hbPushMsgCadastroPessoal.getStyleClass().setAll("push-msg-error");
                hbPushMsgCadastroPessoal.setVisible(true);
            }
        }
    }

}

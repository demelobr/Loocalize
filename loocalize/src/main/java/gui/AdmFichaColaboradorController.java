package gui;

import exception.UsuarioEditadoComSucessoException;
import exception.UsuarioNaoExisteException;
import exception.UsuarioNuloException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import models.Colaborador;

import java.time.LocalDate;

public class AdmFichaColaboradorController {
    Application app;
    Colaborador colaboradorEditado;

    @FXML
    private HBox btnFecharEditarColaborador;

    @FXML
    private Button btnSalvarEditarColaborador;

    @FXML
    private Label btnVoltarEditarColaborador;

    @FXML
    private DatePicker dpDataDeNascimentoEditarColaborador;

    @FXML
    private HBox hbPushMsgEditarColaborador;

    @FXML
    private Label lbPushMsgEditarColaborador;

    @FXML
    private TextField tfCpfEditarColaborador;

    @FXML
    private TextField tfEmailEditarColaborador;

    @FXML
    private TextField tfEnderecoEditarColaborador;

    @FXML
    private TextField tfTelefoneEditarColaborador;

    @FXML
    private TextField tfUsuarioEditarColaborador;

    public AdmFichaColaboradorController(){
        this.app = new Application();
    }

    public Colaborador getColaboradorEditado() {
        return colaboradorEditado;
    }

    public void setColaboradorEditado(Colaborador colaboradorEditado) {
        this.colaboradorEditado = colaboradorEditado;
    }

    @FXML
    public void initialize(){
        hbPushMsgEditarColaborador.setVisible(false);
    }

    @FXML
    public void fecharPushMsg(MouseEvent event) {
        hbPushMsgEditarColaborador.setVisible(false);
    }

    @FXML
    public void salvarEdicaoColaborador(){
        String usuario = tfUsuarioEditarColaborador.getText();
        String email = tfEmailEditarColaborador.getText();
        String cpf = tfCpfEditarColaborador.getText();
        LocalDate dataDeNascimento = dpDataDeNascimentoEditarColaborador.getValue();
        String endereco = tfEnderecoEditarColaborador.getText();
        String telefone = tfTelefoneEditarColaborador.getText();

        if(dataDeNascimento == null) dataDeNascimento = colaboradorEditado.getDataNascimento();

        try {
            app.getServer().editarColaborador(colaboradorEditado, usuario, email, cpf, dataDeNascimento, endereco, telefone);
        } catch (UsuarioNaoExisteException e) {
            lbPushMsgEditarColaborador.setText(e.getMessage());
            hbPushMsgEditarColaborador.getStyleClass().setAll("push-msg-error");
            hbPushMsgEditarColaborador.setVisible(true);
        } catch (UsuarioNuloException e) {
            lbPushMsgEditarColaborador.setText(e.getMessage());
            hbPushMsgEditarColaborador.getStyleClass().setAll("push-msg-error");
            hbPushMsgEditarColaborador.setVisible(true);
        } catch (UsuarioEditadoComSucessoException e) {
            lbPushMsgEditarColaborador.setText(e.getMessage());
            hbPushMsgEditarColaborador.getStyleClass().setAll("push-msg-success");
            hbPushMsgEditarColaborador.setVisible(true);

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
    }

    @FXML
    public void trocarAbaClientes(MouseEvent event) {
        this.resetarTela();
        ScreenManager sm = ScreenManager.getInstance();
        sm.changeScene("adm-aba-clientes.fxml", "Loocalize - Adm");
    }

    public void setarDadosDoColaboradorEditado(){
        tfUsuarioEditarColaborador.setPromptText(colaboradorEditado.getUsuario());
        tfEmailEditarColaborador.setPromptText(colaboradorEditado.getEmail());
        tfCpfEditarColaborador.setPromptText(colaboradorEditado.getCpf());
        dpDataDeNascimentoEditarColaborador.setValue(colaboradorEditado.getDataNascimento());
        tfEnderecoEditarColaborador.setPromptText(colaboradorEditado.getEndereco());
        tfTelefoneEditarColaborador.setPromptText(colaboradorEditado.getTelefone());
    }

    public void resetarTela(){
        hbPushMsgEditarColaborador.setVisible(false);
        tfUsuarioEditarColaborador.setText("");
        tfEmailEditarColaborador.setText("");
        tfCpfEditarColaborador.setText("");
        dpDataDeNascimentoEditarColaborador.setValue(LocalDate.now());
        tfEnderecoEditarColaborador.setText("");
        tfTelefoneEditarColaborador.setText("");
    }

}

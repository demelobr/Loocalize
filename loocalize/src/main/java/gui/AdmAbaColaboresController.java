package gui;

import exception.UsuarioNaoExisteException;
import exception.UsuarioNuloException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import models.Colaborador;

import java.net.URL;
import java.util.ResourceBundle;

public class AdmAbaColaboresController implements Initializable {
    Application app;
    ObservableList<Colaborador> listaDeColaboradores;

    @FXML
    private Button btnAdicionarAbaColaboradores;

    @FXML
    private Button btnDeletarAbaColaboradores;

    @FXML
    private Button btnDetalharAbaColaboradores;

    @FXML
    private Button btnEditarAbaColaboradores;

    @FXML
    private HBox btnFecharAbaColaboradores;

    @FXML
    private HBox hbBtnClientesAbaColaboradores;

    @FXML
    private HBox hbBtnColaboradoresAbaColaboradores;

    @FXML
    private HBox hbPushMsgAbaColaboradores;

    @FXML
    private HBox hbSairAbaColaboradores;

    @FXML
    private Label lbCpfAbaColaboradores;

    @FXML
    private Label lbDataDeNascimentoAbaColaboradores;

    @FXML
    private Label lbDataHoraAbaColaboradores;

    @FXML
    private Label lbEmailAbaColaboradores;

    @FXML
    private Label lbEnderecoAbaColaboradores;

    @FXML
    private Label lbIdAbaColaboradores;

    @FXML
    private Label lbNomeAbaColaboradores;

    @FXML
    private Label lbPushMsgAbaColaboradores;

    @FXML
    private Label lbTelefoneAbaColaboradores;

    @FXML
    private Label lbUsuarioAbaColaboradores;

    @FXML
    private TableColumn<Colaborador, String> tcEmailAbaColaboradores;

    @FXML
    private TableColumn<Colaborador, String> tcIdAbaColaboradores;

    @FXML
    private TableColumn<Colaborador, String> tcNomeAbaColaboradores;

    @FXML
    private TableColumn<Colaborador, String> tcUsuarioAbaColaboradores;

    @FXML
    private TableView<Colaborador> tvColaboradoresAbaColaboradores;

    public AdmAbaColaboresController(){
        this.app = new Application();
    }

    public ObservableList<Colaborador> getListaDeColaboradores() {
        return listaDeColaboradores;
    }

    @FXML
    public void fecharPushMsg(MouseEvent event) {
        hbPushMsgAbaColaboradores.setVisible(false);
    }

    @FXML
    public void adicionarColaborador(ActionEvent event){
        ScreenManager sm = ScreenManager.getInstance();
        sm.changeScene("adm-cadastro-colaborador.fxml", "Loocalize - Cadastrar Colaborador");
    }

    @FXML
    public void detalharColaborador(ActionEvent event){
        Colaborador colaboradorSelecionado = tvColaboradoresAbaColaboradores.getSelectionModel().getSelectedItem();
        if(colaboradorSelecionado != null){
            lbIdAbaColaboradores.setText(colaboradorSelecionado.getId());
            lbUsuarioAbaColaboradores.setText(colaboradorSelecionado.getUsuario());
            lbEmailAbaColaboradores.setText(colaboradorSelecionado.getEmail());
            lbNomeAbaColaboradores.setText(colaboradorSelecionado.getNomeCompleto());
            lbCpfAbaColaboradores.setText(colaboradorSelecionado.getCpf());
            lbDataDeNascimentoAbaColaboradores.setText(colaboradorSelecionado.getDataNascimento().toString());
            lbTelefoneAbaColaboradores.setText(colaboradorSelecionado.getTelefone());
            lbEnderecoAbaColaboradores.setText(colaboradorSelecionado.getEndereco());
        }else{
            lbPushMsgAbaColaboradores.setText("Selecione um colaborador!");
            hbPushMsgAbaColaboradores.getStyleClass().setAll("push-msg-error");
            hbPushMsgAbaColaboradores.setVisible(true);
        }
    }

    @FXML
    public void editarColaborador(ActionEvent event){
        Colaborador colaboradorSelecionado = tvColaboradoresAbaColaboradores.getSelectionModel().getSelectedItem();
        if(colaboradorSelecionado != null){
            ScreenManager sm = ScreenManager.getInstance();
            sm.getAdmFichaColaboradorController().setColaboradorEditado(colaboradorSelecionado);
            sm.getAdmFichaColaboradorController().setarDadosDoColaboradorEditado();
            sm.changeScene("adm-ficha-colaborador.fxml", "Loocalize - Editar Colaborador");
        }else{
            lbPushMsgAbaColaboradores.setText("Selecione um colaborador!");
            hbPushMsgAbaColaboradores.getStyleClass().setAll("push-msg-error");
            hbPushMsgAbaColaboradores.setVisible(true);
        }
    }

    @FXML
    public void deletarColaborador(ActionEvent event){
        Colaborador colaboradorSelecionado = tvColaboradoresAbaColaboradores.getSelectionModel().getSelectedItem();
        if(colaboradorSelecionado != null){
            try {
                app.getServer().deletarColaborador(colaboradorSelecionado);
            } catch (UsuarioNaoExisteException e) {
                lbPushMsgAbaColaboradores.setText("Colaborador não existe!");
                hbPushMsgAbaColaboradores.getStyleClass().setAll("push-msg-error");
                hbPushMsgAbaColaboradores.setVisible(true);
            } catch (UsuarioNuloException e) {
                lbPushMsgAbaColaboradores.setText("Colaborador nulo!");
                hbPushMsgAbaColaboradores.getStyleClass().setAll("push-msg-error");
                hbPushMsgAbaColaboradores.setVisible(true);
            }
            int selectedId = tvColaboradoresAbaColaboradores.getSelectionModel().getSelectedIndex();
            tvColaboradoresAbaColaboradores.getItems().remove(selectedId);
        }else{
            lbPushMsgAbaColaboradores.setText("Selecione um colaborador!");
            hbPushMsgAbaColaboradores.getStyleClass().setAll("push-msg-error");
            hbPushMsgAbaColaboradores.setVisible(true);
        }
    }

    @FXML
    public void trocarAbaClientes(MouseEvent event) {
        ScreenManager sm = ScreenManager.getInstance();
        sm.changeScene("adm-aba-clientes.fxml", "Loocalize - Adm");
    }

    @FXML
    public void trocarTelaLogin(MouseEvent event) {
        ScreenManager sm = ScreenManager.getInstance();
        sm.changeScene("login.fxml", "Loocalize - Login");
    }

    public void atualizaColaboradoresDaTabela(){
        for (int i = 0; i < tvColaboradoresAbaColaboradores.getItems().size(); i++) {
            tvColaboradoresAbaColaboradores.getItems().clear();
        }
        this.listaDeColaboradores = FXCollections.observableArrayList(app.getServer().listarTodosColaboradores());
        tvColaboradoresAbaColaboradores.setItems(listaDeColaboradores);
    }

    public void limparCampos(){
        lbIdAbaColaboradores.setText("");
        lbUsuarioAbaColaboradores.setText("");
        lbEmailAbaColaboradores.setText("");
        lbNomeAbaColaboradores.setText("");
        lbCpfAbaColaboradores.setText("");
        lbDataDeNascimentoAbaColaboradores.setText("");
        lbTelefoneAbaColaboradores.setText("");
        lbEnderecoAbaColaboradores.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hbPushMsgAbaColaboradores.setVisible(false);
        tvColaboradoresAbaColaboradores.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
        tcIdAbaColaboradores.setCellValueFactory(new PropertyValueFactory<Colaborador,String>("id"));
        tcNomeAbaColaboradores.setCellValueFactory(new PropertyValueFactory<Colaborador, String>("nomeCompleto"));
        tcUsuarioAbaColaboradores.setCellValueFactory(new PropertyValueFactory<Colaborador,String>("usuario"));
        tcEmailAbaColaboradores.setCellValueFactory(new PropertyValueFactory<Colaborador,String>("email"));
        this.limparCampos();
        this.atualizaColaboradoresDaTabela();
    }

}

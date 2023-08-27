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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import models.Cliente;

import java.net.URL;
import java.util.ResourceBundle;

public class AdmAbaClientesController implements Initializable {
    Application app;
    ObservableList<Cliente> listaDeClientes;

    @FXML
    private Button btnAdicionarAbaClientes;

    @FXML
    private Button btnDeletarAbaClientes;

    @FXML
    private Button btnDetalharAbaClientes;

    @FXML
    private HBox btnFecharAbaClientes;

    @FXML
    private HBox hbBtnClientesAbaClientes;

    @FXML
    private HBox hbPushMsgAbaClientes;

    @FXML
    private HBox hbSairAbaClientes;

    @FXML
    private Label lbDataHoraAbaClientes;

    @FXML
    private Label lbEmailAbaClientes;

    @FXML
    private Label lbEnderecoAbaClientes;

    @FXML
    private Label lbIdAbaClientes;

    @FXML
    private Label lbPushMsgAbaClientes;

    @FXML
    private Label lbTelefoneAbaClientes;

    @FXML
    private Label lbUsuarioAbaClientes;

    @FXML
    private TableColumn<Cliente, String> tcEmailAbaClientes;

    @FXML
    private TableColumn<Cliente, String> tcIdAbaClientes;

    @FXML
    private TableColumn<Cliente, String> tcNomeAbaClientes;

    @FXML
    private TableColumn<Cliente, String> tcUsuarioAbaClientes;

    @FXML
    private TableView<Cliente> tvClientesAbaClientes;

    public AdmAbaClientesController(){
        this.app = new Application();
    }

    public ObservableList<Cliente> getListaDeClientes() {
        return listaDeClientes;
    }

    @FXML
    public void fecharPushMsg(MouseEvent event) {
        hbPushMsgAbaClientes.setVisible(false);
    }

    @FXML
    public void adicionarCliente(ActionEvent event){
        ScreenManager sm = ScreenManager.getInstance();
        sm.changeScene("adm-cadastro-cliente.fxml", "Loocalize - Cadastrar Cliente");
    }

    @FXML
    public void detalharCliente(ActionEvent event){
        Cliente clienteSelecionado = tvClientesAbaClientes.getSelectionModel().getSelectedItem();
        if(clienteSelecionado != null){
            lbIdAbaClientes.setText(clienteSelecionado.getId());
            lbUsuarioAbaClientes.setText(clienteSelecionado.getUsuario());
            lbEmailAbaClientes.setText(clienteSelecionado.getEmail());
            lbTelefoneAbaClientes.setText(clienteSelecionado.getTelefone());
            lbEnderecoAbaClientes.setText(clienteSelecionado.getEndereco());
        }else{
            lbPushMsgAbaClientes.setText("Selecione um cliente!");
            hbPushMsgAbaClientes.getStyleClass().setAll("push-msg-error");
            hbPushMsgAbaClientes.setVisible(true);
        }
    }

    @FXML
    public void deletarCliente(ActionEvent event){
        Cliente clienteSelecionado = tvClientesAbaClientes.getSelectionModel().getSelectedItem();
        if(clienteSelecionado != null){
            try {
                app.getServer().deletarCliente(clienteSelecionado);
            } catch (UsuarioNaoExisteException e) {
                lbPushMsgAbaClientes.setText("Cliente não existe!");
                hbPushMsgAbaClientes.getStyleClass().setAll("push-msg-error");
                hbPushMsgAbaClientes.setVisible(true);
            } catch (UsuarioNuloException e) {
                lbPushMsgAbaClientes.setText("Cliente nulo!");
                hbPushMsgAbaClientes.getStyleClass().setAll("push-msg-error");
                hbPushMsgAbaClientes.setVisible(true);
            }
            int selectedId = tvClientesAbaClientes.getSelectionModel().getSelectedIndex();
            tvClientesAbaClientes.getItems().remove(selectedId);
        }else{
            lbPushMsgAbaClientes.setText("Selecione um cliente!");
            hbPushMsgAbaClientes.getStyleClass().setAll("push-msg-error");
            hbPushMsgAbaClientes.setVisible(true);
        }
    }

    @FXML
    public void trocarAbaColaboradores(MouseEvent event) {
        ScreenManager sm = ScreenManager.getInstance();
        sm.changeScene("adm-aba-colaboradores.fxml", "Loocalize - Adm");
    }

    @FXML
    public void trocarTelaLogin(MouseEvent event) {
        ScreenManager sm = ScreenManager.getInstance();
        sm.changeScene("login.fxml", "Loocalize - Login");
    }

    public void atualizaColaboradoresDaTabela(){
        for (int i = 0; i < tvClientesAbaClientes.getItems().size(); i++) {
            tvClientesAbaClientes.getItems().clear();
        }
        this.listaDeClientes = FXCollections.observableArrayList(app.getServer().listarTodosClientes());
        tvClientesAbaClientes.setItems(listaDeClientes);
    }

    public void limparCampos(){
        lbIdAbaClientes.setText("");
        lbUsuarioAbaClientes.setText("");
        lbEmailAbaClientes.setText("");
        lbTelefoneAbaClientes.setText("");
        lbEnderecoAbaClientes.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hbPushMsgAbaClientes.setVisible(false);
        tvClientesAbaClientes.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
        tcIdAbaClientes.setCellValueFactory(new PropertyValueFactory<Cliente,String>("id"));
        tcNomeAbaClientes.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nome"));
        tcUsuarioAbaClientes.setCellValueFactory(new PropertyValueFactory<Cliente,String>("usuario"));
        tcEmailAbaClientes.setCellValueFactory(new PropertyValueFactory<Cliente,String>("email"));
        this.limparCampos();
        this.atualizaColaboradoresDaTabela();
    }

}

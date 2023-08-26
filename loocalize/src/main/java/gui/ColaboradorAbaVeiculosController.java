package gui;

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import models.Veiculo;

import java.net.URL;
import java.time.Year;
import java.util.ResourceBundle;

public class ColaboradorAbaVeiculosController implements Initializable {
    Application app;
    ObservableList<Veiculo> listaDeVeiculos;

    @FXML
    private Button btnAdicionarAbaVeiculos;

    @FXML
    private Button btnDeletarAbaVeiculos;

    @FXML
    private Button btnEditarAbaVeiculos;

    @FXML
    private HBox btnFecharAbaVeiculos;

    @FXML
    private HBox hbBtnPromocoesAbaVeiculos;

    @FXML
    private HBox hbBtnVeiculosAbaVeiculos;

    @FXML
    private HBox hbPushMsgAbaVeiculos;

    @FXML
    private HBox hbSairAbaVeiculos;

    @FXML
    private ImageView imgCarroAbaVeiculos;

    @FXML
    private Label lbAnoAbaVeiculos;

    @FXML
    private Label lbDataHoraAbaVeiculos;

    @FXML
    private Label lbDiariaAbaVeiculos;

    @FXML
    private Label lbIdAbaVeiculos;

    @FXML
    private Label lbKmAbaVeiculos;

    @FXML
    private Label lbLocacoesAbaVeiculos;

    @FXML
    private Label lbMarcaAbaVeiculos;

    @FXML
    private Label lbModeloAbaVeiculos;

    @FXML
    private Label lbPlacaAbaVeiculos;

    @FXML
    private Label lbPushMsgAbaVeiculos;

    @FXML
    private TableColumn<Veiculo, Year> tcAnoAbaVeiculos;

    @FXML
    private TableColumn<Veiculo, Double> tcDiariaAbaVeiculos;

    @FXML
    private TableColumn<Veiculo, String> tcIdAbaVeiculos;

    @FXML
    private TableColumn<Veiculo, String> tcMarcaAbaVeiculos;

    @FXML
    private TableColumn<Veiculo, String> tcPlacaAbaVeiculos;

    @FXML
    private TableView<Veiculo> tvVeiculosAbaVeiculos;

    public ColaboradorAbaVeiculosController(){
        this.app = new Application();
    }

    public ObservableList<Veiculo> getListaDeVeiculos() {
        return listaDeVeiculos;
    }

    @FXML
    public void fecharPushMsg(MouseEvent event) {
        hbPushMsgAbaVeiculos.setVisible(false);
    }

    @FXML
    public void adicionarVeiculo(ActionEvent event){
        ScreenManager sm = ScreenManager.getInstance();
        sm.changeScene("colaborador-cadastro-veiculo.fxml", "Loocalize - Cadastrar Veículo");
    }

    @FXML
    public void detalharVeiculo(ActionEvent event){
        Veiculo veiculoSelecionado = tvVeiculosAbaVeiculos.getSelectionModel().getSelectedItem();
        if(veiculoSelecionado != null){
            imgCarroAbaVeiculos.setImage(new Image("file:" + veiculoSelecionado.getFotoDoVeiculo()));
            Rectangle clip = new Rectangle(imgCarroAbaVeiculos.getFitWidth(), imgCarroAbaVeiculos.getFitHeight());
            clip.setArcWidth(10);
            clip.setArcHeight(10);
            imgCarroAbaVeiculos.setClip(clip);
            lbModeloAbaVeiculos.setText(veiculoSelecionado.getModelo());
            lbPlacaAbaVeiculos.setText(veiculoSelecionado.getPlaca());
            lbIdAbaVeiculos.setText(veiculoSelecionado.getId());
            lbMarcaAbaVeiculos.setText(veiculoSelecionado.getMarca());
            lbAnoAbaVeiculos.setText(veiculoSelecionado.getAno().toString());
            lbKmAbaVeiculos.setText(Integer.toString(veiculoSelecionado.getQuilometragem()));
            lbLocacoesAbaVeiculos.setText(Integer.toString(veiculoSelecionado.getQtdDeLocacao()));
            lbDiariaAbaVeiculos.setText("R$ " + String.format("%.2f", veiculoSelecionado.getValorDaDiaria()));
        }else{
            lbPushMsgAbaVeiculos.setText("Selecione um veículo!");
            hbPushMsgAbaVeiculos.getStyleClass().setAll("push-msg-error");
            hbPushMsgAbaVeiculos.setVisible(true);
        }
    }

    @FXML
    public void editarVeiculo(ActionEvent event){
        Veiculo veiculoSelecionado = tvVeiculosAbaVeiculos.getSelectionModel().getSelectedItem();
        if(veiculoSelecionado != null){
            ScreenManager sm = ScreenManager.getInstance();
            sm.getColaboradorFichaVeiculoController().setVeiculoEditado(veiculoSelecionado);
            sm.getColaboradorFichaVeiculoController().setarDadosDoCarroEditado();
            sm.changeScene("colaborador-ficha-veiculo.fxml", "Loocalize - Editar Veículo");
        }else{
            lbPushMsgAbaVeiculos.setText("Selecione um veículo!");
            hbPushMsgAbaVeiculos.getStyleClass().setAll("push-msg-error");
            hbPushMsgAbaVeiculos.setVisible(true);
        }
    }

    @FXML
    public void deletarVeiculo(ActionEvent event){
        Veiculo veiculoSelecionado = tvVeiculosAbaVeiculos.getSelectionModel().getSelectedItem();
        if(veiculoSelecionado != null){
            app.getServer().deletarVeiculo(veiculoSelecionado);
            int selectedId = tvVeiculosAbaVeiculos.getSelectionModel().getSelectedIndex();
            tvVeiculosAbaVeiculos.getItems().remove(selectedId);
        }else{
            lbPushMsgAbaVeiculos.setText("Selecione um veículo!");
            hbPushMsgAbaVeiculos.getStyleClass().setAll("push-msg-error");
            hbPushMsgAbaVeiculos.setVisible(true);
        }
    }

    @FXML
    public void trocarAbaPromocoes(MouseEvent event) {
        ScreenManager sm = ScreenManager.getInstance();
        sm.changeScene("colaborador-aba-promocoes.fxml", "Loocalize - Promoções");
    }

    @FXML
    public void trocarTelaLogin(MouseEvent event) {
        ScreenManager sm = ScreenManager.getInstance();
        sm.changeScene("login.fxml", "Loocalize - Login");
    }

    public void atualizaVeiculosDaTabela(){
        for (int i = 0; i < tvVeiculosAbaVeiculos.getItems().size(); i++) {
            tvVeiculosAbaVeiculos.getItems().clear();
        }
        this.listaDeVeiculos = FXCollections.observableArrayList(app.getServer().listarTodosVeiculos());
        tvVeiculosAbaVeiculos.setItems(listaDeVeiculos);
    }

    public void limparCampos(){
        lbModeloAbaVeiculos.setText("");
        lbPlacaAbaVeiculos.setText("");
        lbIdAbaVeiculos.setText("");
        lbMarcaAbaVeiculos.setText("");
        lbAnoAbaVeiculos.setText("");
        lbKmAbaVeiculos.setText("");
        lbLocacoesAbaVeiculos.setText("");
        lbDiariaAbaVeiculos.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hbPushMsgAbaVeiculos.setVisible(false);
        tvVeiculosAbaVeiculos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
        tcIdAbaVeiculos.setCellValueFactory(new PropertyValueFactory<Veiculo,String>("id"));
        tcMarcaAbaVeiculos.setCellValueFactory(new PropertyValueFactory<Veiculo,String>("marca"));
        tcPlacaAbaVeiculos.setCellValueFactory(new PropertyValueFactory<Veiculo,String>("placa"));
        tcAnoAbaVeiculos.setCellValueFactory(new PropertyValueFactory<Veiculo,Year>("ano"));
        tcDiariaAbaVeiculos.setCellValueFactory(new PropertyValueFactory<Veiculo,Double>("valorDaDiaria"));
        this.limparCampos();
        this.atualizaVeiculosDaTabela();
    }
}

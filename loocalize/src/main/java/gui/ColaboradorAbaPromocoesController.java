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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import models.Promocao;
import models.Veiculo;

import java.net.URL;
import java.time.LocalDate;
import java.time.Year;
import java.util.ResourceBundle;

public class ColaboradorAbaPromocoesController implements Initializable {
    Application app;
    ObservableList<Promocao> listaDePromocoes;

    @FXML
    private Button btnAdicionarAbaPromocoes;

    @FXML
    private Button btnDeletarAbaPromocoes;

    @FXML
    private Button btnDetalharAbaPromocoes;

    @FXML
    private Button btnEditarAbaPromocoes;

    @FXML
    private HBox btnFecharAbaPromocoes;

    @FXML
    private HBox hbBtnPromocoesAbaPromocoes;

    @FXML
    private HBox hbBtnVeiculosAbaPromocoes;

    @FXML
    private HBox hbPushMsgAbaPromocoes;

    @FXML
    private HBox hbSairAbaPromocoes;

    @FXML
    private Label lbAtivaAbaPromocoes;

    @FXML
    private Label lbDataDeCriacaoAbaPromocoes;

    @FXML
    private Label lbDataDeExpiracaoAbaPromocoes;

    @FXML
    private Label lbDataHoraAbaPromocoes;

    @FXML
    private Label lbDescontoAbaPromocoes;

    @FXML
    private Label lbIdAbaPromocoes;

    @FXML
    private Label lbPushMsgAbaPromocoes;

    @FXML
    private Label lbQtdDeMinimaDeLocacoesAbaPromocoes;

    @FXML
    private Label lbQtdMinimaDeDiariasAbaPromocoes;

    @FXML
    private Label lbTituloAbaPromocoes;

    @FXML
    private TableColumn<Promocao, Boolean> tcAtivaAbaPromocoes;

    @FXML
    private TableColumn<Promocao, LocalDate> tcDataDeCriacaoAbaPromocoes;

    @FXML
    private TableColumn<Promocao, LocalDate> tcDataDeExpiracaoAbaPromocoes;

    @FXML
    private TableColumn<Promocao, Integer> tcDescontoAbaPromocoes;

    @FXML
    private TableColumn<Promocao, String> tcIdAbaPromocoes;

    @FXML
    private TableColumn<Promocao, String> tcTituloAbaPromocoes;

    @FXML
    private TableView<Promocao> tvPromocoesAbaPromocoes;

    public ColaboradorAbaPromocoesController(){
        this.app = new Application();
    }

    public ObservableList<Promocao> getListaDePromocoes() {
        return listaDePromocoes;
    }

    @FXML
    public void fecharPushMsg(MouseEvent event) {
        hbPushMsgAbaPromocoes.setVisible(false);
    }

    @FXML
    public void adicionarPromocao(ActionEvent event){
        ScreenManager sm = ScreenManager.getInstance();
        sm.changeScene("colaborador-cadastro-promocao.fxml", "Loocalize - Cadastrar Promoção");
    }

    @FXML
    public void detalharPromocao(ActionEvent event){
        Promocao promocaoSelecionado = tvPromocoesAbaPromocoes.getSelectionModel().getSelectedItem();
        if(promocaoSelecionado != null){
            lbIdAbaPromocoes.setText(promocaoSelecionado.getId());
            lbTituloAbaPromocoes.setText(promocaoSelecionado.getTitulo());
            lbDescontoAbaPromocoes.setText(Integer.toString(promocaoSelecionado.getPorcentagemDeDesconto()));
            lbQtdMinimaDeDiariasAbaPromocoes.setText(Integer.toString(promocaoSelecionado.getQtdMinimaDeDiarias()));
            lbQtdDeMinimaDeLocacoesAbaPromocoes.setText(Integer.toString(promocaoSelecionado.getQtdMinimaDeLocacoes()));
            lbDataDeCriacaoAbaPromocoes.setText(promocaoSelecionado.getDataDeCriacao().toString());
            lbDataDeExpiracaoAbaPromocoes.setText(promocaoSelecionado.getDataDeExpiracao().toString());
            lbAtivaAbaPromocoes.setText(Boolean.toString(promocaoSelecionado.isAtiva()));
        }else{
            lbPushMsgAbaPromocoes.setText("Selecione uma promoção!");
            hbPushMsgAbaPromocoes.getStyleClass().setAll("push-msg-error");
            hbPushMsgAbaPromocoes.setVisible(true);
        }
    }

    @FXML
    public void editarPromocao(ActionEvent event){
        Promocao promocaoSelecionada = tvPromocoesAbaPromocoes.getSelectionModel().getSelectedItem();
        if(promocaoSelecionada != null){
            ScreenManager sm = ScreenManager.getInstance();
            sm.getColaboradorFichaPromocaoController().setPromocaoEditada(promocaoSelecionada);
            sm.getColaboradorFichaPromocaoController().setarDadosDaPromocaoEditada();
            sm.changeScene("colaborador-ficha-promocao.fxml", "Loocalize - Editar Promoção");
        }else{
            lbPushMsgAbaPromocoes.setText("Selecione um veículo!");
            hbPushMsgAbaPromocoes.getStyleClass().setAll("push-msg-error");
            hbPushMsgAbaPromocoes.setVisible(true);
        }
    }

    @FXML
    public void deletarPromocao(ActionEvent event){
        Promocao promocaoSelecionado = tvPromocoesAbaPromocoes.getSelectionModel().getSelectedItem();
        if(promocaoSelecionado != null){
            app.getServer().deletarPromocao(promocaoSelecionado);
            int selectedId = tvPromocoesAbaPromocoes.getSelectionModel().getSelectedIndex();
            tvPromocoesAbaPromocoes.getItems().remove(selectedId);
        }else{
            lbPushMsgAbaPromocoes.setText("Selecione uma promoção!");
            hbPushMsgAbaPromocoes.getStyleClass().setAll("push-msg-error");
            hbPushMsgAbaPromocoes.setVisible(true);
        }
    }

    @FXML
    public void trocarAbaVeiculos(MouseEvent event) {
        ScreenManager sm = ScreenManager.getInstance();
        sm.changeScene("colaborador-aba-veiculos.fxml", "Loocalize - Colaborador");
    }

    @FXML
    public void trocarTelaLogin(MouseEvent event) {
        ScreenManager sm = ScreenManager.getInstance();
        sm.changeScene("login.fxml", "Loocalize - Login");
    }

    public void atualizaPromocoesDaTabela(){
        for (int i = 0; i < tvPromocoesAbaPromocoes.getItems().size(); i++) {
            tvPromocoesAbaPromocoes.getItems().clear();
        }
        this.listaDePromocoes = FXCollections.observableArrayList(app.getServer().listarTodasPromocoes());
        tvPromocoesAbaPromocoes.setItems(listaDePromocoes);
    }

    public void limparCampos(){
        lbIdAbaPromocoes.setText("");
        lbTituloAbaPromocoes.setText("");
        lbDescontoAbaPromocoes.setText("");
        lbQtdMinimaDeDiariasAbaPromocoes.setText("");
        lbQtdDeMinimaDeLocacoesAbaPromocoes.setText("");
        lbDataDeCriacaoAbaPromocoes.setText("");
        lbDataDeExpiracaoAbaPromocoes.setText("");
        lbAtivaAbaPromocoes.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hbPushMsgAbaPromocoes.setVisible(false);
        tvPromocoesAbaPromocoes.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
        tcIdAbaPromocoes.setCellValueFactory(new PropertyValueFactory<Promocao,String>("id"));
        tcTituloAbaPromocoes.setCellValueFactory(new PropertyValueFactory<Promocao,String>("titulo"));
        tcDescontoAbaPromocoes.setCellValueFactory(new PropertyValueFactory<Promocao,Integer>("porcentagemDeDesconto"));
        tcDataDeCriacaoAbaPromocoes.setCellValueFactory(new PropertyValueFactory<Promocao, LocalDate>("dataDeCriacao"));
        tcDataDeExpiracaoAbaPromocoes.setCellValueFactory(new PropertyValueFactory<Promocao,LocalDate>("dataDeExpiracao"));
        tcAtivaAbaPromocoes.setCellValueFactory(new PropertyValueFactory<Promocao,Boolean>("ativa"));
        this.limparCampos();
        this.atualizaPromocoesDaTabela();
    }

}

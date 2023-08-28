package gui;

import exception.VeiculoEditadoComSucessoException;
import exception.VeiculoNaoExisteException;
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
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import models.Cliente;
import models.Locacao;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ClienteAbaMinhasLocacoesController implements Initializable {
    Application app;
    Cliente clienteLogado;
    ObservableList<Locacao> listaDasMinhasLocacoes;

    @FXML
    private VBox EscolhaAbaMinhasLocacoes;

    @FXML
    private Button btnDetalharAbaMinhasLocacoes;

    @FXML
    private Button btnDevolverAbaMinhasLocacoes;

    @FXML
    private HBox btnFecharAbaMinhasLocacoes;

    @FXML
    private HBox hbBtnAbaMinhasLocacoes;

    @FXML
    private HBox hbBtnCatalogoAbaMinhasLocacoes;

    @FXML
    private HBox hbPushMsgAbaMinhasLocacoes;

    @FXML
    private HBox hbSairAbaMinhasLocacoes;

    @FXML
    private ImageView imgCarroAbaMinhasLocacoes;

    @FXML
    private Label lbAnoAbaMinhasLocacoes;

    @FXML
    private Label lbDataHoraAbaMinhasLocacoes;

    @FXML
    private Label lbDiariaAbaMinhasLocacoes;

    @FXML
    private Label lbKmMinhasLocacoes;

    @FXML
    private Label lbMarcaAbaMinhasLocacoes;

    @FXML
    private Label lbModeloAbaMinhasLocacoes;

    @FXML
    private Label lbPlacaAbaMinhasLocacoes;

    @FXML
    private Label lbPushMsgAbaMinhasLocacoes;

    @FXML
    private TableColumn<Locacao, Boolean> tcDevolvidoAbaMinhasLocacoes;

    @FXML
    private TableColumn<Locacao, LocalDate> tcDataMinhasLocacoes;

    @FXML
    private TableColumn<Locacao, String> tcIdAbaMinhasLocacoes;


    @FXML
    private TableView<Locacao> tvLocacoesAbaMinhasLocacoes;

    public ClienteAbaMinhasLocacoesController(){
        this.app = new Application();
    }

    public Cliente getClienteLogado() {
        return clienteLogado;
    }

    public void setClienteLogado(Cliente clienteLogado) {
        this.clienteLogado = clienteLogado;
    }

    public ObservableList<Locacao> getListaDasMinhasLocacoes() {
        return listaDasMinhasLocacoes;
    }

    @FXML
    public void fecharPushMsg(MouseEvent event) {
        hbPushMsgAbaMinhasLocacoes.setVisible(false);
    }

    @FXML
    public void detalharLocacao(ActionEvent event){
        Locacao locacaoSelecionada = tvLocacoesAbaMinhasLocacoes.getSelectionModel().getSelectedItem();
        if(locacaoSelecionada != null){

            imgCarroAbaMinhasLocacoes.setImage(new Image("file:" + locacaoSelecionada.getVeiculo().getFotoDoVeiculo()));
            Rectangle clip = new Rectangle(imgCarroAbaMinhasLocacoes.getFitWidth(), imgCarroAbaMinhasLocacoes.getFitHeight());
            clip.setArcWidth(10);
            clip.setArcHeight(10);
            imgCarroAbaMinhasLocacoes.setClip(clip);

            lbModeloAbaMinhasLocacoes.setText(locacaoSelecionada.getVeiculo().getModelo());
            lbPlacaAbaMinhasLocacoes.setText(locacaoSelecionada.getVeiculo().getPlaca());
            lbDiariaAbaMinhasLocacoes.setText(Double.toString(locacaoSelecionada.getVeiculo().getValorDaDiaria()));
            lbAnoAbaMinhasLocacoes.setText(locacaoSelecionada.getVeiculo().getAno().toString());
            lbMarcaAbaMinhasLocacoes.setText(locacaoSelecionada.getVeiculo().getMarca());
            lbKmMinhasLocacoes.setText(Integer.toString(locacaoSelecionada.getVeiculo().getQuilometragem()));
        }else{
            lbPushMsgAbaMinhasLocacoes.setText("Selecione uma locação!");
            hbPushMsgAbaMinhasLocacoes.getStyleClass().setAll("push-msg-error");
            hbPushMsgAbaMinhasLocacoes.setVisible(true);
        }
    }

    @FXML
    public void devolverVeiculo(ActionEvent event){
        Locacao locacaoSelecionada = tvLocacoesAbaMinhasLocacoes.getSelectionModel().getSelectedItem();
        if(locacaoSelecionada != null){
            try {
                locacaoSelecionada.setCarroDevolvido(true);
                app.getServer().setarDisponibilidadeDoVeiculo(locacaoSelecionada.getVeiculo(), true);
                this.atualizaLocacoesDaTabela();
            } catch (VeiculoNaoExisteException e) {
                lbPushMsgAbaMinhasLocacoes.setText(e.getMessage());
                hbPushMsgAbaMinhasLocacoes.getStyleClass().setAll("push-msg-error");
                hbPushMsgAbaMinhasLocacoes.setVisible(true);
            } catch (VeiculoEditadoComSucessoException e) {
                lbPushMsgAbaMinhasLocacoes.setText("Veículo devolvidado com sucesso!");
                hbPushMsgAbaMinhasLocacoes.getStyleClass().setAll("push-msg-success");
                hbPushMsgAbaMinhasLocacoes.setVisible(true);
            }
        }else{
            lbPushMsgAbaMinhasLocacoes.setText("Selecione uma locação!");
            hbPushMsgAbaMinhasLocacoes.getStyleClass().setAll("push-msg-error");
            hbPushMsgAbaMinhasLocacoes.setVisible(true);
        }
    }

    @FXML
    public void trocarAbaCatalogo(MouseEvent event) {
        ScreenManager sm = ScreenManager.getInstance();
        sm.changeScene("cliente-aba-catalogo.fxml", "Loocalize - Home");
    }

    @FXML
    public void trocarTelaLogin(MouseEvent event) {
        ScreenManager sm = ScreenManager.getInstance();
        sm.changeScene("login.fxml", "Loocalize - Login");
    }

    public void atualizaLocacoesDaTabela(){
        for (int i = 0; i < tvLocacoesAbaMinhasLocacoes.getItems().size(); i++) {
            tvLocacoesAbaMinhasLocacoes.getItems().clear();
        }
//        System.out.println(clienteLogado.getLocacoes());
        this.listaDasMinhasLocacoes = FXCollections.observableArrayList(clienteLogado.getLocacoes());
        tvLocacoesAbaMinhasLocacoes.setItems(listaDasMinhasLocacoes);
    }

    public void limparCampos(){
        lbModeloAbaMinhasLocacoes.setText("");
        lbPlacaAbaMinhasLocacoes.setText("");
        lbDiariaAbaMinhasLocacoes.setText("");
        lbAnoAbaMinhasLocacoes.setText("");
        lbMarcaAbaMinhasLocacoes.setText("");
        lbKmMinhasLocacoes.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hbPushMsgAbaMinhasLocacoes.setVisible(false);
        tvLocacoesAbaMinhasLocacoes.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
        tcIdAbaMinhasLocacoes.setCellValueFactory(new PropertyValueFactory<Locacao,String>("id"));
        tcDataMinhasLocacoes.setCellValueFactory(new PropertyValueFactory<Locacao,LocalDate>("dataHoraDaLocacao"));
        tcDevolvidoAbaMinhasLocacoes.setCellValueFactory(new PropertyValueFactory<Locacao,Boolean>("carroDevolvido"));
        this.limparCampos();
    }

}

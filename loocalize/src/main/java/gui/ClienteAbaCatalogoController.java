package gui;

import exception.LocacaoFeitaComSucessoException;
import exception.VeiculoEditadoComSucessoException;
import exception.VeiculoNaoExisteException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import models.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ClienteAbaCatalogoController implements Initializable {
    Application app;
    Usuario usuarioLogado;
    Promocao promocaoAplicavel;
    Veiculo veiculoSelecionado;

    private List<Veiculo> listaDeVeiculosDocatalogo = new ArrayList<>();

    @FXML
    private VBox EscolhaCatalogoVeiculo;

    @FXML
    private Button btnAlugarCatalogoVeiculos;

    @FXML
    private HBox btnFecharAbaCatalogoVeiculos;

    @FXML
    private GridPane gridCatalogoVeiculo;

    @FXML
    private HBox hbBtnCatalogoVeiculos;

    @FXML
    private HBox hbBtnMinhasLocacoesCatalogoVeiculos;

    @FXML
    private HBox hbPushMsgCatalogoVeiculos;

    @FXML
    private HBox hbSairAbaCatalogoVeiculos;

    @FXML
    private ImageView imgCarroCatalogoVeiculos;

    @FXML
    private ImageView imgPromocaoCatalogoVeiculos;

    @FXML
    private Label lbAnoCatalogoVeiculos;

    @FXML
    private Label lbDataHoraCatalogoVeiculos;

    @FXML
    private Label lbDiariaCatalogoVeiculos;

    @FXML
    private Label lbKmCatalogoVeiculos;

    @FXML
    private Label lbModeloCatalogoVeiculos;

    @FXML
    private Label lbPlacaCatalogoVeiculos;

    @FXML
    private Label lbPrecoCatalogoVeiculos;

    @FXML
    private Label lbPromocaoCatalogoVeiculos;

    @FXML
    private Label lbPushMsgCatalogoVeiculos;

    @FXML
    private ScrollPane scrollCatalogoVeiculo;

    @FXML
    private Spinner<Integer> spDiariasCatalogoVeiculos;

    public ClienteAbaCatalogoController(){
        this.app = new Application();
    }

    public Veiculo getVeiculoSelecionado() {
        return veiculoSelecionado;
    }

    public void setVeiculoSelecionado(Veiculo veiculoSelecionado) {
        this.veiculoSelecionado = veiculoSelecionado;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public Promocao getPromocaoAplicavel() {
        return promocaoAplicavel;
    }

    public void setPromocaoAplicavel(Promocao promocaoAplicavel) {
        this.promocaoAplicavel = promocaoAplicavel;
    }

    @FXML
    public void fecharPushMsg(MouseEvent event) {
        hbPushMsgCatalogoVeiculos.setVisible(false);
    }

    @FXML
    public void trocarTelaLogin(MouseEvent event) {
        ScreenManager sm = ScreenManager.getInstance();
        sm.changeScene("login.fxml", "Loocalize - Login");
    }

    @FXML
    public void trocarAbaMinhasLocacoes(MouseEvent event){
        ScreenManager sm = ScreenManager.getInstance();
        sm.getClienteAbaMinhasLocacoesController().setClienteLogado((Cliente) usuarioLogado);
        sm.getClienteAbaMinhasLocacoesController().atualizaLocacoesDaTabela();
        sm.changeScene("cliente-aba-locacoes.fxml", "Loocalize - Minhas Locações");
    }

    public void setarListaDeVeiculosDoCatalogo(){
        listaDeVeiculosDocatalogo.addAll(app.getServer().listarTodosVeiculos()); // Precisa ser apenas os disponíveis
        System.out.println(listaDeVeiculosDocatalogo);
        int coluna = 0;
        int linha = 1;

        try {
            for(int i = 0; i < listaDeVeiculosDocatalogo.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("card.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                CardController cardController = fxmlLoader.getController();
                cardController.setDadosDoVeiculo(listaDeVeiculosDocatalogo.get(i));

                if(coluna == 2){
                    coluna = 0;
                    linha++;
                }

                gridCatalogoVeiculo.add(anchorPane, coluna++, linha);

                gridCatalogoVeiculo.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridCatalogoVeiculo.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridCatalogoVeiculo.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                gridCatalogoVeiculo.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridCatalogoVeiculo.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridCatalogoVeiculo.setMaxHeight(Region.USE_PREF_SIZE);

                gridCatalogoVeiculo.setVgap(10);
                gridCatalogoVeiculo.setHgap(10);

                GridPane.setMargin(anchorPane, new Insets(10));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void locarVeiculo(ActionEvent event){
        if(veiculoSelecionado != null){
            if(veiculoSelecionado.isDisponivel()){
                try {
                    app.getServer().setarDisponibilidadeDoVeiculo(veiculoSelecionado, false);

                } catch (VeiculoNaoExisteException e) {
                    lbPushMsgCatalogoVeiculos.setText(e.getMessage());
                    hbPushMsgCatalogoVeiculos.getStyleClass().setAll("push-msg-error");
                    hbPushMsgCatalogoVeiculos.setVisible(true);
                } catch (VeiculoEditadoComSucessoException e) {
                    lbPushMsgCatalogoVeiculos.setText("Veículo alugado com sucesso!");
                    hbPushMsgCatalogoVeiculos.getStyleClass().setAll("push-msg-success");
                    hbPushMsgCatalogoVeiculos.setVisible(true);

                    Locacao locacao = new Locacao(veiculoSelecionado, (Cliente) usuarioLogado, LocalDateTime.now(), spDiariasCatalogoVeiculos.getValue(), false);
                    ((Cliente) usuarioLogado).addLocacao(locacao);
                    try {
                        app.getServer().inserirLocacao(locacao);
                    } catch (LocacaoFeitaComSucessoException ev) {
                        lbPushMsgCatalogoVeiculos.setText(ev.getMessage());
                        hbPushMsgCatalogoVeiculos.getStyleClass().setAll("push-msg-error");
                        hbPushMsgCatalogoVeiculos.setVisible(true);
                    }
                }
            }else{
                lbPushMsgCatalogoVeiculos.setText("Veículo indisponível!");
                hbPushMsgCatalogoVeiculos.getStyleClass().setAll("push-msg-error");
                hbPushMsgCatalogoVeiculos.setVisible(true);
            }
        }else{
            lbPushMsgCatalogoVeiculos.setText("Selecione um veículo!");
            hbPushMsgCatalogoVeiculos.getStyleClass().setAll("push-msg-error");
            hbPushMsgCatalogoVeiculos.setVisible(true);
        }
    }

    public void setarDadosDoVeiculoSelecionado(Veiculo veiculo){
        this.setVeiculoSelecionado(veiculo);

        imgCarroCatalogoVeiculos.setImage(new Image("file:" + veiculo.getFotoDoVeiculo()));
        Rectangle clip = new Rectangle(imgCarroCatalogoVeiculos.getFitWidth(), imgCarroCatalogoVeiculos.getFitHeight());
        clip.setArcWidth(10);
        clip.setArcHeight(10);
        imgCarroCatalogoVeiculos.setClip(clip);

        lbModeloCatalogoVeiculos.setText(veiculo.getModelo());
        lbPlacaCatalogoVeiculos.setText(veiculo.getPlaca());
        lbDiariaCatalogoVeiculos.setText("R$ " + Double.toString(veiculo.getValorDaDiaria()));
        lbAnoCatalogoVeiculos.setText(veiculo.getMarca());
        lbKmCatalogoVeiculos.setText(veiculo.getAno().toString());

        pegarPromocaoAplicavel(usuarioLogado, spDiariasCatalogoVeiculos.getValue());
        lbPromocaoCatalogoVeiculos.setText(promocaoAplicavel.getTitulo());

        int desconto = promocaoAplicavel.getPorcentagemDeDesconto();
        double valorTotalComDesconto = (veiculo.getValorDaDiaria() * spDiariasCatalogoVeiculos.getValue()) - (veiculo.getValorDaDiaria() * spDiariasCatalogoVeiculos.getValue() * desconto/100);
        lbPrecoCatalogoVeiculos.setText("R$ " + String.format("%.2f", valorTotalComDesconto));
        if(desconto > 0) imgPromocaoCatalogoVeiculos.setVisible(true);

        spDiariasCatalogoVeiculos.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                int desconto = promocaoAplicavel.getPorcentagemDeDesconto();
                double valorTotalComDesconto = (veiculo.getValorDaDiaria() * spDiariasCatalogoVeiculos.getValue()) - (veiculo.getValorDaDiaria() * spDiariasCatalogoVeiculos.getValue() * desconto/100);
                lbPrecoCatalogoVeiculos.setText("R$ " + String.format("%.2f", valorTotalComDesconto));
                if(desconto > 0) imgPromocaoCatalogoVeiculos.setVisible(true);
                pegarPromocaoAplicavel(usuarioLogado, spDiariasCatalogoVeiculos.getValue());
                lbPromocaoCatalogoVeiculos.setText(promocaoAplicavel.getTitulo());
            }
        });
    }

    public void pegarPromocaoAplicavel(Usuario usuario, int qtdDeDiarias){
        this.setPromocaoAplicavel(app.getServer().buscarPorPromocaoAplicavel(usuario, qtdDeDiarias));
    }

    public void limparCampos(){
        lbModeloCatalogoVeiculos.setText("");
        lbPlacaCatalogoVeiculos.setText("");
        lbDiariaCatalogoVeiculos.setText("");
        lbAnoCatalogoVeiculos.setText("");
        lbKmCatalogoVeiculos.setText("");
        lbPromocaoCatalogoVeiculos.setText("");
        lbPrecoCatalogoVeiculos.setText("");
        imgPromocaoCatalogoVeiculos.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hbPushMsgCatalogoVeiculos.setVisible(false);
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,366);
        valueFactory.setValue(1);
        spDiariasCatalogoVeiculos.setValueFactory(valueFactory);
        this.setarListaDeVeiculosDoCatalogo();
        this.limparCampos();
    }

}

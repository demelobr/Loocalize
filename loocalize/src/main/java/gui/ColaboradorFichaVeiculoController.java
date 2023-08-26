package gui;

import exception.VeiculoEditadoComSucessoException;
import exception.VeiculoNaoExisteException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Veiculo;

import java.io.File;
import java.time.Year;

public class ColaboradorFichaVeiculoController {
    Application app;
    Veiculo veiculoEditado;

    @FXML
    private Button btnCadastrarEditarVeiculo;

    @FXML
    private HBox btnFecharEditarVeiculo;

    @FXML
    private Button btnImgEditarVeiculo;

    @FXML
    private Label btnVoltarEditarVeiculo;

    @FXML
    private CheckBox cbDisponivelEditarVeiculo;

    @FXML
    private HBox hbPushMsgEditarVeiculo;

    @FXML
    private Label lbCaminhoImgEditarVeiculo;

    @FXML
    private Label lbPushMsgEditarVeiculo;

    @FXML
    private TextField tfAnoEditarVeiculo;

    @FXML
    private TextField tfDiariaEditarVeiculo;

    @FXML
    private TextField tfKmEditarVeiculo;

    @FXML
    private TextField tfLocacaoEditarVeiculo;

    @FXML
    private TextField tfMarcaEditarVeiculo;

    @FXML
    private TextField tfModeloEditarVeiculo;

    @FXML
    private TextField tfPlacaEditarVeiculo;

    public ColaboradorFichaVeiculoController(){
        this.app = new Application();
    }

    public Veiculo getVeiculoEditado() {
        return veiculoEditado;
    }

    public void setVeiculoEditado(Veiculo veiculoEditado) {
        this.veiculoEditado = veiculoEditado;
    }

    @FXML
    public void initialize(){
        hbPushMsgEditarVeiculo.setVisible(false);
    }

    @FXML
    public void fecharPushMsg(MouseEvent event) {
        hbPushMsgEditarVeiculo.setVisible(false);
    }

    @FXML
    public void salvarEdicaoVeiculo(){
        String modelo = tfModeloEditarVeiculo.getText();
        String marca = tfMarcaEditarVeiculo.getText();
        String placa = tfPlacaEditarVeiculo.getText();
        String ano = tfAnoEditarVeiculo.getText();
        String quilometragem = tfKmEditarVeiculo.getText();
        String qtdDeLocacao = tfLocacaoEditarVeiculo.getText();
        String valorDaDiaria = tfDiariaEditarVeiculo.getText();
        String foto = lbCaminhoImgEditarVeiculo.getText();
        boolean disponivel = cbDisponivelEditarVeiculo.isSelected();

        if(ano.isEmpty()) ano = veiculoEditado.getAno().toString();
        if(quilometragem.isEmpty()) quilometragem = Integer.toString(veiculoEditado.getQuilometragem());
        if(qtdDeLocacao.isEmpty()) qtdDeLocacao = Integer.toString(veiculoEditado.getQtdDeLocacao());
        if(valorDaDiaria.isEmpty()) valorDaDiaria = Double.toString(veiculoEditado.getValorDaDiaria());

        try {
            app.getServer().editarVeiculo(veiculoEditado, modelo, marca, placa, Year.of(Integer.parseInt(ano)), Integer.parseInt(quilometragem), Integer.parseInt(qtdDeLocacao), Double.parseDouble(valorDaDiaria), foto, disponivel);
        } catch (VeiculoNaoExisteException e) {
            lbPushMsgEditarVeiculo.setText(e.getMessage());
            hbPushMsgEditarVeiculo.getStyleClass().setAll("push-msg-error");
            hbPushMsgEditarVeiculo.setVisible(true);
        } catch (VeiculoEditadoComSucessoException e) {
            lbPushMsgEditarVeiculo.setText(e.getMessage());
            hbPushMsgEditarVeiculo.getStyleClass().setAll("push-msg-success");
            hbPushMsgEditarVeiculo.setVisible(true);

            new Thread(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ev) {
                    ev.printStackTrace();
                }
                Platform.runLater(() -> {
                    this.resetarTela();
                    ScreenManager sm = ScreenManager.getInstance();
                    sm.getColaboradorAbaVeiculosController().atualizaVeiculosDaTabela();
                    sm.changeScene("colaborador-aba-veiculos.fxml", "Loocalize - Colaborador");
                });
            }).start();
        }

    }

    @FXML
    public void escolherImagem(ActionEvent event){
        Stage stg = ScreenManager.getStg();
        FileChooser.ExtensionFilter extPng = new FileChooser.ExtensionFilter("Images", "*.png");
        FileChooser.ExtensionFilter extJpg = new FileChooser.ExtensionFilter("Images", "*.jpg");
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(extPng,extJpg);
        File imgSelecionada = fc.showOpenDialog(stg);
        if(imgSelecionada != null){
            lbCaminhoImgEditarVeiculo.setText(imgSelecionada.getAbsolutePath());
        }
    }

    @FXML
    public void trocarColaboradorAbaVeiculos(){
        this.resetarTela();
        ScreenManager sm = ScreenManager.getInstance();
        sm.changeScene("colaborador-aba-veiculos.fxml", "Loocalize - Colaborador");
    }

    public void setarDadosDoCarroEditado(){
        tfModeloEditarVeiculo.setPromptText(veiculoEditado.getModelo());
        tfMarcaEditarVeiculo.setPromptText(veiculoEditado.getMarca());
        tfPlacaEditarVeiculo.setPromptText(veiculoEditado.getPlaca());
        tfAnoEditarVeiculo.setPromptText(veiculoEditado.getAno().toString());
        tfKmEditarVeiculo.setPromptText(Integer.toString(veiculoEditado.getQuilometragem()));
        tfLocacaoEditarVeiculo.setPromptText(Integer.toString(veiculoEditado.getQtdDeLocacao()));
        tfDiariaEditarVeiculo.setPromptText(Double.toString(veiculoEditado.getValorDaDiaria()));
        lbCaminhoImgEditarVeiculo.setText(veiculoEditado.getFotoDoVeiculo());
        cbDisponivelEditarVeiculo.setSelected(veiculoEditado.isDisponivel());
    }

    public void resetarTela(){
        hbPushMsgEditarVeiculo.setVisible(false);
        tfModeloEditarVeiculo.setText("");
        tfMarcaEditarVeiculo.setText("");
        tfPlacaEditarVeiculo.setText("");
        tfAnoEditarVeiculo.setText("");
        tfKmEditarVeiculo.setText("");
        tfLocacaoEditarVeiculo.setText("");
        tfDiariaEditarVeiculo.setText("");
        lbCaminhoImgEditarVeiculo.setText("");
        cbDisponivelEditarVeiculo.setSelected(false);
    }

}

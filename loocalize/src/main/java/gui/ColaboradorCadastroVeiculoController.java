package gui;

import exception.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import models.Veiculo;

import java.io.File;
import java.time.Year;

public class ColaboradorCadastroVeiculoController {
    Application app;

    @FXML
    private Button btnCadastrarCadastroVeiculo;

    @FXML
    private HBox btnFecharCadastroVeiculo;

    @FXML
    private Button btnImgCadastroVeiculo;

    @FXML
    private Label btnVoltarCadastroVeiculo;

    @FXML
    private HBox hbPushMsgCadastroVeiculo;

    @FXML
    private Label lbCaminhoImgCadastroVeiculo;

    @FXML
    private Label lbPushMsgCadastroVeiculo;

    @FXML
    private TextField tfAnoCadastroVeiculo;

    @FXML
    private TextField tfDiariaCadastroVeiculo;

    @FXML
    private TextField tfKmCadastroVeiculo;

    @FXML
    private TextField tfLocacaoCadastroVeiculo;

    @FXML
    private TextField tfMarcaCadastroVeiculo;

    @FXML
    private TextField tfModeloCadastroVeiculo;

    @FXML
    private TextField tfPlacaCadastroVeiculo;

    public ColaboradorCadastroVeiculoController(){
        this.app = new Application();
    }

    @FXML
    public void initialize(){
        hbPushMsgCadastroVeiculo.setVisible(false);
    }

    @FXML
    public void fecharPushMsg(MouseEvent event) {
        hbPushMsgCadastroVeiculo.setVisible(false);
    }

    @FXML
    public void cadastrarVeiculo(){
        String modelo = tfModeloCadastroVeiculo.getText();
        String marca = tfMarcaCadastroVeiculo.getText();
        String placa = tfPlacaCadastroVeiculo.getText();
        String ano = tfAnoCadastroVeiculo.getText();
        String quilometragem = tfKmCadastroVeiculo.getText();
        String qtdDeLocacao = tfLocacaoCadastroVeiculo.getText();
        String valorDaDiaria = tfDiariaCadastroVeiculo.getText();
        String foto = lbCaminhoImgCadastroVeiculo.getText();
        Veiculo novoVeiculo = null;

        if(!modelo.isEmpty() && !marca.isEmpty() && !placa.isEmpty() && !ano.isEmpty() && !quilometragem.isEmpty() && !qtdDeLocacao.isEmpty() && !valorDaDiaria.isEmpty() && !foto.isEmpty()){
            if(Year.of(Integer.parseInt(ano)) != null && Integer.parseInt(quilometragem) >= 0 && Integer.parseInt(qtdDeLocacao) >= 0 && Double.parseDouble(valorDaDiaria) >= 0){
                try {
                    novoVeiculo = new Veiculo(modelo, marca, placa, Year.of(Integer.parseInt(ano)), Integer.parseInt(quilometragem), Integer.parseInt(qtdDeLocacao), Double.parseDouble(valorDaDiaria), foto, true);
                    app.getServer().inserirVeiculo(novoVeiculo);

                } catch (VeiculoExisteException e) {
                    lbPushMsgCadastroVeiculo.setText(e.getMessage());
                    hbPushMsgCadastroVeiculo.getStyleClass().setAll("push-msg-error");
                    hbPushMsgCadastroVeiculo.setVisible(true);
                } catch (CampoVazioException e) {
                    lbPushMsgCadastroVeiculo.setText(e.getMessage());
                    hbPushMsgCadastroVeiculo.getStyleClass().setAll("push-msg-error");
                    hbPushMsgCadastroVeiculo.setVisible(true);
                } catch (ValorDaDiariaInvalidaException e) {
                    lbPushMsgCadastroVeiculo.setText(e.getMessage());
                    hbPushMsgCadastroVeiculo.getStyleClass().setAll("push-msg-error");
                    hbPushMsgCadastroVeiculo.setVisible(true);
                } catch (QuilometragemInvalidaException e) {
                    lbPushMsgCadastroVeiculo.setText(e.getMessage());
                    hbPushMsgCadastroVeiculo.getStyleClass().setAll("push-msg-error");
                    hbPushMsgCadastroVeiculo.setVisible(true);
                } catch (VeiculoInseridoComSucessoException e) {
                    lbPushMsgCadastroVeiculo.setText(e.getMessage());
                    hbPushMsgCadastroVeiculo.getStyleClass().setAll("push-msg-success");
                    hbPushMsgCadastroVeiculo.setVisible(true);

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
            }else{
                lbPushMsgCadastroVeiculo.setText("Algum dado está errado!");
                hbPushMsgCadastroVeiculo.getStyleClass().setAll("push-msg-error");
                hbPushMsgCadastroVeiculo.setVisible(true);
            }
        }else{
            lbPushMsgCadastroVeiculo.setText("Preencha todos os campos!");
            hbPushMsgCadastroVeiculo.getStyleClass().setAll("push-msg-error");
            hbPushMsgCadastroVeiculo.setVisible(true);
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
            lbCaminhoImgCadastroVeiculo.setText(imgSelecionada.getAbsolutePath());
        }
    }

    @FXML
    public void trocarColaboradorAbaVeiculos(){
        this.resetarTela();
        ScreenManager sm = ScreenManager.getInstance();
        sm.changeScene("colaborador-aba-veiculos.fxml", "Loocalize - Colaborador");
    }

    public void resetarTela(){
        hbPushMsgCadastroVeiculo.setVisible(false);
        tfModeloCadastroVeiculo.setText("");
        tfMarcaCadastroVeiculo.setText("");
        tfPlacaCadastroVeiculo.setText("");
        tfAnoCadastroVeiculo.setText("");
        tfKmCadastroVeiculo.setText("");
        tfLocacaoCadastroVeiculo.setText("");
        tfDiariaCadastroVeiculo.setText("");
        lbCaminhoImgCadastroVeiculo.setText("");
    }

}

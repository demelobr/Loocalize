package gui;

import exception.PromocaoExisteException;
import exception.PromocaoInseridaComSucessoException;
import exception.PromocaoNulaException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import models.Promocao;

import java.time.LocalDate;

public class ColaboradorCadastroPromocaoController {
    Application app;

    @FXML
    private Button btnCadastrarCadastroPromocao;

    @FXML
    private HBox btnFecharCadastroPromocao;

    @FXML
    private Label btnVoltarCadastroPromocao;

    @FXML
    private DatePicker dpDataDeCriacaoCadastroPromocao;

    @FXML
    private DatePicker dpDataDeExpiracaoCadastroPromocao;

    @FXML
    private HBox hbPushMsgCadastroPromocao;

    @FXML
    private Label lbPushMsgCadastroPromocao;

    @FXML
    private TextField tfDescontoCadastroPromocao;

    @FXML
    private TextField tfQtdMinimaDeDiariasCadastroPromocao;

    @FXML
    private TextField tfQtdMinimaDeLocacoesCadastroPromocao;

    @FXML
    private TextField tfTituloCadastroPromocao;

    public ColaboradorCadastroPromocaoController(){
        this.app = new Application();
    }

    @FXML
    public void initialize(){
        hbPushMsgCadastroPromocao.setVisible(false);
        dpDataDeCriacaoCadastroPromocao.setValue(LocalDate.now());
        dpDataDeExpiracaoCadastroPromocao.setValue(LocalDate.now());
    }

    @FXML
    public void fecharPushMsg(MouseEvent event) {
        hbPushMsgCadastroPromocao.setVisible(false);
    }

    @FXML
    public void cadastrarPromocao(){
        String titulo = tfTituloCadastroPromocao.getText();
        String porcentagemDeDesconto = tfDescontoCadastroPromocao.getText();
        String qtdMinimaDeDiarias = tfQtdMinimaDeDiariasCadastroPromocao.getText();
        String qtdMinimaDeLocacoes = tfQtdMinimaDeLocacoesCadastroPromocao.getText();
        LocalDate dataDeCriacao = dpDataDeCriacaoCadastroPromocao.getValue();
        LocalDate dataDeExpiracao = dpDataDeExpiracaoCadastroPromocao.getValue();

        if(!titulo.isEmpty() && !porcentagemDeDesconto.isEmpty() && !qtdMinimaDeDiarias.isEmpty() && !qtdMinimaDeLocacoes.isEmpty() && dataDeCriacao != null && dataDeExpiracao != null){
            if(Integer.parseInt(porcentagemDeDesconto) >= 0 && Integer.parseInt(porcentagemDeDesconto) < 100 && Integer.parseInt(qtdMinimaDeDiarias) >= 0 && Integer.parseInt(qtdMinimaDeLocacoes) >= 0 && !dataDeCriacao.isBefore(LocalDate.now()) && !dataDeExpiracao.isBefore(dataDeCriacao)){
                try {
                    app.getServer().inserirPromocao(new Promocao(titulo, Integer.parseInt(porcentagemDeDesconto), Integer.parseInt(qtdMinimaDeDiarias), Integer.parseInt(qtdMinimaDeLocacoes), dataDeCriacao, dataDeExpiracao, true));
                } catch (PromocaoNulaException e) {
                    lbPushMsgCadastroPromocao.setText(e.getMessage());
                    hbPushMsgCadastroPromocao.getStyleClass().setAll("push-msg-error");
                    hbPushMsgCadastroPromocao.setVisible(true);
                } catch (PromocaoExisteException e) {
                    lbPushMsgCadastroPromocao.setText(e.getMessage());
                    hbPushMsgCadastroPromocao.getStyleClass().setAll("push-msg-error");
                    hbPushMsgCadastroPromocao.setVisible(true);
                } catch (PromocaoInseridaComSucessoException e) {
                    lbPushMsgCadastroPromocao.setText(e.getMessage());
                    hbPushMsgCadastroPromocao.getStyleClass().setAll("push-msg-success");
                    hbPushMsgCadastroPromocao.setVisible(true);

                    new Thread(() -> {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException ev) {
                            ev.printStackTrace();
                        }
                        Platform.runLater(() -> {
                            this.resetarTela();
                            ScreenManager sm = ScreenManager.getInstance();
                            sm.getColaboradorAbaPromocoesController().atualizaPromocoesDaTabela();
                            sm.changeScene("colaborador-aba-promocoes.fxml", "Loocalize - Colaborador");
                        });
                    }).start();
                }
            }else{
                lbPushMsgCadastroPromocao.setText("Algum dado está errado!");
                hbPushMsgCadastroPromocao.getStyleClass().setAll("push-msg-error");
                hbPushMsgCadastroPromocao.setVisible(true);
            }
        }else{
            lbPushMsgCadastroPromocao.setText("Preencha todos os campos!");
            hbPushMsgCadastroPromocao.getStyleClass().setAll("push-msg-error");
            hbPushMsgCadastroPromocao.setVisible(true);
        }
    }

    @FXML
    public void trocarColaboradorAbaPromocoes(){
        this.resetarTela();
        ScreenManager sm = ScreenManager.getInstance();
        sm.changeScene("colaborador-aba-promocoes.fxml", "Loocalize - Colaborador");
    }

    public void resetarTela(){
        hbPushMsgCadastroPromocao.setVisible(false);
        tfTituloCadastroPromocao.setText("");
        tfDescontoCadastroPromocao.setText("");
        tfQtdMinimaDeDiariasCadastroPromocao.setText("");
        tfQtdMinimaDeLocacoesCadastroPromocao.setText("");
        dpDataDeCriacaoCadastroPromocao.setValue(LocalDate.now());
        dpDataDeExpiracaoCadastroPromocao.setValue(LocalDate.now());
    }

}

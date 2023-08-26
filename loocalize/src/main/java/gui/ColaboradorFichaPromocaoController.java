package gui;

import exception.PromocaoEditadaComSucessoException;
import exception.PromocaoNaoExisteException;
import exception.PromocaoNulaException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import models.Promocao;

import java.time.LocalDate;

public class ColaboradorFichaPromocaoController {
    Application app;
    Promocao promocaoEditada;

    @FXML
    private Button btnCadastrarEditarPromocao;

    @FXML
    private HBox btnFecharEditarPromocao;

    @FXML
    private Label btnVoltarEditarPromocao;

    @FXML
    private CheckBox cbAtivaEditarPromocao;

    @FXML
    private DatePicker dpDataDeExpiracaoEditarPromocao;

    @FXML
    private HBox hbPushMsgEditarPromocao;

    @FXML
    private Label lbPushMsgEditarPromocao;

    @FXML
    private TextField tfDescontoEditarPromocao;

    @FXML
    private TextField tfQtdMinimaDeDiariasEditarPromocao;

    @FXML
    private TextField tfQtdMinimaDeLocacoesEditarPromocao;

    @FXML
    private TextField tfTituloEditarPromocao;

    public ColaboradorFichaPromocaoController(){
        this.app = new Application();
    }

    public Promocao getPromocaoEditada() {
        return promocaoEditada;
    }

    public void setPromocaoEditada(Promocao promocaoEditada) {
        this.promocaoEditada = promocaoEditada;
    }

    @FXML
    public void initialize(){
        hbPushMsgEditarPromocao.setVisible(false);
    }

    @FXML
    public void fecharPushMsg(MouseEvent event) {
        hbPushMsgEditarPromocao.setVisible(false);
    }

    @FXML
    public void salvarEdicaoPromocao(){
        String titulo = tfTituloEditarPromocao.getText();
        String porcentagemDeDesconto = tfDescontoEditarPromocao.getText();
        String qtdMinimaDeDiarias = tfQtdMinimaDeDiariasEditarPromocao.getText();
        String qtdMinimaDeLocacoes = tfQtdMinimaDeLocacoesEditarPromocao.getText();
        LocalDate dataDeExpiracao = dpDataDeExpiracaoEditarPromocao.getValue();
        boolean ativa = cbAtivaEditarPromocao.isSelected();

        if(porcentagemDeDesconto.isEmpty()) porcentagemDeDesconto = Integer.toString(promocaoEditada.getPorcentagemDeDesconto());
        if(qtdMinimaDeDiarias.isEmpty()) qtdMinimaDeDiarias = Integer.toString(promocaoEditada.getQtdMinimaDeDiarias());
        if(qtdMinimaDeLocacoes.isEmpty()) qtdMinimaDeLocacoes = Integer.toString(promocaoEditada.getQtdMinimaDeLocacoes());
        if(dataDeExpiracao == null) dataDeExpiracao = promocaoEditada.getDataDeExpiracao();

        try {
            app.getServer().editarPromocao(promocaoEditada, titulo, Integer.parseInt(porcentagemDeDesconto), Integer.parseInt(qtdMinimaDeDiarias), Integer.parseInt(qtdMinimaDeLocacoes), dataDeExpiracao, ativa);
        } catch (PromocaoNulaException e) {
            lbPushMsgEditarPromocao.setText(e.getMessage());
            hbPushMsgEditarPromocao.getStyleClass().setAll("push-msg-error");
            hbPushMsgEditarPromocao.setVisible(true);
        } catch (PromocaoNaoExisteException e) {
            lbPushMsgEditarPromocao.setText(e.getMessage());
            hbPushMsgEditarPromocao.getStyleClass().setAll("push-msg-error");
            hbPushMsgEditarPromocao.setVisible(true);
        } catch (PromocaoEditadaComSucessoException e) {
            lbPushMsgEditarPromocao.setText(e.getMessage());
            hbPushMsgEditarPromocao.getStyleClass().setAll("push-msg-success");
            hbPushMsgEditarPromocao.setVisible(true);

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
    }

    @FXML
    public void trocarColaboradorAbaPromocoes(){
        this.resetarTela();
        ScreenManager sm = ScreenManager.getInstance();
        sm.changeScene("colaborador-aba-promocoes.fxml", "Loocalize - Colaborador");
    }

    public void setarDadosDaPromocaoEditada(){
        tfTituloEditarPromocao.setPromptText(promocaoEditada.getTitulo());
        tfDescontoEditarPromocao.setPromptText(Integer.toString(promocaoEditada.getPorcentagemDeDesconto()));
        tfQtdMinimaDeDiariasEditarPromocao.setPromptText(Integer.toString(promocaoEditada.getQtdMinimaDeDiarias()));
        tfQtdMinimaDeLocacoesEditarPromocao.setPromptText(Integer.toString(promocaoEditada.getQtdMinimaDeLocacoes()));
        dpDataDeExpiracaoEditarPromocao.setValue(promocaoEditada.getDataDeExpiracao());
        cbAtivaEditarPromocao.setSelected(promocaoEditada.isAtiva());
    }

    public void resetarTela(){
        hbPushMsgEditarPromocao.setVisible(false);
        tfTituloEditarPromocao.setText("");
        tfDescontoEditarPromocao.setText("");
        tfQtdMinimaDeDiariasEditarPromocao.setText("");
        tfQtdMinimaDeLocacoesEditarPromocao.setText("");
        dpDataDeExpiracaoEditarPromocao.setValue(LocalDate.now());
        cbAtivaEditarPromocao.setSelected(false);
    }
}

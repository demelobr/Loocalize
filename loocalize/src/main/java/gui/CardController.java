package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import models.Veiculo;

public class CardController {
    Application app;
    Veiculo veiculo;

    @FXML
    private ImageView imgVeiculoCard;

    @FXML
    private ImageView imgDescontoCard;

    @FXML
    private Label lbModeloCard;

    @FXML
    private Label lbPrecoCard;

    public CardController(){
        this.app = new Application();
    }

    public Application getApp() {
        return app;
    }

    public void setApp(Application app) {
        this.app = app;
    }

    public void setVeiculoSelecionado(MouseEvent event){
        ScreenManager sm = ScreenManager.getInstance();
        sm.getClienteAbaCatalogoController().setarDadosDoVeiculoSelecionado(this.veiculo);
    }

    public void setDadosDoVeiculo(Veiculo veiculo){
        this.veiculo = veiculo;
        lbModeloCard.setText(veiculo.getModelo());
        lbPrecoCard.setText("R$ " + veiculo.getValorDaDiaria());
        imgVeiculoCard.setImage(new Image("file:" + veiculo.getFotoDoVeiculo()));
        Rectangle clip = new Rectangle(imgVeiculoCard.getFitWidth(), imgVeiculoCard.getFitHeight());
        clip.setArcWidth(10);
        clip.setArcHeight(10);
        imgDescontoCard.setClip(clip);
        imgDescontoCard.setVisible(false);
    }
}

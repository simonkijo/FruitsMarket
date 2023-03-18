package com.simonkijo.fruitsmarket;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.Objects;

public class ItemController {
    @FXML
    private ImageView img;
    @FXML
    private Label nameLabel;
    @FXML
    private Label priceLabel;

    private FruitData fruit;
    private MyListener myListener;

    public void click(MouseEvent mouseEvent){
        myListener.OnClickListener(fruit);
    }

    public void setData(FruitData fruit,MyListener myListener){
        this.fruit=fruit;
        this.myListener=myListener;
        nameLabel.setText(fruit.getName());
        priceLabel.setText(MarketMain.CURRENCY+fruit.getPrice());
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(fruit.getImgSrc())));
        img.setImage(image);
    }
}

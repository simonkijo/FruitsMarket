package com.simonkijo.fruitsmarket;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class ItemController {
    @FXML
    private ImageView img;
    @FXML
    private Label nameLabel;
    @FXML
    private Label priceLabel;

    private FruitData fruit;

    public void setData(FruitData fruit){
        this.fruit=fruit;
        nameLabel.setText(fruit.getName());
        priceLabel.setText(MarketMain.CURRENCY+fruit.getPrice());
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(fruit.getImgSrc())));
        img.setImage(image);
    }
}

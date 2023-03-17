package com.simonkijo.fruitsmarket;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MarketController implements Initializable {
    @FXML
    private VBox chosenFruitCard;
    @FXML
    private ImageView fruitImg;
    @FXML
    private Label fruitNameLabel;
    @FXML
    private Label fruitPriceLabel;
    @FXML
    private GridPane grid;
    @FXML
    private ScrollPane scroll;

    private List<FruitData> fruits = new ArrayList<>();
    private List<FruitData> getData(){
        List<FruitData> fruits = new ArrayList<>();
        FruitData fruit;

        for(int i=0;i<20;i++){
            fruit = new FruitData();
            fruit.setName("kiwi");
            fruit.setPrice(2.99);
            fruit.setImgSrc("/images/kiwi.png");
            fruit.setColor("6a7324");
            fruits.add(fruit);
        }
        return fruits;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fruits.addAll(getData());
        int column=0;
        int row=0;

        try {
            for(int i=0;i<fruits.size();i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("item.fxml"));

                    AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(fruits.get(i));

                if(column==3){
                    column=0;
                    row++;
                }
                grid.add(anchorPane,column++,row); // child,column,row
                GridPane.setMargin(anchorPane,new Insets(10));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
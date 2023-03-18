package com.simonkijo.fruitsmarket;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.*;

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

    private final List<FruitData> fruits = new ArrayList<>();
    private Image image;
    private MyListener myListener;
    private List<FruitData> getData(){
        List<String> fruitName = new ArrayList<>(List.of(new String[]{"banana","mango","cherry","coconut","grapes","kiwi","orange","peach","strawberry","watermelon"}));
        List<Double> fruitPrice = new ArrayList<>(List.of(new Double[]{0.89,1.25,0.99,0.45,2.30,4.03,3.45,2.0,0.86,2.50}));
        List<String> fruitColor = new ArrayList<>(List.of(new String[]{"22371d","fb5d03","291d36","f16c31","a7745b","5f060e","ffb605","80080c","e7c00f","6a7324"}));

        List<FruitData> fruits = new ArrayList<>();
        FruitData fruit;

        for(int i=0;i<fruitName.size();i++){
            fruit = new FruitData();
            fruit.setName(fruitName.get(i));
            fruit.setPrice(fruitPrice.get(i));
            fruit.setImgSrc("/images/"+fruitName.get(i)+".png");
            fruit.setColor(fruitColor.get(i));
            fruits.add(fruit);
        }
        return fruits;
    }
    public void setChosenFruitCard(FruitData fruit){
        fruitNameLabel.setText(fruit.getName());
        fruitPriceLabel.setText(MarketMain.CURRENCY+fruit.getPrice());
        image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(fruit.getImgSrc())));
        fruitImg.setImage(image);
        chosenFruitCard.setStyle("-fx-background-color:#"+fruit.getColor()+";\n" +"-fx-background-radius:30;");

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fruits.addAll(getData());

        if(fruits.size()>0){
            setChosenFruitCard(fruits.get(0));
            myListener = new MyListener() {
                @Override
                public void OnClickListener(FruitData fruit) {
                    setChosenFruitCard(fruit);
                }
            };
        }

        int column=0;
        int row=1;

        try {
            for(int i=0;i<fruits.size();i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("item.fxml"));

                    AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(fruits.get(i),myListener);

                if(column==3){
                    column=0;
                    row++;
                }
                grid.add(anchorPane,column++,row); // child,column,row
                //set grid with
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);
                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane,new Insets(10));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
package com.baileyh.cardtracker;

import com.baileyh.cardtracker.util.ArrayUtil;
import com.baileyh.cardtracker.util.WebUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable
{
    private final String[] cardList = {
            "2", "3", "4", "5", "6", "7",
            "8", "9", "10", "J", "Q", "K",
            "A"
    };
    @FXML
    private Button resetButton;
    @FXML
    private CheckBox singleClickCheckBox;
    @FXML
    private MenuItem aboutMenuItem;
    @FXML
    private MenuItem closeMenuItem;
    @FXML
    private ListView<Label> spadesList;
    @FXML
    private ListView<Label> heartsList;
    @FXML
    private ListView<Label> clubsList;
    @FXML
    private ListView<Label> diamondsList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resetButton.setOnAction(event -> loadCards());
        aboutMenuItem.setOnAction(event -> {
            try {
                WebUtil.openWebpage(new URI("https://github.com/baileyholl/CardTracker"));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        });
        closeMenuItem.setOnAction(e -> System.exit(0));
        loadCards();
        spadesList.setOnMouseClicked(event -> toggleCard(event, spadesList));
        heartsList.setOnMouseClicked(event -> toggleCard(event, heartsList));
        clubsList.setOnMouseClicked(event -> toggleCard(event, clubsList));
        diamondsList.setOnMouseClicked(event -> toggleCard(event, diamondsList));
    }

    private void loadCards(ListView<Label> labelListView, String symbol, Color color){
        labelListView.setItems(asObservable(
                ArrayUtil.setColor(ArrayUtil.stringsToLabels(ArrayUtil.appendStringArray(cardList, symbol)), color)));
    }

    private void toggleCard(MouseEvent click, ListView listView){
        if ((click.getClickCount() == 1 && singleClickCheckBox.isSelected()) || click.getClickCount() == 2) {
            Label label = (Label)listView.getSelectionModel().getSelectedItem();
            label.setDisable(!label.isDisabled());
        }
        clearSelected();
    }

    private void clearSelected(){
        spadesList.getSelectionModel().clearSelection();
        heartsList.getSelectionModel().clearSelection();
        diamondsList.getSelectionModel().clearSelection();
        clubsList.getSelectionModel().clearSelection();
    }

    private void loadCards(){
        loadCards(spadesList, "♠", Color.BLACK);
        loadCards(heartsList,"♡", Color.RED);
        loadCards(clubsList, "♣", Color.BLACK);
        loadCards(diamondsList, "♢", Color.RED);
    }

    private ObservableList asObservable(Object... collection){
        return FXCollections.observableArrayList(collection);
    }
}

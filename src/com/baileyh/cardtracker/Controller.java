package com.baileyh.cardtracker;

import com.baileyh.cardtracker.util.WebUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

import static com.baileyh.cardtracker.Constants.cardList;
import static com.baileyh.cardtracker.util.ArrayUtil.*;

public class Controller implements Initializable
{
    @FXML
    private Button resetButton;
    @FXML
    private CheckBox singleClickCheckBox;
    @FXML
    private MenuItem aboutMenuItem;
    @FXML
    private MenuItem closeMenuItem;
    @FXML
    private ListView<Text> spadesList;
    @FXML
    private ListView<Text> heartsList;
    @FXML
    private ListView<Text> clubsList;
    @FXML
    private ListView<Text> diamondsList;
    @FXML
    private RadioMenuItem handCounterMenu;
    @FXML
    private RadioMenuItem cardCounterMenu;
    @FXML
    private Label handsPlayedLabel;
    @FXML
    private Label cardsMarkedLabel;

    private static int handsPlayed;
    private static int cardsMarked;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resetButton.setOnAction(event -> loadCards());
        aboutMenuItem.setOnAction(event -> WebUtil.openWebpage(Constants.gitURL));
        closeMenuItem.setOnAction(e -> System.exit(0));
        spadesList.setOnMouseClicked(event -> toggleCard(event, spadesList));
        heartsList.setOnMouseClicked(event -> toggleCard(event, heartsList));
        clubsList.setOnMouseClicked(event -> toggleCard(event, clubsList));
        diamondsList.setOnMouseClicked(event -> toggleCard(event, diamondsList));
        handCounterMenu.setOnAction(event -> handsPlayedLabel.setVisible(handCounterMenu.isSelected()));
        cardCounterMenu.setOnAction(event -> cardsMarkedLabel.setVisible(cardCounterMenu.isSelected()));
        loadCards();
    }


    private void toggleCard(MouseEvent click, ListView listView){
        if ((click.getClickCount() == 1 && singleClickCheckBox.isSelected()) || click.getClickCount() == 2 && listView.getSelectionModel().getSelectedItem() != null) {
            Text text = (Text) listView.getSelectionModel().getSelectedItem();
            text.setDisable(!text.isDisabled());
            if(text.isDisabled()){
                cardsMarked += 1;
                text.setStrikethrough(true);
                text.setOpacity(.2);
            }else {
                cardsMarked -= 1;
                text.setStrikethrough(false);
                text.setOpacity(1);
            }
            handsPlayed = (int) Math.floor(cardsMarked / 4);
            updateLabels();
        }
        clearSelected();
    }

    /**
     * Clears the selection values. Used to stop the highlighting of boxes in multiple lists at a time.
     */
    private void clearSelected(){
        spadesList.getSelectionModel().clearSelection();
        heartsList.getSelectionModel().clearSelection();
        diamondsList.getSelectionModel().clearSelection();
        clubsList.getSelectionModel().clearSelection();
    }

    private void updateLabels(){
        cardsMarkedLabel.setText("Cards Marked: " + cardsMarked);
        handsPlayedLabel.setText("Hands Played: " + handsPlayed);
    }
    /**
     * Fills the listviews with the standard deck of cards.
     */
    private void loadCards(){
        Font font = new Font(17);
        loadCards(spadesList, "♠", font, Color.BLACK);
        loadCards(heartsList,"♡", font, Color.RED);
        loadCards(clubsList, "♣", font, Color.BLACK);
        loadCards(diamondsList, "♢", font, Color.RED);
        handsPlayed = 0;
        cardsMarked = 0;
        updateLabels();
    }
    /**
     * Loads the array of labels into the list views with a given text color and symbol to be appended at the end of the
     * cardList.
     * Creates an observable list of the desired colored label array and sets the items of the given listview.
     * @param labelListView Listview to add the labels to
     * @param symbol String to be appended at the end of the card array.
     * @param font The font of the label boxes
     * @param color Color of the label text
     */
    private void loadCards(ListView<Text> labelListView, String symbol, Font font, Color color){
        labelListView.setItems(asObservable(stringsToText(appendStringArray(cardList, symbol), font, color)));
    }

    private ObservableList asObservable(Object... collection){
        return FXCollections.observableArrayList(collection);
    }
}

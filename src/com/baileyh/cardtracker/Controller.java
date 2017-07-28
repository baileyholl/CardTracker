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
        aboutMenuItem.setOnAction(event -> WebUtil.openWebpage(Constants.gitURL));
        closeMenuItem.setOnAction(e -> System.exit(0));
        spadesList.setOnMouseClicked(event -> toggleCard(event, spadesList));
        heartsList.setOnMouseClicked(event -> toggleCard(event, heartsList));
        clubsList.setOnMouseClicked(event -> toggleCard(event, clubsList));
        diamondsList.setOnMouseClicked(event -> toggleCard(event, diamondsList));
        loadCards();
    }


    private void toggleCard(MouseEvent click, ListView listView){
        if ((click.getClickCount() == 1 && singleClickCheckBox.isSelected()) || click.getClickCount() == 2) {
            Label label = (Label)listView.getSelectionModel().getSelectedItem();
            label.setDisable(!label.isDisabled());
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

    /**
     * Fills the listviews with the standard deck of cards.
     */
    private void loadCards(){
        Font font = new Font(17);
        loadCards(spadesList, "♠", font, Color.BLACK);
        loadCards(heartsList,"♡", font, Color.RED);
        loadCards(clubsList, "♣", font, Color.BLACK);
        loadCards(diamondsList, "♢", font, Color.RED);
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
    private void loadCards(ListView<Label> labelListView, String symbol, Font font, Color color){
        labelListView.setItems(asObservable(
                setColor(stringsToLabels(appendStringArray(cardList, symbol), font), color)));
    }

    private ObservableList asObservable(Object... collection){
        return FXCollections.observableArrayList(collection);
    }
}

package seedu.nuke.gui.component;

import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.PopupWindow;

import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class AutoCompleteTextField extends TextField {
    private final SortedSet<String> entries;
    private ContextMenu entriesPopup;

    public AutoCompleteTextField() {
        super();
        this.entries = new TreeSet<>();
        this.entriesPopup = new ContextMenu();
        entriesPopup.setStyle("-fx-font-family: Consolas; -fx-font-size: 12pt; -fx-border-color: lightgrey; -fx-border-radius: 3");
        setListener();
    }

    public ContextMenu getEntriesPopup() {
        return entriesPopup;
    }

    private void setListener() {
        //Add "suggestions" by changing text
        textProperty().addListener((observable, oldValue, newValue) -> {
            String enteredText = getText().trim();
            // Hide suggestions if there in no input
            if (enteredText.isBlank()) {
                entriesPopup.hide();
            } else {
                // Filters suggestions in case-insensitive manner
                List<String> filteredEntries = entries.stream()
                        .filter(e -> e.toLowerCase().contains(enteredText.toLowerCase()))
                        .collect(Collectors.toList());
                // Show suggestions if present
                if (!filteredEntries.isEmpty()) {
                    // Create popup
                    int y = (filteredEntries.size())*12;
                    populatePopup(filteredEntries, enteredText);
                    //if (!entriesPopup.isShowing()) {
                    entriesPopup.show(AutoCompleteTextField.this, Side.TOP, 0, -y); //position of popup
                    //}
                    // Hide popup if no suggestions
                } else {
                    entriesPopup.hide();
                }
            }
        });

        // Hide always by focus-in (optional) and out
        focusedProperty().addListener((observableValue, oldValue, newValue) -> {
            entriesPopup.hide();
        });
    }


    /**
     * Populate the entry set with the given search results. Display is limited to 10 entries, for performance.
     *
     * @param suggestions The set of matching strings.
     */
    private void populatePopup(List<String> suggestions, String searchResult) {
        //List of "suggestions"
        List<CustomMenuItem> menuItems = new LinkedList<>();
        //List size - 10 or founded suggestions count
        int maxEntries = 10;
        int count = Math.min(suggestions.size(), maxEntries);
        //Build list as set of labels
        for (int i = 0; i < count; i++) {
            final String result = suggestions.get(i);
            //label with graphic (text flow) to highlight founded subtext in suggestions
            Label entryLabel = new Label();
            entryLabel.setGraphic(buildTextFlow(result, searchResult));
            entryLabel.setPrefHeight(12);  //don't sure why it's changed with "graphic"
            CustomMenuItem item = new CustomMenuItem(entryLabel, true);
            menuItems.add(item);

            //if any suggestion is select set it into text and close popup
            item.setOnAction(actionEvent -> {
                setText(result);
                positionCaret(result.length());
                entriesPopup.hide();
            });
        }

        //"Refresh" context menu
        entriesPopup.getItems().clear();
        entriesPopup.getItems().addAll(menuItems);
    }


    /**
     * Get the existing set of autocomplete entries.
     *
     * @return The existing autocomplete entries.
     */
    public SortedSet<String> getEntries() { return entries; }

    private TextFlow buildTextFlow(String text, String filter) {
        int filterIndex = text.toLowerCase().indexOf(filter.toLowerCase());
        Text textBefore = new Text(text.substring(0, filterIndex));
        Text textAfter = new Text(text.substring(filterIndex + filter.length()));
        Text textFilter = new Text(text.substring(filterIndex,  filterIndex + filter.length()));
        textFilter.setFill(Color.GREEN);
        textFilter.setStyle("-fx-font-weight: bold");
        return new TextFlow(textBefore, textFilter, textAfter);
    }
}


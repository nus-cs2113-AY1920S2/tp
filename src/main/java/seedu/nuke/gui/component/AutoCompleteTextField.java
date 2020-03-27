package seedu.nuke.gui.component;

import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class AutoCompleteTextField extends TextField {
    private final SortedSet<String> entries;
    private ContextMenu entriesPopup;
    private String enteredText;
    private int startIndex;
    private int endIndex;
    private String prefix;

    public AutoCompleteTextField() {
        super();
        this.entries = new TreeSet<>();
        this.entriesPopup = new ContextMenu();
        entriesPopup.setStyle("-fx-font-family: Consolas; -fx-font-size: 12pt; "
                + "-fx-border-color: lightgrey; -fx-border-radius: 3");
        // setListener();
    }

    public void setEnteredText(String enteredText, int startIndex, int endIndex, String prefix) {
        this.enteredText = enteredText;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.prefix = prefix;
    }

    public void setEnteredText(String enteredText, int startIndex, int endIndex) {
        this.setEnteredText(enteredText, startIndex, endIndex, "");
    }

    public ContextMenu getEntriesPopup() {
        return entriesPopup;
    }

    public void displaySuggestions() {
        if (enteredText.isBlank()) {
            entriesPopup.hide();
        } else {
            // Filters suggestions in case-insensitive manner
            List<String> filteredEntries = entries.stream()
                    .filter(entry -> entry.toLowerCase().contains(enteredText.toLowerCase()))
                    .collect(Collectors.toList());

            // Show suggestions if present
            if (!filteredEntries.isEmpty()) {
                // Create popup
                populatePopup(filteredEntries, enteredText);
                int xDisplacement = startIndex * 10;
                // Position and show popup
                entriesPopup.show(AutoCompleteTextField.this, Side.BOTTOM, xDisplacement, 0);
            } else {
                entriesPopup.hide();
            }
        }
    }

    /**
     * Populate the entry set with the given search results. Display is limited to 10 entries, for performance.
     *
     * @param suggestions The set of matching strings.
     */
    private void populatePopup(List<String> suggestions, String searchResult) {
        // List of suggestions
        List<CustomMenuItem> menuItems = new LinkedList<>();
        // List size - 10 or founded suggestions count
        int maxEntries = 10;
        int count = Math.min(suggestions.size(), maxEntries);
        // Build list as set of labels
        for (int i = 0; i < count; i++) {
            final String result = suggestions.get(i);
            // Label with graphic (text flow) to highlight found subtext in suggestions
            Label entryLabel = new Label();
            entryLabel.setGraphic(buildTextFlow(result, searchResult));
            entryLabel.setPrefHeight(12);  //don't sure why it's changed with "graphic"
            CustomMenuItem item = new CustomMenuItem(entryLabel, true);
            menuItems.add(item);

            // Set selected suggestion into text and close popup
            item.setOnAction(actionEvent -> {
                String inputText = getText();
                String before = inputText.substring(0, startIndex).trim();
                String after = inputText.substring(endIndex).trim();
                String resultWithPrefix = prefix.isEmpty() ? result : (prefix + " " + result);
                String combinedBefore = before.isEmpty() ? resultWithPrefix : (before + " " + resultWithPrefix);
                setText(String.format("%s %s", combinedBefore, after));

                // Position caret just after change
                positionCaret(combinedBefore.length() + 1);
                entriesPopup.hide();
            });
        }

        // Refresh context menu
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


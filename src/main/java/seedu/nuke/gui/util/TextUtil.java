package seedu.nuke.gui.util;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class TextUtil {

    public static Text createText(String input) {
        return new Text(input);
    }

    public static Text createText(String input, Color color) {
        Text text = new Text(input);
        text.setFill(color);
        return text;
    }
}

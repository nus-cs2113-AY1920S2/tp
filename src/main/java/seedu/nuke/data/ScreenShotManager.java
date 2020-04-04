package seedu.nuke.data;

import seedu.nuke.data.storage.Decoder;
import seedu.nuke.data.storage.Encoder;
import seedu.nuke.directory.Module;
import seedu.nuke.exception.CorruptedFileException;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 * a screen shot manager that manages all scree shots and deals with operations such as save screen shots and undo.
 */
public class ScreenShotManager {
    private static Stack<ScreenShot> undoStack = new Stack<>();
    private static Stack<ScreenShot> redoStack = new Stack<>();

    public static void setIsLastCommandRedo(boolean isLastCommandRedo) {
        ScreenShotManager.isLastCommandRedo = isLastCommandRedo;
    }

    private static boolean isLastCommandRedo = false;

    private static ScreenShot popLastScreenShot() throws EmptyStackException {
        ScreenShot tmp = undoStack.pop();
        redoStack.push(tmp);
        return undoStack.peek();
    }

    private static ScreenShot peekLastScreenShot() {
        return undoStack.peek();
    }

    /**
     * revert to the last changed state for moduleList for undo feature.
     *
     * @throws IOException exception is thrown when error occurred during IO operation.
     * @throws CorruptedFileException exception is thrown when converting a corrupted string to moduleList.
     * @throws EmptyStackException exception is thrown when user trying to undo at the initial state.
     */
    public static void undo() throws IOException, CorruptedFileException, EmptyStackException {
        ScreenShot lastScreenShot = popLastScreenShot();
        String moduleListString = lastScreenShot.getModuleListString();
        InputStream is = new ByteArrayInputStream(moduleListString.getBytes());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        ArrayList<Module> moduleList = new Decoder(bufferedReader).decode();
        ModuleManager.setModuleList(moduleList);
        bufferedReader.close();
    }

    /**
     * redo the command which was undo by the user.
     *
     * @throws IOException exception is thrown when error occurred during IO operation.
     * @throws CorruptedFileException exception is thrown when converting a corrupted string to moduleList.
     * @throws EmptyStackException exception is thrown when user trying to undo at the initial state.
     */
    public static void redo() throws IOException, CorruptedFileException {
        ScreenShot redoScreenShot = popRedoScreenShot();
        String moduleListString = redoScreenShot.getModuleListString();
        InputStream is = new ByteArrayInputStream(moduleListString.getBytes());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        ArrayList<Module> moduleList = new Decoder(bufferedReader).decode();
        ModuleManager.setModuleList(moduleList);
        bufferedReader.close();
        isLastCommandRedo = true;
    }

    private static ScreenShot popRedoScreenShot() {
        return redoStack.pop();
    }

    /**
     * Save the moduleList as a string if it was changed.
     */
    public static void saveScreenShot() {
        String currentModuleListString = new Encoder(ModuleManager.getModuleList()).encode();
        ScreenShot screenShot = new ScreenShot(currentModuleListString);
        if (getScreenShotSize() == 0) {
            undoStack.push(screenShot);
            return;
        }
        ScreenShot lastScreenShot = peekLastScreenShot();
        String lastModuleListString = lastScreenShot.getModuleListString();
        if (!lastModuleListString.equals(currentModuleListString)) {
            undoStack.push(screenShot);
            if (!isLastCommandRedo) {
                popAllRedoStack();
            }
        }
    }

    private static void popAllRedoStack() {
        while (!redoStack.isEmpty()) {
            redoStack.pop();
        }
    }

    private static int getScreenShotSize() {
        return undoStack.size();
    }

}

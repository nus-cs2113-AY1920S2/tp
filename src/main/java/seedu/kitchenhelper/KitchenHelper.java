package seedu.kitchenhelper;

import seedu.kitchenhelper.command.Command;
import seedu.kitchenhelper.command.CommandResult;
import seedu.kitchenhelper.command.ExitCommand;
import seedu.kitchenhelper.notification.ChoreNotification;
import seedu.kitchenhelper.notification.IngredientNotification;
import seedu.kitchenhelper.object.Expenditure;
import seedu.kitchenhelper.storage.Storage;
import seedu.kitchenhelper.exception.KitchenHelperException;
import seedu.kitchenhelper.object.Chore;
import seedu.kitchenhelper.object.Recipe;
import seedu.kitchenhelper.object.ingredient.Ingredient;
import seedu.kitchenhelper.parser.Parser;
import seedu.kitchenhelper.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

public class KitchenHelper {
    public final Logger kitchenLogs = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public ArrayList<Ingredient> ingredientList = new ArrayList<>();
    public ArrayList<Recipe> recipeList = new ArrayList<>();
    public ArrayList<Chore> choreList = new ArrayList<>();
    
    private Ui ui;
    private Storage storage;

    /**
     * Retrieves private ui from main class so other classes can use
     * to write to standard output or read from standard input without
     * triggering errors during runtest.
     *
     * @return private ui from this main class.
     */
    private Ui getUi() {
        return ui;
    }

    private void start() {
        ui = new Ui();
        String userChoice = ui.getUserChoice();
        ui.validUserChoice(userChoice);
        ui.showWelcomeMessage();
        createNewFiles();
        if (userChoice.trim().equals("1")) {
            storage = new Storage("outputIngredient.txt", "outputRecipe.txt",
                    "outputChore.txt", "outputExpenditure.txt");
            loadFromStorage();
        } else if (userChoice.trim().equals("2")) {
            storage = new Storage("outputIngredientCopy.txt", "outputRecipeCopy.txt",
                    "outputChoreCopy.txt", "outputExpenditureCopy.txt");
            loadFromStorage();
        }
    }


    /**
     * Loads data from storage files into respective ingredient, recipe and chore lists as well as weekly expenditure.
     */
    private void loadFromStorage() {
        try {
            ingredientList = new ArrayList<>(storage.getIngredientData());
            recipeList = new ArrayList<>(storage.getRecipeData());
            choreList = new ArrayList<>(storage.getChoreData());
            storage.loadExpenditureData();
            Expenditure.getInstance().setUi(getUi());
        } catch (FileNotFoundException err) {
            ingredientList = new ArrayList<>();
            recipeList = new ArrayList<>();
            choreList = new ArrayList<>();
            Expenditure.getInstance().loadExpenditureVariables(0, 0, null);
            Expenditure.getInstance().setUi(getUi());
        }
    }

    /**
     * Create storage output files if they do not exist.
     */
    private void createNewFiles() {
        File fileChore = new File("outputChore.txt");
        File fileIngredient = new File("outputIngredient.txt");
        File fileRecipe = new File("outputRecipe.txt");
        File fileExpenditure = new File("outputExpenditure.txt");
        File fileChoreCopy = new File("outputChoreCopy.txt");
        File fileIngredientCopy = new File("outputIngredientCopy.txt");
        File fileRecipeCopy = new File("outputRecipeCopy.txt");
        File fileExpenditureCopy = new File("outputExpenditureCopy.txt");

        try {
            if (!fileChore.exists()) {
                fileChore.createNewFile();
            }

            if (!fileIngredient.exists()) {
                fileIngredient.createNewFile();
            }

            if (!fileRecipe.exists()) {
                fileRecipe.createNewFile();
            }

            if (!fileExpenditure.exists()) {
                fileExpenditure.createNewFile();
            }

            if (!fileChore.exists()) {
                fileChoreCopy.createNewFile();
            }

            if (!fileIngredient.exists()) {
                fileIngredientCopy.createNewFile();
            }

            if (!fileRecipe.exists()) {
                fileRecipeCopy.createNewFile();
            }

            if (!fileExpenditure.exists()) {
                fileExpenditureCopy.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void run() throws KitchenHelperException {
        setUpLogger();
        start();
        showNotifications();
        runCommandLoopUntilExitCommand();
        exit();
    }
    
    private void exit() {
        System.exit(0);
    }

    private void setUpLogger() throws KitchenHelperException {
        /*
        Output to console only when a serious failure has caused normal
        execution of the program
         */
        LogManager.getLogManager().reset();
        ConsoleHandler consoleOutput = new ConsoleHandler();
        consoleOutput.setLevel(Level.SEVERE);
        kitchenLogs.addHandler(consoleOutput);

        /*
        Logs all information above the Level.Fine to a log file
         */
        try {
            FileHandler logFile = new FileHandler("KitchenLogs.log");
            logFile.setFormatter(new SimpleFormatter());
            logFile.setLevel(Level.FINE);
            kitchenLogs.addHandler(logFile);
        } catch (IOException e) {
            throw new KitchenHelperException("Error in Logging");
        }
    }
    
    private void runCommandLoopUntilExitCommand() {
        Command command;
        String userCommandInput = "";
        
        do {
            try {
                userCommandInput = ui.getUserCommand();
                command = new Parser().parseUserCommand(userCommandInput);
                CommandResult result = executeCommand(command);
                ui.showResultToUser(result);
                ui.printDivider();
            } catch (KitchenHelperException e) {
                ui.printInvalidCmd();
                ui.print(e.getMessage());
                ui.printDivider();
            }
        } while (!userCommandInput.equalsIgnoreCase(ExitCommand.COMMAND_WORD));

    }

    private void showNotifications() {
        String choreNotification;
        choreNotification = new ChoreNotification().getNotifications(choreList);
        System.out.println(choreNotification);
        String ingredientNotification;
        ingredientNotification = new IngredientNotification().getNotifications(ingredientList);
        System.out.println(ingredientNotification);
    }
    
    public static void main(String[] args) throws KitchenHelperException {
        new KitchenHelper().run();
    }
    
    /**
     * Executes the command and return result.
     *
     * @param command the command being executed.
     * @return the return message of the command.
     */
    public CommandResult executeCommand(Command command) {
        try {
            CommandResult result = command.execute(ingredientList, recipeList, choreList);
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

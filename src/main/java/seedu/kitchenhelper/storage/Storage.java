package seedu.kitchenhelper.storage;

import seedu.kitchenhelper.object.Expenditure;
import seedu.kitchenhelper.object.Recipe;
import seedu.kitchenhelper.object.Chore;
import seedu.kitchenhelper.object.ingredient.Ingredient;
import seedu.kitchenhelper.object.ingredient.Dairy;
import seedu.kitchenhelper.object.ingredient.Drink;
import seedu.kitchenhelper.object.ingredient.Fruit;
import seedu.kitchenhelper.object.ingredient.Meat;
import seedu.kitchenhelper.object.ingredient.Staple;
import seedu.kitchenhelper.object.ingredient.Vegetable;
import seedu.kitchenhelper.object.ingredient.Miscellaneous;

import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;


/**
 * Storage class to get/load and store data.
 */
public class Storage {

    private String filePathIngredient;
    private String filePathRecipe;
    private String filePathChore;
    private String filePathExpenditure;



    /**
     * Constructor for Storage.
     * @param filePathIngredient String of filepath for stored Ingredient data.
     * @param filePathRecipe String of filepath for stored Recipe data.
     * @param filePathChore String of filepath for stored Chore data.
     * @param filePathExpenditure String of filepath for stored Expenditure data.
     */
    public Storage(String filePathIngredient, String filePathRecipe, String filePathChore, String filePathExpenditure) {
        this.filePathIngredient = filePathIngredient;
        this.filePathRecipe = filePathRecipe;
        this.filePathChore = filePathChore;
        this.filePathExpenditure = filePathExpenditure;
    }

    public static void copyFile(File source, File dest) {
        try {
            Files.copy(source.toPath(), dest.toPath(),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the saved Ingredient data from text file.
     * @return ArrayList Contains data from saved text file
     * @throws FileNotFoundException If file from file path does not exists.
     */
    public ArrayList<Ingredient> getIngredientData() throws FileNotFoundException {
        ArrayList<Ingredient> ingredientList = new ArrayList<>();
        File file = new File(filePathIngredient);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNext()) {

            String userData = scanner.nextLine();
            String[] getName = userData.split("/n ");
            String[] getCat = getName[1].split(" /c ");
            String[] getQuantity = getCat[1].split(" /q ");
            String[] getPrice = getQuantity[1].split(" /p ");
            String[] getExpiry = getPrice[1].split(" /e ");

            String name = getCat[0];
            String category =  getQuantity[0];
            Integer quantity = Integer.parseInt(getPrice[0]);
            Double price = Double.parseDouble(getExpiry[0]);
            String expiry = getExpiry[1];

            loadingIngredients(name, category, quantity, price, expiry, ingredientList);
        }
        scanner.close();
        return ingredientList;
    }


    /**
     * Loads the ingredient into the ArrayList according to the category type.
     *
     * @param name name of the ingredient.
     * @param category category of the ingredient.
     * @param quantity number of serving of ingredient.
     * @param price cost of the ingredient.
     * @param expiry ingredient expiry date.
     * @param ingredientList the ArrayList to store ingredients.
     */
    public void loadingIngredients(String name, String category, Integer quantity, Double price, String expiry,
                            ArrayList<Ingredient> ingredientList) {

        switch (category.toLowerCase()) {
        case "dairy":
            Ingredient diary = new Dairy(name, category, quantity, price, expiry);
            ingredientList.add(diary);
            break;
        case "drink": {
            Ingredient drink = new Drink(name, category, quantity, price, expiry);
            ingredientList.add(drink);
            break;
        }
        case "fruit": {
            Ingredient fruit = new Fruit(name, category, quantity, price, expiry);
            ingredientList.add(fruit);
            break;
        }
        case "meat": {
            Ingredient meat = new Meat(name, category, quantity, price, expiry);
            ingredientList.add(meat);
            break;
        }
        case "miscellaneous": {
            Ingredient miscellaneous = new Miscellaneous(name, category, quantity, price, expiry);
            ingredientList.add(miscellaneous);
            break;
        }
        case "staple": {
            Ingredient staple = new Staple(name, category, quantity, price, expiry);
            ingredientList.add(staple);
            break;
        }
        case "vegetable": {
            Ingredient vegetable = new Vegetable(name, category, quantity, price, expiry);
            ingredientList.add(vegetable);
            break;
        }
        default:
            throw new IllegalStateException("Unexpected value: " + category);
        }
    }


    /**
     * Gets the saved Recipe data from text file.
     * @return ArrayList Contains data from saved text file
     * @throws FileNotFoundException If file from file path does not exists.
     */
    public ArrayList<Recipe> getRecipeData() throws FileNotFoundException {
        ArrayList<Recipe> recipeList = new ArrayList<>();
        File file = new File(filePathRecipe);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNext()) {

            Recipe freshRecipe = new Recipe();
            ArrayList<Ingredient> recipeItems = new ArrayList<>();

            String userData = scanner.nextLine();
            String removeQuantity = userData.substring(userData.length() - 1);
            Integer ingredientQuantity = Integer.parseInt(removeQuantity);
            String newUserData = userData.substring(0, userData.length() - 1);
            String[] recipeName = newUserData.split("/n ");
            String addRecipeName = recipeName[1].substring(0, recipeName[1].length() - 2);
            Integer count = ingredientQuantity + 1;
            freshRecipe.setRecipeNameForStorage(addRecipeName);

            while (count > 1) {
                String command = recipeName[count];
                String[] getName = command.split(" /c ");
                String[] getCat = getName[1].split(" /q ");
                String[] getQuantity = getCat[1].split(" /p ");
                String[] getPrice = getQuantity[1].split(" /e ");

                String name = getName[0];
                String category = getCat[0];
                Integer quantity = Integer.parseInt(getQuantity[0]);
                Double price = Double.parseDouble(getPrice[0]);
                String expiry = getPrice[1].substring(0, getPrice[1].length() - 2);

                loadingRecipeItems(name, category, quantity, price, expiry, recipeItems);

                count -= 1;
            }
            freshRecipe.addIngredientsToRecipeFromArrayList(recipeItems);
            recipeList.add(freshRecipe);
        }
        scanner.close();
        return recipeList;
    }


    /**
     * Loads the ingredient into the ArrayList according to the category type.
     *
     * @param name name of the ingredient.
     * @param category category of the ingredient.
     * @param quantity number of serving of ingredient.
     * @param price cost of the ingredient.
     * @param expiry ingredient expiry date.
     * @param recipeItems the ArrayList to store ingredients of the recipe.
     */
    public void loadingRecipeItems(String name, String category, Integer quantity, Double price, String expiry,
                                    ArrayList<Ingredient> recipeItems) {

        switch (category.toLowerCase()) {
        case "dairy":
            Ingredient diary = new Dairy(name, category, quantity, price, expiry);
            recipeItems.add(diary);
            break;
        case "drink": {
            Ingredient drink = new Drink(name, category, quantity, price, expiry);
            recipeItems.add(drink);
            break;
        }
        case "fruit": {
            Ingredient fruit = new Fruit(name, category, quantity, price, expiry);
            recipeItems.add(fruit);
            break;
        }
        case "meat": {
            Ingredient meat = new Meat(name, category, quantity, price, expiry);
            recipeItems.add(meat);
            break;
        }
        case "miscellaneous": {
            Ingredient miscellaneous = new Miscellaneous(name, category, quantity, price, expiry);
            recipeItems.add(miscellaneous);
            break;
        }
        case "staple": {
            Ingredient staple = new Staple(name, category, quantity, price, expiry);
            recipeItems.add(staple);
            break;
        }
        case "vegetable": {
            Ingredient vegetable = new Vegetable(name, category, quantity, price, expiry);
            recipeItems.add(vegetable);
            break;
        }
        default:
            throw new IllegalStateException("Unexpected value: " + category);
        }
    }


    /**
     * Gets the saved Chore data from text file.
     * @return ArrayList Contains data from saved text file
     * @throws FileNotFoundException If file from file path does not exists.
     */
    public ArrayList<Chore> getChoreData() throws FileNotFoundException {

        ArrayList<Chore> choreList = new ArrayList<>();
        File file = new File(filePathChore);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNext()) {
            String userData = scanner.nextLine();
            char isDone = userData.charAt(1);
            String[] description = userData.substring(4).split(" \\(by: ");
            Chore todo =
                    Chore.createChoreWhenLoadFile(description[0],
                            description[1].substring(0, description[1].length() - 1));

            if (isDone == '/') {
                todo.markAsDone();
            }
            choreList.add(todo);
        }
        scanner.close();
        return choreList;
    }

    /**
     * Loads the saved Expenditure data from the text file
     * into the singleton Expenditure class.
     *
     * @throws FileNotFoundException if file from file path does not exist.
     */
    public void loadExpenditureData() throws FileNotFoundException {

        File file = new File(filePathExpenditure);
        Scanner scanner = new Scanner(file);

        try {
            String userData = scanner.nextLine();
            String[] variables = userData.split(",");
            int expenditureIndex = variables[0].indexOf('$') + 1;
            double expenditure = Double.parseDouble(variables[0].substring(expenditureIndex));
            int amountUsedIndex = variables[1].indexOf("$") + 1;
            double amountUsed = Double.parseDouble(variables[1].substring(amountUsedIndex));

            DateFormat dateFormat = new SimpleDateFormat("EEE dd/MM/yyyy HH:mm:ss");
            Date lastSavedDate = dateFormat.parse(variables[2]);
            Expenditure.getInstance().loadExpenditureVariables(expenditure, amountUsed, lastSavedDate);
        } catch (NoSuchElementException | IndexOutOfBoundsException | ParseException e) {
            Expenditure.getInstance().loadExpenditureVariables(0, 0, null);
        } finally {
            scanner.close();
        }
    }


    /**
     * Saves and stores the ingredients in ArrayList Ingredient into a text file.
     * @param ingredientList ArrayList.
     */
    public static void saveIngredientData(ArrayList<Ingredient> ingredientList) {
        try {
            FileWriter fw = new FileWriter("outputIngredient.txt");
            for (Ingredient ingredient : ingredientList) {
                fw.write(ingredient.toString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException err) {
            err.printStackTrace();
        }
    }


    /**
     * Saves the recipe in recipeList ArrayList and recipe ingredients in recipeItems ArrayList into a text file.
     * @param recipeList ArrayList.
     */
    public static void saveRecipeData(ArrayList<Recipe> recipeList) {
        try {
            FileWriter fw = new FileWriter("outputRecipe.txt");
            for (Recipe recipe : recipeList) {
                fw.write(recipe.toString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException err) {
            err.printStackTrace();
        }
    }


    /**
     * Saves the chores in choreList into a text file.
     * @param choreList ArrayList.
     */
    public static void saveChoreData(ArrayList<Chore> choreList) {
        try {
            FileWriter fw = new FileWriter("outputChore.txt");
            for (Chore chore : choreList) {
                fw.write(chore.toString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    /**
     * Saves the Expenditure data into a text file.
     */
    public static void saveExpenditureData() {
        try {
            FileWriter fw = new FileWriter("outputExpenditure.txt");
            fw.write(Expenditure.getInstance().saveExpenditureFile());
            fw.close();
        } catch (IOException err) {
            err.printStackTrace();
        }
    }
}
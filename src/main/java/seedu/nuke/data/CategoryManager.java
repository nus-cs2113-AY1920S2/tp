package seedu.nuke.data;

import seedu.nuke.directory.Category;
import seedu.nuke.directory.Module;
import seedu.nuke.directory.Task;
import seedu.nuke.exception.DuplicateDataException;

import java.util.ArrayList;

public class CategoryManager {
    private ArrayList<Category> categoryList;

    private static final String NO_KEYWORD = "";

    /**
     * Constructs the Category Manager with initialised categories.
     *
     * @param parentModule
     *  The parent module of the category in the directory
     */
    public CategoryManager(Module parentModule) {
        categoryList = initialiseCategories(parentModule);
    }

    /**
     * Initialises the Category List with common default categories.
     *
     * @param parentModule
     *  The parent module of the category in the directory
     * @return
     *  The Category List with the initialised categories
     */
    public ArrayList<Category> initialiseCategories(Module parentModule) {
        ArrayList<Category> initialTaskCategories = new ArrayList<>();
        initialTaskCategories.add(new Category(parentModule, "Lecture", 1));
        initialTaskCategories.add(new Category(parentModule, "Tutorial", 2));
        initialTaskCategories.add(new Category(parentModule, "Assignment", 4));
        initialTaskCategories.add(new Category(parentModule, "Lab", 3));

        return initialTaskCategories;
    }

    /**
     * Returns the entire Category List
     * @return
     *  The Category List
     */
    public ArrayList<Category> getCategoryList() {
        return categoryList;
    }

    /**
     * Finds a task with the specified category name in the Category List
     * @param categoryName
     *  The name of the category
     * @return
     *  The category with the specified name if found
     * @throws CategoryNotFoundException
     *  If the category is not found in the Category List
     */
    public Category getCategory(String categoryName) throws CategoryNotFoundException {
        for (Category category : categoryList) {
            if (category.getCategoryName().equals(categoryName)) {
                return category;
            }
        }
        throw new CategoryNotFoundException();
    }

    /**
     * Checks for duplicates of the specified category in the Category List.
     * @param toCheck
     *  The category to check
     * @return
     *  <code>TRUE</code> if there exists a duplicate, and <code>FALSE</code> otherwise
     */
    private boolean contains(Category toCheck) {
        for (Category category : categoryList) {
            if (category.isSameCategory(toCheck)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Add a category to the Category List.
     *
     * @param toAdd
     *  The category to be added
     */
    public void add(Category toAdd) throws DuplicateCategoryException {
        if (contains(toAdd)) {
            throw new DuplicateCategoryException();
        } else {
            categoryList.add(toAdd);
        }
    }

    /**
     * Deletes the specified category from the Category List.
     *
     * @param toDelete
     *  The category to be deleted
     */
    public boolean delete(Category toDelete) {
        return categoryList.remove(toDelete);
    }

    /**
     * Deletes a <b>Category</b> with the specified <code>category name</code> from the <b>Category List</b>.
     *
     * @param categoryName
     *  The category name of the <b>Category</b> to be deleted
     * @throws CategoryNotFoundException
     *  If the category with the specified category name is not found in the <b>Category List</b>
     * @see Category
     */
    public Category delete(String categoryName) throws CategoryNotFoundException {
        Category toDelete = getCategory(categoryName);
        categoryList.remove(toDelete);
        return toDelete;
    }

    /**
     * Retrieves the Task List of the category with the specified name
     *
     * @param categoryName
     *  The name of the category to retrieve the Task List from
     * @return
     *  The Task List of the found category
     * @throws CategoryNotFoundException
     *  If the category with the specified name is not found in the Category List
     */
    public TaskManager retrieveList(String categoryName) throws CategoryNotFoundException {
        return getCategory(categoryName).getTasks();
    }

    /**
     * Filter for categories in the Category List with name that contains the specified keyword.
     * Filtering is done in a case-insensitive manner.
     *
     * @param categoryKeyword
     *  The keyword to filter the categories
     * @return
     *  The list of filtered categories
     */
    public ArrayList<Category> filter(String categoryKeyword) {
        ArrayList<Category> filteredCategoryList = new ArrayList<>();
        for (Category category : categoryList) {
            if (category.getCategoryName().toLowerCase().contains(categoryKeyword.toLowerCase())) {
                filteredCategoryList.add(category);
            }
        }
        return filteredCategoryList;
    }

    /**
     * Filter for categories in the Category List with name that matches <b>exactly</b> the specified keyword.
     * Filtering is done in a case-insensitive manner.
     *
     * @param categoryKeyword
     *  The keyword to filter the categories
     * @return
     *  The list of filtered categories
     */
    public ArrayList<Category> filterExact(String categoryKeyword) {
        // Returns all categories in the Category List if no keyword is provided.
        if (categoryKeyword.equals(NO_KEYWORD)) {
            return this.getCategoryList();
        }

        ArrayList<Category> filteredCategoryList = new ArrayList<>();
        for (Category category : categoryList) {
            if (category.getCategoryName().toLowerCase().equals(categoryKeyword.toLowerCase())) {
                filteredCategoryList.add(category);
            }
        }
        return filteredCategoryList;
    }

    public static class CategoryNotFoundException extends Exception {}
    public static class DuplicateCategoryException extends DuplicateDataException {}
}

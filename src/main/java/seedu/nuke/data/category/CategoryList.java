package seedu.nuke.data.category;

import seedu.nuke.exception.DataNotFoundException;
import seedu.nuke.exception.DuplicateDataException;

import java.util.ArrayList;

public class CategoryList {
    ArrayList<Category> categoryList;

    public CategoryList() {
        categoryList = initialiseCategories();
    }

    private ArrayList<Category> initialiseCategories() {
        ArrayList<Category> initialTaskCategories = new ArrayList<>();
        initialTaskCategories.add(new Category("Lecture", 1));
        initialTaskCategories.add(new Category("Tutorial", 2));
        initialTaskCategories.add(new Category("Assignment", 4));
        initialTaskCategories.add(new Category("Lab", 3));

        return initialTaskCategories;
    }

    /**
     * Checks for duplicates of the specified category in the Category List.
     * @param toCheck   The category to check
     * @return <code>TRUE</code> if there exists a duplicate, and <code>FALSE</code> otherwise
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
     * Add a category to the category List.
     *
     * @param toAdd the category to add
     */
    public void add(Category toAdd) throws DuplicateCategoryException {
        if (contains(toAdd)) {
            throw new DuplicateCategoryException();
        } else {
            categoryList.add(toAdd);
        }
    }

    public void delete(Category toDelete) {
        categoryList.remove(toDelete);
    }


    /**
     * Deletes a <b>Category</b> with the specified <code>category name</code> from the <b>Category List</b>.
     *
     * @param categoryName The category name of the <b>Category</b> to be deleted
     * @throws CategoryNotFoundException  If the category with the specified category name is not found in the <b>Category List</b>
     * @see Category
     */
    public Category delete(String categoryName) throws CategoryNotFoundException {
        for (Category category : categoryList) {
            if (category.getCategoryName().equals(categoryName)) {
                categoryList.remove(category);
                return category;
            }
        }
        throw new CategoryNotFoundException();
    }

    public static class CategoryNotFoundException extends DataNotFoundException {}
    public static class DuplicateCategoryException extends DuplicateDataException {}
}

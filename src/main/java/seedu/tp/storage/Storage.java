package seedu.tp.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import seedu.tp.exceptions.DeletionFailedException;
import seedu.tp.exceptions.DuplicateFlashcardNameException;
import seedu.tp.flashcard.Flashcard;
import seedu.tp.flashcard.FlashcardList;
import seedu.tp.group.FlashcardGroup;
import seedu.tp.group.GroupList;
import seedu.tp.studyplan.StudyPlanList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeMap;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static seedu.tp.utils.Constants.LOG_FOLDER;
import static seedu.tp.utils.Constants.SAVE_FOLDER;

/**
 * Class to save/load savables as JSON.
 */
public class Storage {

    private static final Logger LOGGER = Logger.getLogger(Storage.class.getName());
    private static final String FILE_PATH = LOG_FOLDER + "storage.log";
    private static final String FILE_EXTENSION = ".json";
    private static Storage storage = null;
    private Gson gson;

    private Storage() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Flashcard.class, new AClassAdapter<Flashcard>());
        builder.setPrettyPrinting();
        this.gson = builder.create();
    }

    /**
     * Get an instance of the Storage object.
     *
     * @return the static Storage instance
     */
    public static Storage getInstance() {
        if (storage == null) {
            storage = new Storage();
        }
        return storage;
    }

    /**
     * Set up the Storage logger. Call once at the start of the program.
     *
     * @throws IOException when logger set up failed
     */
    public static void setupLogger() throws IOException {
        LOGGER.setLevel(Level.ALL);
        LOGGER.setUseParentHandlers(false);
        FileHandler fileHandler = new FileHandler(FILE_PATH, true);
        fileHandler.setFormatter(new SimpleFormatter());
        LOGGER.addHandler(fileHandler);
    }

    private String getJson(Savable savable) {
        return gson.toJson(savable);
    }

    /**
     * Save the savable as a formatted JSON string.
     *
     * @param savable the savable to save
     * @throws IOException if the save fails
     */
    public void save(Savable savable) throws IOException {
        String pathName = SAVE_FOLDER + "/" + savable.getFileName() + FILE_EXTENSION;
        File file = new File(pathName);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }

        String fileContents;
        if (savable instanceof Flashcard) {
            fileContents = gson.toJson(savable, Flashcard.class);
        } else if (savable instanceof StudyPlanList) {
            fileContents = gson.toJson(((StudyPlanList) savable).getTreeMap(), TreeMap.class);
        } else if (savable instanceof FlashcardList) {
            fileContents = gson.toJson(((FlashcardList) savable).getAllFlashcardNames(), List.class);
        } else if (savable instanceof FlashcardGroup) {
            fileContents = gson.toJson(savable);
        } else {
            return;
        }

        FileWriter fileWriter = new FileWriter(pathName);
        fileWriter.write(fileContents);
        fileWriter.close();
    }

    /**
     * Delete a savable from disk.
     *
     * @param savable the savable to delete
     * @throws DeletionFailedException if the deletion fails
     */
    public void delete(Savable savable) throws DeletionFailedException {
        String pathName = SAVE_FOLDER + "/" + savable.getFileName() + FILE_EXTENSION;
        File file = new File(pathName);

        boolean success = file.delete();
        if (!success) {
            throw new DeletionFailedException();
        }
    }

    /**
     * Call once at the start of the program to load flashcards and groups from file.
     *
     * @param groupList the GroupList to load into
     */
    public List<Flashcard> loadAll(GroupList groupList) {
        final String flashcardsFolderString = SAVE_FOLDER + "/" + Flashcard.FLASHCARDS_FOLDER;
        final String groupsFolderString = SAVE_FOLDER + "/" + FlashcardGroup.GROUPS_FOLDER;

        File flashcardsFolder = new File(flashcardsFolderString);
        File groupsFolder = new File(groupsFolderString);

        List<Flashcard> flashcards = new ArrayList<>();
        if (flashcardsFolder.exists()) {
            for (File f : Objects.requireNonNull(flashcardsFolder.listFiles())) {
                try {
                    Flashcard flashcard = gson.fromJson(new FileReader(f), Flashcard.class);
                    flashcard.initializeObservers();
                    flashcards.add(flashcard);
                    LOGGER.info("File: " + f.toString() + " was loaded from disk.");
                } catch (FileNotFoundException e) {
                    LOGGER.warning("File: " + f.toString() + " was not found when loading from disk.");
                } catch (JsonParseException e) {
                    LOGGER.warning("File: " + f.toString() + " could not be loaded (bad formatting).");
                }
            }
        }

        if (groupsFolder.exists()) {
            for (File f : Objects.requireNonNull(groupsFolder.listFiles())) {
                try {
                    FlashcardGroup group = gson.fromJson(new FileReader(f), FlashcardGroup.class);
                    for (Flashcard flashcard : group.getGroupCards().getFlashcards()) {
                        for (Flashcard fcard : flashcards) {
                            if (flashcard.equals(fcard)) {
                                fcard.attach((group));
                                break;
                            }
                        }
                    }
                    groupList.addFlashcardGroup(group);
                    LOGGER.info("File: " + f.toString() + " was loaded from disk.");
                } catch (FileNotFoundException e) {
                    LOGGER.warning("File: " + f.toString() + " was not found when loading from disk.");
                } catch (JsonParseException e) {
                    LOGGER.warning("File: " + f.toString() + " could not be loaded (bad formatting).");
                }
            }
        }

        return flashcards;
    }

    /**
     * Call once at the start of the program to load flashcard list from file.
     *
     * @param flashcards the flashcards to be added into flashcard list
     * @return the flashcard list created
     */
    public FlashcardList loadFlashcardList(List<Flashcard> flashcards) {
        final String flashcardListFolderString = SAVE_FOLDER + "/" + FlashcardList.FLASHCARD_LIST_FOLDER;
        File flashcardListFolder = new File(flashcardListFolderString);

        if (flashcardListFolder.exists()) {
            for (File file : Objects.requireNonNull(flashcardListFolder.listFiles(
                (dir, name) -> name.equals(FlashcardList.FLASHCARD_LIST_FILE_NAME + FILE_EXTENSION)
            ))) {
                try {
                    List flashcardNameList = gson.fromJson(new FileReader(file), List.class);
                    FlashcardList flashcardList = new FlashcardList();
                    for (Object object : flashcardNameList) {
                        String flashcardName = (String) object;
                        Optional<Flashcard> flashcardOptional =
                            flashcards.stream().filter(o -> o.getName().equals(flashcardName)).findFirst();
                        flashcardOptional.ifPresent(flashcard -> {
                            try {
                                flashcardList.addFlashcard(flashcard);
                            } catch (DuplicateFlashcardNameException e) {
                                // Ignore DuplicateFlashcardNameException
                            }
                        });
                    }
                    LOGGER.info("File: " + file.toString() + " was loaded from disk.");
                    return flashcardList;
                } catch (FileNotFoundException e) {
                    LOGGER.warning("File: " + file.toString() + " was not found when loading from disk.");
                } catch (JsonParseException e) {
                    LOGGER.warning("File: " + file.toString() + " could not be loaded (bad formatting).");
                }
            }
        }

        return new FlashcardList();
    }

    /**
     * Call once at the start of the program to load study plan list from file.
     *
     * @return the loaded StudyPlanList
     */
    @SuppressWarnings("unchecked")
    public StudyPlanList loadStudyPlanList() {
        final String studyPlanListFolderString = SAVE_FOLDER + "/" + StudyPlanList.STUDY_PLAN_LIST_FOLDER;

        File studyPlanListFolder = new File(studyPlanListFolderString);

        if (studyPlanListFolder.exists()) {
            for (File file : Objects.requireNonNull(studyPlanListFolder.listFiles(
                (dir, name) -> name.equals(StudyPlanList.STUDY_PLAN_LIST_FILE_NAME + FILE_EXTENSION)
            ))) {
                try {
                    TreeMap studyPlanListTreeMapRaw = gson.fromJson(new FileReader(file), TreeMap.class);
                    TreeMap<LocalDate, List<Integer>> studyPlanListTreeMap = new TreeMap<>();
                    ((TreeMap<String, List<Double>>) studyPlanListTreeMapRaw).forEach((key, value) -> {
                        List<Integer> integerList = new ArrayList<>();
                        for (Double index : value) {
                            integerList.add(index.intValue());
                        }
                        studyPlanListTreeMap.put(LocalDate.parse(key), integerList);
                    });
                    StudyPlanList studyPlanList = new StudyPlanList(studyPlanListTreeMap);
                    LOGGER.info("File: " + file.toString() + " was loaded from disk.");
                    return studyPlanList;
                } catch (FileNotFoundException e) {
                    LOGGER.warning("File: " + file.toString() + " was not found when loading from disk.");
                } catch (JsonParseException e) {
                    LOGGER.warning("File: " + file.toString() + " could not be loaded (bad formatting).");
                }
            }
        }

        return new StudyPlanList();
    }
}

//package seedu.duke;
//
//import java.io.*;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//
//TODO update to card & cardlist
//
//public class Storage  {
//
//    String dir = System.getProperty("user.dir");
//    Path filepath = Paths.get(dir, "data", "taskList.txt"); //todo change filepath
//    String filepathStr  = String.valueOf(filepath);;
//    File dukeFile = new File(filepathStr);
//
//    public ArrayList<Task> loadDuke() {
//        ArrayList<Task> loadTasks = new ArrayList<>();
//        if(!dukeFile.exists()) {
//            try {
//                // creates all sub dir if not exist
//                dukeFile.getParentFile().mkdirs();
//                dukeFile.createNewFile();
//            }
//            catch (IOException e){
//                System.out.println("File creation error");
//            }
//        }
//        else {
//            try{
//                FileInputStream fileRead = new FileInputStream(dukeFile);
//                ObjectInputStream objRead = new ObjectInputStream(fileRead);
//
//                loadTasks = (ArrayList<Task>) objRead.readObject();
//                objRead.close();
//            }
//            catch (IOException | ClassNotFoundException e) {
//                System.out.println("\nLoad error");
//            }
//        }
//        return loadTasks;
//    }
//
//    public void saveDuke(TaskList saveTasks) {
//        try{
//            FileOutputStream fileWrite = new FileOutputStream(dukeFile);
//            ObjectOutputStream objWrite = new ObjectOutputStream(fileWrite);
//
//            objWrite.writeObject(saveTasks.getTaskList());
//            objWrite.flush();
//            objWrite.close();
//        }
//        catch (IOException e) {
//            System.out.println("\nSave error");
//        }
//    }
//}

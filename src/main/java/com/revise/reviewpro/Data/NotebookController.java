package com.revise.reviewpro.Data;

import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class NotebookController {

    public static List<Note> currentNoteBook;
    public static String filePathofNoteBook;

    public static void SaveNoteBook(List<Note> NoteBooktoSave, File fileHandle) throws IOException {

        FileOutputStream fileOutputStream = new FileOutputStream(fileHandle);
        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);

        try {
            outputStream.writeObject(NoteBooktoSave);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        outputStream.close();
        fileOutputStream.close();

    }

    public static String getFilePathofNoteBook() {
        return filePathofNoteBook;
    }

    public static void setFilePathofNoteBook(String newPath) {
        filePathofNoteBook = newPath;
    }

    public static List<Note> getCurrentNoteBook() {
        return currentNoteBook;
    }

    public static void setCurrentNoteBook(List<Note> currentNoteBook) {
        NotebookController.currentNoteBook = currentNoteBook;
    }

    public static List<Note> LoadNoteBook (File FilePath) {
        try {

            FileInputStream fileInputStream = new FileInputStream(FilePath);
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);

            List<Note> loadedNotes = (List<Note>)inputStream.readObject();
            currentNoteBook = loadedNotes;
            filePathofNoteBook = FilePath.getAbsolutePath();
               for(Note note : loadedNotes){
                   System.out.println(note.getMainText());
               }

            return loadedNotes;
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        return null;
    }
}

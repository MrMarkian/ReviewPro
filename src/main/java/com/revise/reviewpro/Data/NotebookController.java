package com.revise.reviewpro.Data;

import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class NotebookController {

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

    public static List<Note> LoadNoteBook (File FilePath) throws IOException, ClassNotFoundException {
        try {

            FileInputStream fileInputStream = new FileInputStream(FilePath);
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);

            List<Note> loadedNotes = (List<Note>)inputStream.readObject();

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

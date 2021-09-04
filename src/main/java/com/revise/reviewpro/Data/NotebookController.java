package com.revise.reviewpro.Data;

import com.revise.reviewpro.View.DataInterface;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Optional;


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
            System.out.println(EncryptionHandler.encrypt("Bollocks","Manic"));
            return loadedNotes;
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static void EncodeNoteBook(String password){
        for (Note note: DataInterface.allnotes) {
            note.setMainText(EncryptionHandler.encrypt(note.getMainText(),password));
            note.setTitle(EncryptionHandler.encrypt(note.getTitle(),password));

        }
    }

    public static void DecodeNoteBook(String password){
        for(Note note : DataInterface.allnotes){
            note.setMainText(EncryptionHandler.decrypt(note.getMainText(),password));
            note.setTitle(EncryptionHandler.decrypt(note.getTitle(),password));

        }
    }







    static class EncryptionHandler{
        public static String encrypt(String text, String pass) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                Key key = new SecretKeySpec(messageDigest.digest(pass.getBytes(StandardCharsets.UTF_8)), "AES");
                Cipher cipher = Cipher.getInstance("AES");
                cipher.init(Cipher.ENCRYPT_MODE, key);

                byte[] encrypted = cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));
                byte[] encoded = Base64.getEncoder().encode(encrypted);
                System.out.println(Arrays.toString(encoded));
                return new String(encoded, StandardCharsets.UTF_8);

            } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
                throw new RuntimeException("Cannot encrypt", e);
            }
        }

        public static String decrypt(String text, String pass) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                Key key = new SecretKeySpec(messageDigest.digest(pass.getBytes(StandardCharsets.UTF_8)), "AES");
                Cipher cipher = Cipher.getInstance("AES");
                cipher.init(Cipher.DECRYPT_MODE, key);

                byte[] decoded = Base64.getDecoder().decode(text.getBytes(StandardCharsets.UTF_8));
                byte[] decrypted = cipher.doFinal(decoded);
                System.out.println(decrypted);
                return new String(decrypted, StandardCharsets.UTF_8);

            } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
                throw new RuntimeException("Cannot decrypt", e);
            }
        }
    }
}

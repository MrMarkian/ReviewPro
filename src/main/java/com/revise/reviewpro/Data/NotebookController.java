package com.revise.reviewpro.Data;

import com.revise.reviewpro.View.DataInterface;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;

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



public class NotebookController {

    public static String PathOfNoteBook;

    public static void SaveNoteBook(File fileHandle) throws IOException {

        FileOutputStream fileOutputStream = new FileOutputStream(fileHandle);
        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);

        try {
            outputStream.writeObject(DataInterface.allNotes.stream().toList());
            outputStream.writeObject(DataInterface.allQuestions.stream().toList());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        outputStream.close();
        fileOutputStream.close();

    }


    public static void LoadNoteBook (File FilePath) {
        try {

            FileInputStream fileInputStream = new FileInputStream(FilePath);
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);

            List<Note> tmpList = (List<Note>)inputStream.readObject();
            DataInterface.AddAllNotesFromList(tmpList);

            List<Question> tmpQuestions = (List<Question>)inputStream.readObject();
            DataInterface.AddAllQuestionsFromList(tmpQuestions);
            PathOfNoteBook = FilePath.getAbsoluteFile().toString();

        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public static void EncodeNoteBook(String password){
        for (Note note: DataInterface.allNotes) {
            note.setMainText(EncryptionHandler.encrypt(note.getMainText(),password));
            note.setTitle(EncryptionHandler.encrypt(note.getTitle(),password));
        }

        for (Question question : DataInterface.allQuestions){
            question.setQuestionText(EncryptionHandler.encrypt(question.getQuestionText(),password));

        }
    }

    public static void DecodeNoteBook(String password){
        for(Note note : DataInterface.allNotes){
            note.setMainText(EncryptionHandler.decrypt(note.getMainText(),password));
            note.setTitle(EncryptionHandler.decrypt(note.getTitle(),password));

        }

        for (Question question : DataInterface.allQuestions){
            question.setQuestionText(EncryptionHandler.decrypt(question.getQuestionText(),password));

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

                return new String(encoded, StandardCharsets.UTF_8);

            } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR,e.getMessage());
                alert.show();
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

                return new String(decrypted, StandardCharsets.UTF_8);

            } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR,e.getMessage());
                alert.show();
                throw new RuntimeException("Cannot decrypt", e);
            }
        }
    }
}

package co.weirddoeats.game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {

    public static final String FILENAME = "highscore.txt";

    public void writeScore(int score) {

        try {
            FileWriter writer = new FileWriter(FILENAME);
            BufferedWriter bWriter = new BufferedWriter(writer);
            bWriter.write(score);
            bWriter.close();

        }catch(Exception ex){
            String msg = ex.getMessage();
            System.out.println("Exception " + msg);
        }
    }


}

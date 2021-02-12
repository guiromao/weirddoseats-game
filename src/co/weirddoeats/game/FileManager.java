package co.weirddoeats.game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {


    private void highScore(String file, int score) throws IOException {


        FileWriter writer = new FileWriter(file);
        BufferedWriter bWriter = new BufferedWriter(writer);

        bWriter.write(score);

        bWriter.close();

    }


}

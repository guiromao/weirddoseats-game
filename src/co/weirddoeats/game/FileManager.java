package co.weirddoeats.game;

import java.io.*;

public class FileManager {

    public static final String FILENAME = "resources/highscore.txt";

    public void writeScore(int score) {

        try {
            FileWriter writer = new FileWriter(FILENAME);
            BufferedWriter bWriter = new BufferedWriter(writer);
            bWriter.write(String.valueOf(score));
            bWriter.close();

        }catch(Exception ex){
            String msg = ex.getMessage();
            System.out.println("Exception " + msg);
        }
    }

    public int readScore(){
        int result = 0;

        try{
            FileReader reader = new FileReader(FILENAME);
            BufferedReader bReader = new BufferedReader(reader);
            String line = bReader.readLine();

            if(line != null){
                result = Integer.parseInt(line);
            }
            bReader.close();
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }

        return result;
    }


}

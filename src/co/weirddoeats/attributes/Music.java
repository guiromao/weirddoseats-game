package co.weirddoeats.attributes;

import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class Music {

    private String filename;
    private String songName;
    private Player player;
    private Player soundPlayer;
    private int songIndex;

    public Music() {
        songIndex = 0;
    }

    public void play(String file) {
        filename = "resources/" + file;
        songName = file;
        try {
            FileInputStream fis = new FileInputStream(filename);
            BufferedInputStream bis = new BufferedInputStream(fis);
            player = new Player(bis);
        } catch (Exception e) {
            System.out.println("Problem playing file " + filename);
            System.out.println(e);
        }

        // run in new thread to play in background
        new Thread() {
            public void run() {
                try {
                    player.play();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }.start();

    }

    public void stop() {
        if (player != null) {
            player.close();
        }
    }

    public String getSongName() {
        return songName;
    }

    public void playNextSong() {
        stop();
        if(songIndex < (MusicType.values().length)){
            MusicType[] songs = MusicType.values();
            play(songs[songIndex].getSong());
            songIndex++;
        }
    }

    public void playSound(String sound){
        String file = "resources/" + sound;
        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            soundPlayer = new Player(bis);
        } catch (Exception e) {
            System.out.println("Problem playing file " + file);
            System.out.println(e);
        }

        // run in new thread to play in background
        new Thread() {
            public void run() {
                try {
                    soundPlayer.play();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }.start();
    }

}

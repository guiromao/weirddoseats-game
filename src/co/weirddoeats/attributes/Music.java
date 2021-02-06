package co.weirddoeats.attributes;

import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class Music {

    private String filename;
<<<<<<< HEAD
    private String songName;
    private Player player;

    public Music(String music){
        songName = music;
        filename = "resources/" + songName;
    }

    public void play(){
=======
    private Player player;
    private int songIndex;

    public Music(){
        songIndex = 0;
    }

    public void play(String file){
        filename = "resources/" + file;
>>>>>>> master
        try {
            FileInputStream fis     = new FileInputStream(filename);
            BufferedInputStream bis = new BufferedInputStream(fis);
            player = new Player(bis);
        }
        catch (Exception e) {
            System.out.println("Problem playing file " + filename);
            System.out.println(e);
        }

        // run in new thread to play in background
        new Thread() {
            public void run() {
                try { player.play(); }
                catch (Exception e) { System.out.println(e); }
            }
        }.start();
    }

    public void stop(){
<<<<<<< HEAD
        player.close();
    }

    public String getSongName(){
        return songName;
=======
        if(player != null){
            player.close();
        }
>>>>>>> master
    }

    public void playNextSong(){
        stop();
        MusicType [] songs = MusicType.values();
        int songsLength = songs.length;

        play(songs[songIndex].getSong());

        if(songIndex < songsLength){
            songIndex++;
        }

    }

}

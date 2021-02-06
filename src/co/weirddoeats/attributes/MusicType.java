package co.weirddoeats.attributes;

public enum MusicType {
    ONE("8bitretro.mp3"),
    TWO("happyrock.mp3");

    private String song;

    MusicType(String song){
        this.song = song;
    }

    public String getSong(){
        return song;
    }

}

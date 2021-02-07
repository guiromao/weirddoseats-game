package co.weirddoeats.attributes;

public enum MusicType {
    ONE("8bitretro.mp3"),
    TWO("action1.mp3"),
    THREE("action2.mp3"),
    FOUR("action3.mp3"),
    FIVE("action4.mp3"),
    SIX("action5.mp3"),
    SEVEN("action6.mp3");

    private String song;

    MusicType(String song){
        this.song = song;
    }

    public String getSong(){
        return song;
    }

}

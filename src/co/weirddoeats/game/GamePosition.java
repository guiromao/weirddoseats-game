package co.weirddoeats.game;

import co.weirddoeats.gfx.simplegfx.SimpleGfxGridPosition;

public class GamePosition {

    private String type;
    private SimpleGfxGridPosition position;

    public GamePosition(String type, SimpleGfxGridPosition pos){
        this.type = type;
        position = pos;
    }

    public String getType(){
        return type;
    }

    public SimpleGfxGridPosition getPosition(){
        return position;
    }

}

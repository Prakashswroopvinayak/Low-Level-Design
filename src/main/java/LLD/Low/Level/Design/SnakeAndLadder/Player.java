package LLD.Low.Level.Design.SnakeAndLadder;

import java.security.DigestException;

public class Player {
    String name;
    int pos;

    Player(String name, int pos){
        this.name = name;
        this.pos = pos;

    }

    public  int getPos(){
        return pos;
    }

    public void setPos(int pos){
        this.pos = pos;
    }
}

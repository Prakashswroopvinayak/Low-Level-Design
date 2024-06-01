package LLD.Low.Level.Design.SnakeAndLadder;

public class Jump {
    int start ;
    int   end ;
   JumpType jumpType;
    Jump(int start, int end,  JumpType jumpType){
        this.start = start;
        this.end = end;
        this.jumpType = jumpType;
    }

}

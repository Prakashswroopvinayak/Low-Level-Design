package LowLevelDesign.TicTacToe;

public class Player {

    String id;
    Symbols mySymbol;

    public Player(String id, Symbols mySymbol){
        this.id = id;
        this.mySymbol = mySymbol;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return  this.id;
    }

    public void setMySymbol(Symbols mySymbol){
        this.mySymbol = mySymbol;
    }

    public Symbols getMySymbol(){
        return  this.mySymbol;
    }
}

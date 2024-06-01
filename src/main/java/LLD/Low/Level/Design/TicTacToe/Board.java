package LLD.Low.Level.Design.TicTacToe;

import java.util.Arrays;

public class Board {

    Symbols[][] symbolMatrix ;
    int size;
    public Board(int size){
        symbolMatrix = new Symbols[size][size];
        this.size = size;
        fillSymbolMatrix();
    }

    public void fillSymbolMatrix(){
        for(int i =0; i<size; i++){
            Arrays.fill(symbolMatrix[i],Symbols.EMPTY);
        }
    }

    public void setSymbol(int row, int col, Symbols symbols){
        symbolMatrix[row][col]=symbols;
    }

    public Symbols getSymbol(int row, int col){
        return symbolMatrix[row][col];
    }
}

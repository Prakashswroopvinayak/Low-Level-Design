package LLD.Low.Level.Design.TicTacToe;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class TicTacToeController {

    Board board;
    Deque<Player> players = new LinkedList<>();

    public  TicTacToeController(){
        initializer();
        startGame();
    }

    public static void main(String[] args) {
        TicTacToeController ticTacToeController = new TicTacToeController();
    }

    public void initializer(){

        this.board = new Board(3);

        Player player1 = new Player("1st", Symbols.X);
        Player player2 = new Player("2nd", Symbols.Y);
        players.add(player1);
        players.add(player2);

    }

    public void startGame(){

        Player winner = null;

        while(winner==null){

            printBoard();
            Player player = players.removeFirst();
            System.out.println("Player"+player.id + "  turn");
            System.out.println("Enter position");
            Scanner sc = new Scanner(System.in);
            int row = sc.nextInt();
            int col = sc.nextInt();
            if(row>=board.size || col>=board.size || row<0 || col<0){
                System.out.println("Please inter valid position");
                players.addFirst(player);
                continue;
            }
            if(board.getSymbol(row, col)==Symbols.EMPTY){
                board.setSymbol(row, col,player.mySymbol);
            }else {
                System.out.println("Please choose empty place");
            }

            winner = checkWinner(player, row, col);

            if(winner!=null){
                System.out.println("Winner is "+player.id);
                break;
            }

            if(tie()){
                System.out.println("Game is tie");
                break;
            }

            players.add(player);
        }
    }

    public boolean tie(){

        boolean isTie = true;

        for(int i =0; i<board.size; i++){
            for(int j =0; j<board.size; j++){
                if(board.getSymbol(i, j)==Symbols.EMPTY) return false;
            }
        }
        return isTie;
    }
    public Player checkWinner(Player player, int row, int col){
       boolean flag = true;
       for(int i =0; i<board.size; i++){
           if(board.getSymbol(row,i)!=player.mySymbol){
               flag = false;
               break;
           }
       }
       if(flag) return player;

       flag = true;
       for(int i =0; i<board.size; i++){
           if(board.getSymbol(i,col)!=player.mySymbol){
               flag = false;
               break;
           }
       }

        if(flag) return player;

        flag = true;
       for(int i=0;i<board.size; i++){
           if(board.getSymbol(i,i)!=player.mySymbol){
               flag = false;
               break;
           }

       }
        if(flag) return player;
        flag = true;
        int i =0;
        int j = board.size-1;

        while(i<j){
            if(board.getSymbol(i,j)!=player.mySymbol  || board.getSymbol(j,i)!=player.mySymbol ){
                flag = false;
                break;
            }
            i++;
            j--;
        }
        if(flag) return player;
        return null;

    }

    public void printBoard(){

        int n = this.board.size;
        int row =0;
        int col =0;
        for(int i =0; i<n+2; i++){
            col =0;
            if(i%2==1){
                for(int j =0; j<n+1; j++){
                    System.out.print(" - " );
                }
                System.out.println();

                continue;
            }
            for(int j =0; j<n+2; j++){
                if(j%2==0){
                    if(board.getSymbol(row, col)==Symbols.EMPTY){
                        System.out.print("  ");
                    }else{
                        System.out.print(board.getSymbol(row, col));
                    }

                    col++;
                }else{
                    System.out.print(" | ");
                }
            }
            System.out.println();

            row++;
        }

    }
}

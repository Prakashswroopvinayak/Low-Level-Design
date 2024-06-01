package LLD.Low.Level.Design.SnakeAndLadder;

import java.util.Deque;
import java.util.LinkedList;

public class SnakeAndLadderController {

    Board board;
    Deque<Player> players = new LinkedList<>();
    Player winner =null;
    Dice dice ;
    SnakeAndLadderController(){
        initializer();

    }

    public static void main(String[] args) {
        SnakeAndLadderController snakeAndLadderController = new SnakeAndLadderController();
        snakeAndLadderController.startGame();
    }
    public void startGame(){

        while (winner==null){

            Player playerTurn = players.removeFirst();
            System.out.println("Current position of "+playerTurn.name + " "+ playerTurn.pos);
            int num =dice.rollDice();
            System.out.println("Dice value "+num);
            if(num+playerTurn.getPos()>= board.size* board.size){
                winner=playerTurn;
                break;
            }
            int pos = num+playerTurn.getPos();
            playerTurn.setPos(pos);
            Cell cell = board.getCell(pos);
            if(cell.jump!=null){
                if(cell.jump.jumpType==JumpType.SNAKE){
                    System.out.println("Snake bite at "+ pos);
                }else{
                    System.out.println("Ladder at "+ pos);
                }
                playerTurn.setPos(cell.jump.end);
            }
            System.out.println("***********************************");
            players.add(playerTurn);

        }

        System.out.println("Winner is "+ winner.name);
    }
    public void initializer(){
        board = new Board(10,5,5);
        this.dice = new Dice(1,1,6);
        addPlayer();

    }

    public void addPlayer(){
        Player player1 = new Player("Prakash",0);
        Player player2 = new Player("Vinayak",0);
        players.add(player1);
        players.add(player2);
    }
}

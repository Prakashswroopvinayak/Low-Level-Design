package LLD.Low.Level.Design.SnakeAndLadder;

import java.util.concurrent.ThreadLocalRandom;

public class Dice {

    int n;
    int min =1;
    int max = 6;
    Dice(int n){
        this.n = n;
    }
    Dice(int n, int min, int max){
        this.n = n;
        this.min = min;
        this.max = max;;
    }

    public int rollDice(){
        int num = 0;
        for(int i =0; i<n; i++){
            num += ThreadLocalRandom.current().nextInt(min, max);
        }
        return num;

    }
}

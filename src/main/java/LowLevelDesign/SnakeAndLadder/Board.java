package LowLevelDesign.SnakeAndLadder;

import java.util.concurrent.ThreadLocalRandom;

public class Board {

    Cell[][] cells;
    int size;
    Board(int size, int noOfSnakes, int noOfLadder){
        this.size = size;

        creatBoard(size);
        addSnakesAndLadders(noOfSnakes, noOfLadder);
    }
    public void creatBoard(int size){
        cells = new Cell[size][size];

        for(int i =0; i<size; i++){
            for(int j =0; j<size; j++){
                cells[i][j] = new Cell();
            }
        }
    }


    public void addSnakesAndLadders(int noOfSnakes, int noOfLadder ){

        while (noOfSnakes>0){

            int start = ThreadLocalRandom.current().nextInt(0, size*size-1);
            int end = ThreadLocalRandom.current().nextInt(0, size*size-1);

            if(start<=end){
                continue;
            }


            Cell cell = new Cell(new Jump(start, end,JumpType.SNAKE));

            cells[start/size][start%size]=cell;

            noOfSnakes--;

        }

        while (noOfLadder>0){

            int start = ThreadLocalRandom.current().nextInt(0, size*size-1);
            int end = ThreadLocalRandom.current().nextInt(0, size*size-1);

            if(start>=end){
                continue;
            }


            Cell cell = new Cell(new Jump(start, end,JumpType.LADDER));

            cells[start/size][start%size]=cell;

            noOfLadder--;

        }
    }
    public Cell getCell(int pos){
        int row = pos/size;
        int col = pos%size;
        return this.cells[row][col];
    }

    public void setCells(int pos, Cell cell){
        int row = pos/size;
        int col = pos%size;
        this.cells[row][col] = cell;
    }
}

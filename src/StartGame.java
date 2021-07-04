import java.util.*;
import java.io.*;
public class StartGame {
    private int[][] grid = new int[4][4];
    Random random = new Random();
    private int score=0;
    private boolean running = false;
    private String move;
    public StartGame(){
        System.out.println("You can make your move by clicking");
        System.out.println("W: UP");
        System.out.println("A: LEFT");
        System.out.println("S: DOWN");
        System.out.println("D: RIGHT");
        running = true;
        for(int i=0; i<4; i++) {
            for (int j = 0; j < 4; j++) {
                grid[i][j] = 0;
            }
        }
        //ToDo initialize as per needed
    }

    public void printState(){
        for(int i=0; i<4; i++) {
            for (int j = 0; j < 4; j++) {
                if(grid[i][j]!=0){
                    System.out.print(grid[i][j] + " ");
                }else{
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }

    public void generateRandom(){
        int i, j;
        i = random.nextInt(4);
        j = random.nextInt(4);
        if(grid[i][j] != 0){
            generateRandom();
        }else{
            grid[i][j] = 2;
        }
    }

    public void takeInput(){
        Scanner temp = new Scanner(System.in);
        System.out.print("Enter your move: ");
        move = temp.nextLine();
    }

    public void processInput(){
        switch (move){
            //up
            case "W":
                up();
                break;
            //down
            case "S":
                down();
                break;
            //left
            case "A":
                left();
                break;
            //right
            case "D":
                right();
                break;
        }
    }

    public void mearging1DArray(int[] oneDArray){
        for(int k=0; k<3; k++){
            for(int i=0; i<3; i++){
                if(oneDArray[i]==0){
                    oneDArray[i] = oneDArray[i+1];
                    oneDArray[i+1] = 0;
                }
            }
        }

        for(int i=0; i<3; i++){
                if(oneDArray[i]==oneDArray[i+1]){
                    oneDArray[i] = 2*oneDArray[i+1];
                    oneDArray[i+1] = 0;
                }
            }
        for(int i=0; i<3; i++){
            if(oneDArray[i]==0){
                oneDArray[i] = oneDArray[i+1];
                oneDArray[i+1] = 0;
            }
        }
    }

    public void up(){
        for(int j=0; j<4; j++){
            int handelingSingleArray[] = {0,0,0,0};

            for(int i=0; i<4; i++){
                handelingSingleArray[i] = grid[i][j];
            }
            mearging1DArray(handelingSingleArray);
            for(int i=0; i<4; i++){
                 grid[i][j] = handelingSingleArray[i];
            }
        }
    }

    public void down(){
        for(int j=0; j<4; j++){
            int handelingSingleArray[] = {0,0,0,0};
            for(int i=0; i<4; i++){
                handelingSingleArray[3-i] = grid[i][j];
            }
            mearging1DArray(handelingSingleArray);
            for(int i=0; i<4; i++){
                grid[i][j] = handelingSingleArray[3-i];
            }
        }
    }

    public void left(){
        for(int i=0; i<4; i++){
            int handelingSingleArray[] = {0,0,0,0};

            for(int j=0; j<4; j++){
                handelingSingleArray[j] = grid[i][j];
            }
            mearging1DArray(handelingSingleArray);
            for(int j=0; j<4; j++){
                grid[i][j] = handelingSingleArray[j];
            }
        }
    }

    public void right(){
        for(int i=0; i<4; i++){
            int handelingSingleArray[] = {0,0,0,0};
            for(int j=0; j<4; j++){
                handelingSingleArray[3-j] = grid[i][j];
            }
            mearging1DArray(handelingSingleArray);
            for(int j=0; j<4; j++){
                grid[i][j] = handelingSingleArray[3-j];
            }
        }
    }


    public void checkState(){
        int ans = 2048;
        int countOfNonzeors = 0;
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                if(grid[i][j] != 0){
                    countOfNonzeors++;
                }
                if(grid[i][j]>=ans){
                    won();
                }
            }
        }
        if(countOfNonzeors==16){
            loss();
        }
    }

    public void won(){
        System.out.println("You Won!!!!!!!!");
    }

    public void loss(){
        System.out.println("You Loss!!!!!!!!");
    }

    public void playgame(){
        while(running){
            generateRandom();
            printState();
            takeInput();
            processInput();
            checkState();
        }
    }
}

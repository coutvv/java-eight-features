package ru.coutvv.concurrency.fundamental;

import java.io.IOException;
import java.util.Random;

/**
 * @author coutvv
 */
public class GameLife {

    boolean[][] board;

    public void createBoard(int dimension, int maxLive) {
        board = new boolean[dimension][dimension];
        Random rand = new Random();
        int x = 0,
            y = 0;
        for(int i = 0; i < maxLive; i++) {
            while(board[x][y] || x==0 || y==0 || x==dimension-1 || y==dimension-1) {
                x = Math.abs(rand.nextInt()%dimension);
                y = Math.abs(rand.nextInt()%dimension);
            }
            board[x][y] = true;
        }
    }

    public void showBoard() {
        System.out.println();
        System.out.print("|");
        for(int i =0 ; i< board.length;i++) System.out.print("-");
        System.out.println("|");

        for(int i = 0; i < board.length; i++) {
            System.out.print("|");
            for(int j =0; j< board.length; j++) {
                if(board[i][j])
                    System.out.print("+");
                else
                    System.out.print(".");
            }
            System.out.println("|");
        }
        System.out.print("|");
        for(int i =0 ; i< board.length;i++) System.out.print("-");
        System.out.println("|");
    }

    public void step() {
        int max = board.length;
        boolean[][] thisStep = new boolean[max][max];
        for(int i = 1; i < max-1; i++) {
            for(int j = 1; j< max-1;j++) {
                int count = countLiveAroundCell(i,j);
                if(board[i][j]){
                    thisStep[i][j] = count == 2 || count == 3;
                    if(!thisStep[i][j])System.out.print("клетка {" + i + "," + j + "} сдохла нахрен count=" + count + ";\t ");

                } else {
                    thisStep[i][j] = count == 3;
                    if(thisStep[i][j])System.out.print("клетка {" + i + "," + j + "} родилась; ");
                }
            }
        }
        board = thisStep;
    }

    public int countLiveAroundCell(int x, int y) {
        int count = 0;
        for(int i = -1; i<2; i++) {
            for(int j = -1; j < 2; j++) {
                if(i!=0 || j!=0) {
                    if(board[x+i][y+j])
                        count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        GameLife gl = new GameLife();
        gl.createBoard(7, 10);
        gl.showBoard();
        System.out.println(gl.countLiveAroundCell(2,2));
        while(true) {
            System.in.read();
            gl.step();
            gl.showBoard();
        }


    }

}

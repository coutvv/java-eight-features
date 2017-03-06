package ru.coutvv.concurrency.fundamental.buildingblocks;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Что-то вроде игры  Conway's Life game
 */
public class CellularAutomata {

    private final Board mainBoard;
    private final CyclicBarrier barrier;
    private final Worker[] workers;

    public CellularAutomata(Board board) {
        this.mainBoard = board;
        int count = Runtime.getRuntime().availableProcessors();
        this.barrier = new CyclicBarrier(count, () -> {
           mainBoard.commitNewValues();
        });
        this.workers = new Worker[count];
        for(int i = 0; i < count; i++) {
            workers[i] = new Worker(mainBoard.getSubBoard(count, i));
        }
    }

    private class Worker implements Runnable {
        private final Board board;
        Worker(Board board) {
            this.board = board;
        }
        @Override
        public void run() {
            while(!board.hasConverged()) {
                //some change board
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public void start() {
        for(int i = 0; i< workers.length; i++) {
            new Thread(workers[i]).start();
        }
        mainBoard.waitForConvergence();
    }

    public static void main(String[] args) {
        System.out.println( Runtime.getRuntime().availableProcessors());
    }
}
class Board {
    void commitNewValues() {

    }
    Board getSubBoard(int count, int i) {
        return new Board();
    }
    boolean hasConverged() {
        return new Random().nextBoolean();
    }

    int getMaxX() {return 1;}
    int getMaxY() {return 1;}

    void waitForConvergence(){}
};

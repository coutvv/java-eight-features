package ru.coutvv.concurrency.sturcturing.taskexec;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author coutvv
 */
public class TaskExecutionWebServer {

    private static final int NTHREADS = 100;
    private static final Executor exec = Executors.newFixedThreadPool(NTHREADS);

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while(true) {
            final Socket connection = socket.accept();
            exec.execute(() -> {
                try {
                    connection.getOutputStream().write("fuck off".getBytes());
                    connection.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(connection.getInetAddress());
            });
        }
    }
}

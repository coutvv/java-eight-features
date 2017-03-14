package ru.coutvv.concurrency.sturcturing.taskexec;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

/**
 * @author coutvv
 */
public class LifecycleWebServer {

    private static final int NTHREADS = 100;
    private final ExecutorService exec = Executors.newFixedThreadPool(NTHREADS);

    public void start() throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while(!exec.isShutdown()) {
            try {
                final Socket conn = socket.accept();
                exec.execute(() -> {
                    handleRequest(conn);
                });
            } catch (RejectedExecutionException e) {
                if(!exec.isShutdown())
                    System.out.println("task submission reejcted " + e);
            }
        }
    }

    void handleRequest(Socket connection) {
        try {
            byte[] buffer = new byte[255];
            int size= connection.getInputStream().read(buffer);
            byte[] req = Arrays.copyOf(buffer, size);
            System.out.println(new String(req));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            connection.getOutputStream().write("fuck off".getBytes());
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

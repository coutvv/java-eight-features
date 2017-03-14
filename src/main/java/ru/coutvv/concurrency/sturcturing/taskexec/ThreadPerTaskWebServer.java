package ru.coutvv.concurrency.sturcturing.taskexec;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Простенький веб-сервер. Создаёт на каждое соединение свой тред.
 *
 * Недостатки подхода:
 * - Создание и уничтожение тредов не бесплатно
 * - Потребление ресурсов. Треды жрут память. А когда их очень дофига
 * и они простаивают(мало ядер на проце), то пипец
 * - Стабильность. Всегда есть предел. В нашем случае сколько
 * потоков может быть создано. Как только достигнем этого значения
 * получим OutOfMemoryError.
 *
 * @author coutvv
 */
public class ThreadPerTaskWebServer {

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while(true) {
            final Socket connection = socket.accept();

            new Thread(() -> {
                    System.out.println(connection.getInetAddress());
                try {
                    connection.getOutputStream().write("fuck you asshole".getBytes());
                    connection.close();
                    System.out.println("connection closed");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).run();
        }
    }
}

import Server.Socket.SocketConnection;

import java.net.Socket;

/**
 * Created by Tommaso Garuglieri on 19/08/2016.
 */
public class EasySocketExample {

    public static void main(String... args) {
        Socket socket = new Socket();

        SocketConnection connection = new SocketConnection(socket);
        connection.send("Hi");
        connection.readOn(System.out::println);


    }
}

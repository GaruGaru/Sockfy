import Server.Server.Sockfy;

/**
 * Created by Tommaso Garuglieri on 19/08/2016.
 */
public class FilterExample {
    public static void main(String... args) {
        Sockfy.create()
                .filter(socket -> socket.getInetAddress().isLoopbackAddress()) // Localhost only connections
                .answer(message -> message)
                .onPort(7777)
                .run();
    }
}

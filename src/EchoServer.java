import Server.Server.Sockfy;

/**
 * Created by Tommaso Garuglieri on 19/08/2016.
 */
public class EchoServer {

    public static void main(String... args) {
        Sockfy.create()
                .answer(message -> "got " + message)
                .onPort(7777)
                .run();
    }
}

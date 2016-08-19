import Server.Server.Sockfy;

import java.io.IOException;

/**
 * Created by Tommaso Garuglieri on 19/08/2016.
 */
public class ProcessExample {

    public static void main(String... args) {
        Sockfy.create()
                .execute((client, message) -> run(message))
                .answer(message -> "Executed " + message)
                .onPort(7777)
                .run();
    }

    private static void run(String command) {
        try {
            Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

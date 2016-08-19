import Server.Logging.Loggy;
import Server.Server.Sockfy;

import java.util.logging.Logger;

public class Main {
    private static final Logger log = Loggy.get(Main.class);

    public static void main(String[] args) {
        Sockfy.create()
                .filter(s -> s.getInetAddress().isLoopbackAddress()) // Localhost only
                .execute((h, r) -> log.info(h + ": " + r))
                .answer(message -> message)
                .onPort(7777)
                .run();
    }

}

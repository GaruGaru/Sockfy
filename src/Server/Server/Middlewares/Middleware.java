package Server.Server.Middlewares;

import java.net.Socket;

/**
 * Created by Tommaso Garuglieri on 19/08/2016.
 */

@FunctionalInterface
public interface Middleware {
    Middleware ONLY_LOCALHOST = socket -> socket.getInetAddress().isLoopbackAddress();
    boolean accept(Socket socket);
}

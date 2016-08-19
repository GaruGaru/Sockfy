package Server.Client.Adapters;

import Server.Client.Handling.AsyncClientHandler;
import Server.Client.Handling.IClientHandler;
import Server.Socket.SocketConnection;

import java.net.Socket;
import java.util.function.Function;

/**
 * Created by Tommaso Garuglieri on 19/08/2016.
 */

public class SocketClientAdapter implements Function<Socket, IClientHandler> {

    private IClientHandler getSocketConnection(Socket source) {
        SocketConnection connection = new SocketConnection(source);
        return new AsyncClientHandler(connection);
    }

    @Override
    public IClientHandler apply(Socket socket) {
        return getSocketConnection(socket);
    }
}

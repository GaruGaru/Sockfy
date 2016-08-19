package Server.Client.Handling;

import Server.Client.Managing.Interfaces.ClientListener;
import Server.Socket.SocketConnection;

/**
 * Created by Tommaso Garuglieri on 18/08/2016.
 */
public interface IClientHandler {

    void start();

    void close();

    void setListener(ClientListener listener);

    SocketConnection getConnection();

}

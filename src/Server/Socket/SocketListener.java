package Server.Socket;

/**
 * Created by Tommaso Garuglieri on 13/08/2016.
 */
public interface SocketListener {

    void onConnectionEstablished(SocketConnection connection);

    void onDisconnected(SocketConnection connection);

}

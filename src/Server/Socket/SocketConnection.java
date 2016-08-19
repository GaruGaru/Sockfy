package Server.Socket;

import java.net.Socket;

/**
 * Created by Tommaso Garuglieri on 13/08/2016.
 */
public class SocketConnection extends EasySocket {


    public SocketConnection(Socket socket) {
        this(socket, null);
    }

    public SocketConnection(Socket socket, SocketListener listener) {
        super(socket);
    }

    @Override
    void onDisconnected() {
    }

    @Override
    void onConnected() {
    }

    @Override
    public String toString() {
        return getSocket().getInetAddress().toString() + ":" + getSocket().getPort() + " -> " + getSocket().getLocalPort();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        if (!(obj instanceof SocketConnection))
            return false;

        SocketConnection comparable = (SocketConnection) obj;
        return comparable.getSocket().equals(getSocket());
    }
}

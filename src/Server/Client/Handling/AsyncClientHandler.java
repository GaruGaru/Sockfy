package Server.Client.Handling;

import Server.Client.Managing.Interfaces.ClientListener;
import Server.Logging.Loggy;
import Server.Socket.SocketConnection;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Tommaso Garuglieri on 18/08/2016.
 */
public class AsyncClientHandler extends Thread implements IClientHandler {

    private static final Logger logger = Loggy.get(IClientHandler.class);
    private SocketConnection socketConnection;
    private List<ClientListener> listenerList;

    public AsyncClientHandler(SocketConnection socketConnection, ClientListener... clientListeners) {
        this.socketConnection = socketConnection;
        this.listenerList = new LinkedList<>();
        Collections.addAll(listenerList, clientListeners);
    }

    private List<ClientListener> getListeners() {
        return listenerList;
    }

    @Override
    public void run() {
        getListeners().forEach(l -> l.onConnected(this));

        socketConnection.readOn
                (s -> getListeners().forEach(
                        l -> l.onMessage(this, s)
                ));

        getListeners().forEach(l -> l.onDisconnected(this));

    }

    public SocketConnection getConnection() {
        return socketConnection;
    }

    @Override
    public synchronized void start() {
        super.start();
    }

    @Override
    public void close() {
        socketConnection.close();
        interrupt();
    }

    @Override
    public void setListener(ClientListener listener) {
        this.listenerList.clear();
        this.listenerList.add(listener);
    }
}

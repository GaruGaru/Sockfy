package Server.Server;

import Server.Logging.Loggy;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * Created by Tommaso Garuglieri on 13/08/2016.
 */
public abstract class Server {

    protected static final Logger LOG = Loggy.get(Sockfy.class);

    private ServerSocket serverSocket;

    private boolean listening = false;

    private int port;

    protected Server(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void startListening() {

        open();

        setListening(true);
        while (isListening()) {
            try {
                onNewClient(serverSocket.accept());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void open() {
        try {
            this.serverSocket = new ServerSocket(getPort());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected abstract void onNewClient(Socket accept);

    abstract void onListeningChanged(boolean status);

    public boolean isListening() {
        return listening;
    }

    private void setListening(boolean listening) {
        this.listening = listening;
        this.onListeningChanged(listening);
    }

    public void stopListening() {
        setListening(false);
    }
}

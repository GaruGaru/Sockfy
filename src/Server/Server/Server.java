package Server.Server;

import Server.Logging.Loggy;
import Server.Server.Handlers.ExceptionHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * Created by Tommaso Garuglieri on 13/08/2016.
 */
public abstract class Server {

    protected static final Logger LOG = Loggy.get(Sockfy.class);

    private ExceptionHandler exceptionHandler = ExceptionHandler.PRINT;

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
                getExceptionHandler().onException(this, e);
            }
        }
    }

    private void open() {
        try {
            this.serverSocket = new ServerSocket(getPort());
        } catch (IOException e) {
            getExceptionHandler().onException(this, e);
        }
    }

    public ExceptionHandler getExceptionHandler() {
        return exceptionHandler;
    }

    public void setExceptionHandler(ExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
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

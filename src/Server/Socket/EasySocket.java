package Server.Socket;

import Server.Socket.Stream.Exceptions.IOStreamProvider;
import Server.Socket.Stream.IO;

import java.io.IOException;
import java.net.Socket;
import java.util.function.Consumer;

/**
 * Created by Tommaso Garuglieri on 13/08/2016.
 */
public abstract class EasySocket {

    private final Socket socket;
    private final IO<String> ioStream;

    public EasySocket(Socket socket) {
        if (socket == null)
            throw new NullPointerException("Socket can't be null");
        this.socket = socket;
        this.ioStream = IOStreamProvider.createFrom(socket);
        this.onConnected();
    }

    public Socket getSocket() {
        return socket;
    }

    private IO<String> getStream() {
        return ioStream;
    }

    public void send(String message) {
        getStream().send(message);
    }

    public String read() {
        String out = getStream().read();
        if (out == null)
            onDisconnected();
        return out;
    }

    public void readOn(Consumer<String> consumer) {
        String out;
        while (socket.isConnected() && (out = getStream().read()) != null) {
            consumer.accept(out);
        }
        close();
    }

    public void readOnAsync(Consumer<String> consumer) {
        new Thread(() -> {
            readOn(consumer);
        }).start();
    }

    public boolean isConnected() {
        return socket.isConnected();
    }

    public void close() {
        if (!socket.isClosed())
            try {
                getStream().close();
                socket.close();
            } catch (IOException ignored) {
            }
    }

    abstract void onDisconnected();

    abstract void onConnected();


}

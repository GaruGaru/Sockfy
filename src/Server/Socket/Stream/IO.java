package Server.Socket.Stream;

/**
 * Created by Tommaso Garuglieri on 13/08/2016.
 */
public interface IO<T> {
    void send(T message);
    T read();
    void close();
}

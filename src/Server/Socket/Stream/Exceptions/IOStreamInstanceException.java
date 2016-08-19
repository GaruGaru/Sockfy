package Server.Socket.Stream.Exceptions;

/**
 * Created by Tommaso Garuglieri on 13/08/2016.
 */
public class IOStreamInstanceException extends RuntimeException {

    public IOStreamInstanceException(String message) {
        super("Unable to create IOStream: " + message);
    }

}

package Server.Socket.Stream.Exceptions;

import Server.Socket.Stream.IOStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by Tommaso Garuglieri on 13/08/2016.
 */
public class IOStreamProvider {

    public static IOStream createFrom(InputStream inputStream, OutputStream outputStream) {
        return new IOStream(inputStream, outputStream);
    }

    public static IOStream createFrom(Socket socket) {
        try {
            return createFrom(socket.getInputStream(), socket.getOutputStream());
        } catch (IOException e) {
            throw new IOStreamInstanceException("Error getting IO stream from socket");
        }
    }
}

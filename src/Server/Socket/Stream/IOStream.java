package Server.Socket.Stream;

import java.io.*;

/**
 * Created by Tommaso Garuglieri on 13/08/2016.
 */
public class IOStream implements IO<String> {

    private final InputStream inputStream;
    private final OutputStream outputStream;

    private PrintWriter printWriter;
    private BufferedReader bufferedReader;

    public IOStream(InputStream inputStream, OutputStream outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    private PrintWriter getPrintWriter() {
        if (printWriter == null)
            this.printWriter = new PrintWriter(outputStream);
        return this.printWriter;
    }


    private BufferedReader getBufferedReader() {
        if (bufferedReader == null)
            this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        return this.bufferedReader;
    }

    @Override
    public void close() {
        try {
            getBufferedReader().close();
            getPrintWriter().close();
        } catch (Exception ignored) {
        }
    }

    @Override
    public void send(String data) {
        getPrintWriter().println(data);
        getPrintWriter().flush();
    }

    @Override
    public String read() {
        try {
            return getBufferedReader().readLine();
        } catch (IOException e) {
            return null;
        }
    }


}

package Server.Client.Adapters;

import Server.Client.Handling.IClientHandler;
import Server.Client.Managing.Interfaces.ClientListener;

import java.net.Socket;

/**
 * Created by Tommaso Garuglieri on 19/08/2016.
 */
public class SocketClientListenerAdapter extends SocketClientAdapter {

    private final ClientListener listener;

    public SocketClientListenerAdapter(ClientListener listener) {
        this.listener = listener;
    }

    @Override
    public IClientHandler apply(Socket source) {
        IClientHandler handler = super.apply(source);
        handler.setListener(listener);
        return handler;
    }
}

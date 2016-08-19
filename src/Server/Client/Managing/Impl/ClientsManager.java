package Server.Client.Managing.Impl;

import Server.Client.Adapters.SocketClientListenerAdapter;
import Server.Client.Handling.Answerer;
import Server.Client.Handling.IClientHandler;
import Server.Client.Managing.Interfaces.ClientManagerAbstract;
import Server.Client.Managing.Interfaces.IClientsManager;

import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

/**
 * Created by Tommaso Garuglieri on 19/08/2016.
 */
public class ClientsManager extends ClientManagerAbstract {

    private Function<Socket, IClientHandler> socketAdapter;
    private List<IClientHandler> clients;

    public ClientsManager(Answerer answerer) {
        super(answerer);
        this.clients = new LinkedList<>();
        this.socketAdapter = new SocketClientListenerAdapter(this);
    }

    public static IClientsManager create() {
        Answerer answerer = Answerer.EMPTY;
        return new ClientsManager(answerer);
    }

    @Override
    public void clear() {
        clients.forEach(this::onDisconnected);
        clients.clear();
    }

    @Override
    public void handle(Socket socket) {
        IClientHandler client = socketAdapter.apply(socket);
        client.start();
    }

    @Override
    public void onMessage(IClientHandler client, String message) {
        this.execute(client, message);
        String answer = getAnswerer().answer(message);
        if (answer != null)
            client.getConnection().send(answer);
    }

    @Override
    public void onDisconnected(IClientHandler client) {
        Log.info("Disconnected " + client);
        client.close();
        clients.remove(client);
    }

    @Override
    public void onConnected(IClientHandler client) {
        this.clients.add(client);
        Log.info("Connected " + client);
    }

    public List<IClientHandler> getClients() {
        return clients;
    }
}

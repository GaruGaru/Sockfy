package Server.Server;

import Server.Client.Managing.Interfaces.IClientsManager;
import Server.Server.Middlewares.Middleware;

import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Tommaso Garuglieri on 13/08/2016.
 */

public class ServerImpl extends Server {

    private IClientsManager clientsManager;
    private List<Middleware> middlewareList;

    public ServerImpl(int port) {
        super(port);
        this.middlewareList = new LinkedList<>();
    }

    public void addMiddleware(Middleware middleware) {
        this.middlewareList.add(middleware);
    }

    public List<Middleware> getMiddlewareList() {
        return middlewareList;
    }

    @Override
    protected void onNewClient(Socket clientsSocket) {
        if (middlewareList.stream().allMatch(middleware -> middleware.accept(clientsSocket)))
            this.clientsManager.handle(clientsSocket);
        else
            LOG.severe("Blocked connection from " + clientsSocket);
    }

    public IClientsManager getClientsManager() {
        return clientsManager;
    }

    public void setClientsManager(IClientsManager clientsManager) {
        this.clientsManager = clientsManager;
    }

    @Override
    void onListeningChanged(boolean status) {
        if (status)
            LOG.info("Server listening on port: " + getPort());
        else
            LOG.info("Server stopped");
    }

    @Override
    public void startListening() {
        this.clientsManager.clear();
        super.startListening();
    }
}

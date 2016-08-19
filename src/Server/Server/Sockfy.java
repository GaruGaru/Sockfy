package Server.Server;

import Server.Client.Handling.Answerer;
import Server.Client.Managing.Impl.ClientsManager;
import Server.Client.Managing.Interfaces.IClientsManager;
import Server.Logging.Loggy;
import Server.Server.Handlers.ExceptionHandler;
import Server.Server.Middlewares.Middleware;
import Server.Server.Middlewares.RequestExecutor;
import Server.Server.Middlewares.RequestProcessor;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Tommaso Garuglieri on 19/08/2016.
 */
public class Sockfy extends ServerImpl {

    private static final Logger LOG = Loggy.get(Sockfy.class);

    protected Sockfy() {
        super(0);
    }


    protected Sockfy(int port) {
        super(port);
    }

    public static Sockfy create() {
        Sockfy socky = new Sockfy();
        socky.manageWith(ClientsManager.create());
        socky.answer(message -> message);
        return socky;
    }

    public Sockfy filter(Middleware middleware) {
        addMiddleware(middleware);
        return this;
    }

    public Sockfy execute(RequestExecutor processor) {
        getClientsManager().executeWith(processor);
        return this;
    }

    public Sockfy process(RequestProcessor processor) {
        getClientsManager().processWith(processor);
        return this;
    }

    public Sockfy manageWith(IClientsManager manager) {
        setClientsManager(manager);
        return this;
    }

    public Sockfy onPort(int port) {
        setPort(port);
        return this;
    }

    public Sockfy onError(ExceptionHandler exceptionHandler) {
        setExceptionHandler(exceptionHandler);
        return this;
    }

    public Sockfy run() {
        if (getPort() <= 0)
            throw new RuntimeException("Invalid port " + getPort());
        LOG.log(Level.INFO, "Server running on " + getPort());
        startListening();
        return this;
    }

    public Sockfy runAsync() {
        new Thread(this::run).start();
        return this;
    }

    public Sockfy answer(Answerer answerer) {
        getClientsManager().answerWith(answerer);
        return this;
    }

}

package Server.Server.Handlers;

import Server.Server.Server;

/**
 * Created by Tommaso Garuglieri on 19/08/2016.
 */

@FunctionalInterface
public interface ExceptionHandler {
    ExceptionHandler PRINT = (server, throwable) -> throwable.printStackTrace();
    ExceptionHandler RETRY = ((server, throwable) -> server.startListening());
    ExceptionHandler INTERRUPT = ((server, throwable) -> System.exit(0));

    void onException(Server server, Throwable throwable);
}

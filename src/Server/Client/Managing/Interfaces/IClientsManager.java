package Server.Client.Managing.Interfaces;

import Server.Client.Handling.Answerer;
import Server.Logging.Loggy;
import Server.Server.Middlewares.RequestExecutor;
import Server.Server.Middlewares.RequestProcessor;

import java.net.Socket;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Tommaso Garuglieri on 19/08/2016.
 */

public interface IClientsManager {
    Logger Log = Loggy.get(IClientsManager.class);

    void handle(Socket socket);

    IClientsManager answerWith(Answerer answerer);

    Answerer getAnswerer();

    IClientsManager executeWith(RequestExecutor processor);

    IClientsManager processWith(RequestProcessor processor);

    List<RequestExecutor> getExecutors();

    List<RequestExecutor> getProcessors();

}

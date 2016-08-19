package Server.Server.Middlewares;

import Server.Client.Handling.IClientHandler;

/**
 * Created by Tommaso Garuglieri on 19/08/2016.
 */

@FunctionalInterface
public interface RequestExecutor {
    void execute(IClientHandler client, String message);
}

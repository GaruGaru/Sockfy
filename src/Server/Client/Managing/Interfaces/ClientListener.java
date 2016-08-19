package Server.Client.Managing.Interfaces;

import Server.Client.Handling.IClientHandler;

/**
 * Created by Tommaso Garuglieri on 13/08/2016.
 */
public interface ClientListener {

    void onMessage(IClientHandler client, String message);

    void onDisconnected(IClientHandler client);

    void onConnected(IClientHandler client);

}

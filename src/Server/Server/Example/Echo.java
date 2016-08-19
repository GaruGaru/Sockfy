package Server.Server.Example;

import Server.Client.Handling.Answerer;
import Server.Server.Sockfy;

/**
 * Created by Tommaso Garuglieri on 19/08/2016.
 */
public class Echo extends Sockfy {
    public Echo(int port) {
        super(port);
        answer(Answerer.ECHO);
    }
}

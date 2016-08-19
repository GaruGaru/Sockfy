package Server.Logging;

import java.util.logging.Logger;

/**
 * Created by Tommaso Garuglieri on 19/08/2016.
 */
public class Loggy {

    public static Logger get(Class lClass) {
        return get(lClass.getName());
    }

    public static Logger get(String name) {
        return Logger.getLogger(name);
    }

}

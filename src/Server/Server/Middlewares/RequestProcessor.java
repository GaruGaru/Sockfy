package Server.Server.Middlewares;

/**
 * Created by Tommaso Garuglieri on 19/08/2016.
 */

@FunctionalInterface
public interface RequestProcessor {
    String process(String message);
}

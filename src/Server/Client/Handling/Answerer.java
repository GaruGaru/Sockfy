package Server.Client.Handling;

/**
 * Created by Tommaso Garuglieri on 19/08/2016.
 */

@FunctionalInterface
public interface Answerer {

    Answerer EMPTY = message -> null;
    Answerer ECHO = message -> message;

    String answer(String message);

}

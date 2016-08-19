package Server.Client.Managing.Interfaces;

import Server.Client.Handling.Answerer;
import Server.Client.Handling.IClientHandler;
import Server.Server.Middlewares.RequestExecutor;
import Server.Server.Middlewares.RequestProcessor;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Tommaso Garuglieri on 19/08/2016.
 */
public abstract class ClientManagerAbstract implements IClientsManager, ClientListener {

    private Answerer answerer;

    private List<RequestExecutor> executorList;
    private List<RequestProcessor> processorList;

    public ClientManagerAbstract(Answerer answerer) {
        this.answerer = answerer;
        this.executorList = new LinkedList<>();
        this.processorList = new LinkedList<>();
    }

    protected void execute(IClientHandler handler, String message) {
        executorList.forEach(p -> p.execute(handler, message));
    }

    protected String process(String message) {
        for (RequestProcessor processor : processorList)
            message = processor.process(message);
        return message;
    }

    public List<RequestExecutor> getExecutors() {
        return executorList;
    }

    public IClientsManager processWith(RequestProcessor processor) {
        processorList.add(processor);
        return this;
    }

    @Override
    public List<RequestExecutor> getProcessors() {
        return executorList;
    }

    @Override
    public IClientsManager executeWith(RequestExecutor processor) {
        executorList.add(processor);
        return this;
    }

    public Answerer getAnswerer() {
        return answerer;
    }

    @Override
    public IClientsManager answerWith(Answerer answerer) {
        this.answerer = answerer;
        return this;
    }
}

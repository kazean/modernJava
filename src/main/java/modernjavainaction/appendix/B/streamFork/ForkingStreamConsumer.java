package modernjavainaction.appendix.B.streamFork;

import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.function.Consumer;

public class ForkingStreamConsumer<T> implements Results, Consumer<T> {
    static final Object END_OF_STREAM = new Object();

    private final List<BlockingQueue<T>> queues;
    private final Map<Object, Future<?>> actions;

    public ForkingStreamConsumer(List<BlockingQueue<T>> queues, Map<Object, Future<?>> actions) {
        this.queues = queues;
        this.actions = actions;
    }

    void finish(){
        accept((T) END_OF_STREAM);
    }

    @Override
    public void accept(T t) {
        queues.forEach(q->q.add(t));
    }

    @Override
    public <R> R get(Object key) {
        try{
            return ((Future<R>) actions.get(key)).get();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}

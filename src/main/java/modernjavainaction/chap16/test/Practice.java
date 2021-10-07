package modernjavainaction.chap16.test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Practice {
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(()->1);

        Executor executor = Executors.newFixedThreadPool(Math.min(2, 3), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setDaemon(true);
                return t;
            }
        });


    }
}

package modernjavainaction.chap15.test;

import java.util.concurrent.*;

import static modernjavainaction.chap15.Functions.f;
import static modernjavainaction.chap15.Functions.g;

public class Pratice {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        int x = 1337;
        CompletableFuture<Integer> a = new CompletableFuture<>();
        executorService.submit(()-> a.complete(f(x)));
        int b = g(x);
//        2674 + 1338 > 4012
         System.out.println(a.get() + b);
        executorService.shutdown();

        ExecutorService  executorService1 = Executors.newFixedThreadPool(10);
        CompletableFuture<Integer> b1 = new CompletableFuture<>();
        CompletableFuture<Integer> b2 = new CompletableFuture<>();
        CompletableFuture<Integer> b3 = b1.thenCombine(b2, (y,z)->y+z);
        executorService1.submit(()-> b1.complete(f(x)));
        executorService1.submit(()->b2.complete(g(x)));
        System.out.println("CFCombine result >> " + b3.get());
        executorService1.shutdown();

    }
}

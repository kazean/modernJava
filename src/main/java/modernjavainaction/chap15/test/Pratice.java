package modernjavainaction.chap15.test;

import java.util.concurrent.*;
import java.util.function.IntConsumer;

public class Pratice {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        pracCallback();
//        pracComepletableFuture();
        pracComepletableFutrueAdvanced();
    }

    public static void pracComepletableFutrueAdvanced() throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(3);
        int x = 1337;
        CompletableFuture<Integer> a = new CompletableFuture<>();
        CompletableFuture<Integer> b = new CompletableFuture<>();
        CompletableFuture<Integer> c = a.thenCombine(b, (y,z)-> y+z);
        service.submit(()->a.complete(f(x)));
        service.submit(()->b.complete(g(x)));
        System.out.println(c.get());
    }

    public static void pracComepletableFuture() throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(3);
        int x = 1337;
        CompletableFuture<Integer> a = new CompletableFuture<>();
        service.submit(()->a.complete(f(x)));
        System.out.println(a.get()+g(x));
    }

    public static void pracCallback(){
        int x = 1337;
        Result result = new Result();

        callBackF(x, (int y)->{
            result.left = y;
            System.out.println(result.left+result.right);
        });

        callBackG(x, (int y)->{
            result.right=y;
            System.out.println(result.left+result.right);
        });
    }

    private static class Result{
        int left;
        int right;
    }

    private static int f(int x){
        return x*2;
    }

    private static int g(int x){
        return x+1;
    }

    private static void callBackF(int x, IntConsumer consumer){
        consumer.accept(f(x));
    }

    private static void callBackG(int x, IntConsumer consumer){
        consumer.accept(g(x));
    }

}

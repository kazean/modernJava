package modernjavainaction.chap16.test;

import java.util.concurrent.CompletableFuture;

public class Practice {
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(()->1);
    }
}

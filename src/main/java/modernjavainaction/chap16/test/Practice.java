package modernjavainaction.chap16.test;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Practice {
    static List<Shop> shopList;
    static Executor executor;

    public static void main(String[] args) {
        /*List<String> item = new ArrayList<>();
        item.add("iph");
        item.add("gal");

        item.forEach(s-> {
            try {
                System.out.println(s + " : "+getPriceAsync(s).get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });*/

        shopList = Arrays.asList(new Shop("BestPrice")
            ,new Shop("LetsSaveBig")
            ,new Shop("MyFavoriteShop")
            ,new Shop("BuyItAll")
                ,new Shop("Temp5")
                ,new Shop("Temp6")
                ,new Shop("Temp7")
                ,new Shop("Temp8")
                ,new Shop("Temp9"));

//        Stream
        long start = System.nanoTime();
        String product = "phone";
        shopList.stream()
                .map(s->String.format("%s : %.2f", s.getName(),s.getPrice(product)))
                .collect(Collectors.toList());
//                .forEach(s-> System.out.println(s));
        System.out.println("Duration Stream : " + (System.nanoTime() - start) /1000000+" msec");
        System.out.println();

//        StreamParallel
        start = System.nanoTime();
        shopList.parallelStream()
                .map(s->String.format("%s : %.2f", s.getName(),s.getPrice(product)))
                .collect(Collectors.toList());
//                .forEach(s-> System.out.println(s));
        System.out.println("Duration Stream Parallel: " + (System.nanoTime() - start) /1000000+" msec");
        System.out.println();

//        CompletableFuture
        executor = Executors.newFixedThreadPool(Math.min(shopList.size(), 100), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setDaemon(true);
                return t;
            }
        });
        start = System.nanoTime();
        List<String> priceList = findPrice(product);
        System.out.println("Duration : " + (System.nanoTime() - start) /1000000+" msec");
    }

    public static List<String> findPrice(String product){
        List<CompletableFuture<String>> priceList = shopList.stream()
                .map(s-> CompletableFuture.supplyAsync(()->String.format("%s : %.2f", s.getName(), s.getPrice(product)),executor))
                .collect(Collectors.toList());
        return priceList.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    public static CompletableFuture<String> getPriceAsync(String name){
        return CompletableFuture.supplyAsync(()-> String.format("Price is %.2f",calculate(name)));
    }

    public static double calculate(String name){
        try {
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Random random = new Random();
        return random.nextDouble() * name.charAt(0) + name.charAt(1);
    }
}

class Shop{
    String name;

    public Shop(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getPrice(String product){
        return calculate(product);
    }

    public double calculate(String product){
        try{
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }
        Random random = new Random();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }
}

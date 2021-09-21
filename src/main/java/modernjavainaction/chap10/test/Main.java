package modernjavainaction.chap10.test;

import modernjavainaction.chap10.test.model.Order;
import modernjavainaction.chap10.test.model.Stock;
import modernjavainaction.chap10.test.model.Trade;

import java.util.function.*;

import static modernjavainaction.chap10.test.MethodChainingOrderBuilder.*;

public class Main {
    public static void main(String[] args) {
//        naturalJavaMethod();
        fluentDsl();

        /*
        Predicate<Integer> t;
        t.test();
        Consumer<Integer> c;
        c.accept();
        Function<Integer,Integer> f;
        f.apply();
        Supplier<Integer> s;
        s.get();
        UnaryOperator<Integer> uo;
        uo.apply();
        */
    }

    public static void naturalJavaMethod(){
        Order order = new Order();
        order.setCustomer("BigBank");

        Trade trade1 = new Trade();
        trade1.setType(Trade.Type.BUY);

        Stock stock1 = new Stock();
        stock1.setSymbol("IBM");
        stock1.setMarket("NYSE");

        trade1.setStock(stock1);
        trade1.setPrice(125.00);
        trade1.setQuantity(80);
        order.addTrades(trade1);

        Trade trade2 = new Trade();
        trade2.setType(Trade.Type.BUY);

        Stock stock2 = new Stock();
        stock2.setSymbol("GOOGLE");
        stock2.setMarket("NASDAQ");

        trade2.setStock(stock2);
        trade2.setPrice(375.00);
        trade2.setQuantity(50);
        order.addTrades(trade2);

        System.out.println("order customer >>" + order.getCustomer());
        System.out.println("order val >> " + order.getValue());

    }

    public static void fluentDsl(){
        Order order = forCustomer("BigBank")
                .buy(80)
                .stock("IBM")
                .on("NYSE")
                .at(125.00)
                .buy(50)
                .stock("GOOGLE")
                .on("NASDAQ")
                .at(375.00)
                .end();

        System.out.println("order customer >>" + order.getCustomer());
        System.out.println("order val >> " + order.getValue());
    }
}

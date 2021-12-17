package modernjavainaction.chap10.test2.dsl.nestedFunction;

import modernjavainaction.chap10.test2.dsl.model.Order;
import modernjavainaction.chap10.test2.dsl.model.Stock;
import modernjavainaction.chap10.test2.dsl.model.Trade;

import java.util.stream.Stream;

public class NetstedFunctionOrderBuilder {
    public static Order order(String customer, Trade... trades){
        Order order = new Order();
        order.setCustomer(customer);
        Stream.of(trades).forEach(order::addTrade);
        return order;
    }

    public static Trade buy(int quantity, Stock stock, double price){
        return buildTrade(quantity, stock, price, Trade.Type.BUY);
    }

    public static Trade sell(int quantity, Stock stock, double price){
        return buildTrade(quantity, stock, price, Trade.Type.SELL);
    }

    private static Trade buildTrade(int quantity, Stock stock, double price, Trade.Type type){
        Trade trade = new Trade();
        trade.setQuantity(quantity);
        trade.setType(type);
        trade.setStock(stock);
        trade.setPrice(price);
        return trade;
    }

    public static Stock stock(String symbol, String market){
        Stock stock = new Stock();
        stock.setSymbol(symbol);
        stock.setMarket(market);
        return stock;
    }

    public static String on(String market){
        return market;
    }

    public static double at(double price){
        return price;
    }
}

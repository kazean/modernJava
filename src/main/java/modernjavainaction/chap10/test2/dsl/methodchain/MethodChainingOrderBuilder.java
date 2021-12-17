package modernjavainaction.chap10.test2.dsl.methodchain;

import modernjavainaction.chap10.test2.dsl.model.Order;
import modernjavainaction.chap10.test2.dsl.model.Trade;

public class MethodChainingOrderBuilder {
    public final Order order = new Order();

    public MethodChainingOrderBuilder(String customer) {
        order.setCustomer(customer);
    }

    public static MethodChainingOrderBuilder forCustomer(String name){
        return new MethodChainingOrderBuilder(name);
    }

    public TradeBuilder buy(int quantity){
        return new TradeBuilder(this, quantity, Trade.Type.BUY);
    }

    public TradeBuilder sell(int quantity){
        return new TradeBuilder(this, quantity, Trade.Type.SELL);
    }

    public MethodChainingOrderBuilder addTrade(Trade trade){
        order.addTrade(trade);
        return this;
    }

    public Order end(){
        return order;
    }

}

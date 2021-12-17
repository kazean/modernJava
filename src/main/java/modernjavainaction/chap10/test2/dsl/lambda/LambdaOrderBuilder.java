package modernjavainaction.chap10.test2.dsl.lambda;

import modernjavainaction.chap10.test2.dsl.model.Order;
import modernjavainaction.chap10.test2.dsl.model.Trade;

import java.util.function.Consumer;

public class LambdaOrderBuilder {
    private Order order = new Order();

    public static Order order(Consumer<LambdaOrderBuilder> c){
        LambdaOrderBuilder lambdaOrderBuilder = new LambdaOrderBuilder();
        c.accept(lambdaOrderBuilder);
        return lambdaOrderBuilder.order;
    }

    public void forCustomer(String customer){
        this.order.setCustomer(customer);
    }

    public void buy(Consumer<TradeBuilder> consumer){
        trade(consumer, Trade.Type.BUY);
    }

    public void sell(Consumer<TradeBuilder> consumer){
        trade(consumer, Trade.Type.SELL);
    }

    private void trade(Consumer<TradeBuilder> consumer, Trade.Type type){
        TradeBuilder tradeBuilder = new TradeBuilder();
        tradeBuilder.getTrade().setType(type);
        consumer.accept(tradeBuilder);
        order.addTrade(tradeBuilder.getTrade());
    }
}

package modernjavainaction.chap10.test2.dsl.mixed;

import modernjavainaction.chap10.test2.dsl.model.Order;
import modernjavainaction.chap10.test2.dsl.model.Trade;

import java.util.function.Consumer;
import java.util.stream.Stream;

public class MixNestedFunction {
    public static Order forCustomer(String customer, TradeBuilder... tradeBuilders){
        Order order = new Order();
        order.setCustomer(customer);
        Stream.of(tradeBuilders).forEach(b-> order.addTrade(b.getTrade()));
        return order;
    }

    public static TradeBuilder buy(Consumer<TradeBuilder> consumer){
        return buildTrade(consumer, Trade.Type.BUY);
    }

    public static TradeBuilder sell(Consumer<TradeBuilder> consumer){
        return buildTrade(consumer, Trade.Type.SELL);
    }

    private static TradeBuilder buildTrade(Consumer<TradeBuilder> conumser, Trade.Type type){
        TradeBuilder tradeBuilder = new TradeBuilder();
        tradeBuilder.getTrade().setType(type);
        conumser.accept(tradeBuilder);
        return tradeBuilder;
    }

}

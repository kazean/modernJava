package modernjavainaction.chap10.test2.dsl.lambda;


import modernjavainaction.chap10.test2.dsl.model.Trade;

import java.util.function.Consumer;

public class TradeBuilder {
    private Trade trade = new Trade();

    public Trade getTrade() {
        return trade;
    }
    public void setTrade(Trade trade) {
        this.trade = trade;
    }

    public void quantity(int quantity){
        trade.setQuantity(quantity);
    }

    public void price(double price){
        trade.setPrice(price);
    }

    public void stock(Consumer<StockBuilder> consumer){
        StockBuilder stockBuilder = new StockBuilder();
        consumer.accept(stockBuilder);
        this.trade.setStock(stockBuilder.getStock());
    }
}

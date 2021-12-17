package modernjavainaction.chap10.test2.dsl.methodchain;

import modernjavainaction.chap10.test2.dsl.model.Trade;

public class TradeBuilder {
    private final MethodChainingOrderBuilder mcob;
    public final Trade trade = new Trade();

    public TradeBuilder(MethodChainingOrderBuilder mcob, int quantity, Trade.Type type) {
        this.mcob = mcob;
        trade.setQuantity(quantity);
        trade.setType(type);
    }

    public StockBuilder stock(String symbol){
        return new StockBuilder(mcob, trade, symbol);
    }
}

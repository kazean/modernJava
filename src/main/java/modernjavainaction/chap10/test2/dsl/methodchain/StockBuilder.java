package modernjavainaction.chap10.test2.dsl.methodchain;

import modernjavainaction.chap10.test2.dsl.model.Stock;
import modernjavainaction.chap10.test2.dsl.model.Trade;

public class StockBuilder {
    private final MethodChainingOrderBuilder mcob;
    private final Trade trade;
    private final Stock stock = new Stock();

    public StockBuilder(MethodChainingOrderBuilder mcob, Trade trade, String symbol) {
        this.mcob = mcob;
        this.trade = trade;
        stock.setSymbol(symbol);
    }

    public TradeBuilderWithStcok on(String market){
        stock.setMarket(market);
        trade.setStock(stock);
        return new TradeBuilderWithStcok(mcob, trade);
    }
}

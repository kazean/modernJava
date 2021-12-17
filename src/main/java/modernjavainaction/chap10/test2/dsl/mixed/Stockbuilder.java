package modernjavainaction.chap10.test2.dsl.mixed;

import modernjavainaction.chap10.test2.dsl.model.Stock;
import modernjavainaction.chap10.test2.dsl.model.Trade;

public class Stockbuilder {
    private Stock stock = new Stock();
    private TradeBuilder tradeBuilder;
    private Trade trade;

    public Stockbuilder(TradeBuilder tradeBuilder, Trade trade, String symbol) {
        this.tradeBuilder = tradeBuilder;
        this.trade = trade;
        stock.setSymbol(symbol);
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public TradeBuilder on(String market){
        stock.setMarket(market);
        return tradeBuilder;
    }
}

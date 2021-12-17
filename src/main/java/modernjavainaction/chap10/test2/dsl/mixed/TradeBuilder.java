package modernjavainaction.chap10.test2.dsl.mixed;

import modernjavainaction.chap10.test2.dsl.model.Trade;

public class TradeBuilder {
    private Trade trade = new Trade();

    public Trade getTrade() {
        return trade;
    }

    public void setTrade(Trade trade) {
        this.trade = trade;
    }

    public TradeBuilder quantity(int quantity){
        trade.setQuantity(quantity);
        return this;
    }

    public TradeBuilder at(double price){
        trade.setPrice(price);
        return this;
    }

    public Stockbuilder stock(String symbol){
        return new Stockbuilder(this, trade, symbol);
    }
}

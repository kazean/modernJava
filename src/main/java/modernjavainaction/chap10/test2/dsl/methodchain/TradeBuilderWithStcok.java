package modernjavainaction.chap10.test2.dsl.methodchain;

import modernjavainaction.chap10.test2.dsl.model.Trade;

public class TradeBuilderWithStcok {
    private final MethodChainingOrderBuilder mcob;
    private final Trade trade;

    public TradeBuilderWithStcok(MethodChainingOrderBuilder mcob, Trade trade) {
        this.mcob = mcob;
        this.trade = trade;
    }

    public MethodChainingOrderBuilder at(double price){
        trade.setPrice(price);
        return mcob.addTrade(trade);
    }
}

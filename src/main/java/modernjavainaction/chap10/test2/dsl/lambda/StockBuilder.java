package modernjavainaction.chap10.test2.dsl.lambda;

import modernjavainaction.chap10.test2.dsl.model.Stock;

public class StockBuilder {
    private Stock stock = new Stock();

    public void symbol(String symbol){
        stock.setSymbol(symbol);
    }

    public void market(String market){
        stock.setMarket(market);
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
}

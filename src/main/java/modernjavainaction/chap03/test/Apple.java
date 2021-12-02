package modernjavainaction.chap03.test;

public class Apple {
    int weight;
    Color colr;

    public Apple(int weight, Color colr) {
        this.weight = weight;
        this.colr = colr;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Color getColr() {
        return colr;
    }

    public void setColr(Color colr) {
        this.colr = colr;
    }
}

package modernjavainaction.chap02.test;

import java.util.ArrayList;
import java.util.List;

public class PrettyPrintApple {
    public static void main(String[] args) {
        List<Apple> appleList = new ArrayList<>();
        appleList.add(new Apple("RED",150));
        appleList.add(new Apple("RED",100));
        appleList.add(new Apple("RED",180));
        appleList.add(new Apple("RED",250));
        appleList.add(new Apple("RED",200));
        appleList.add(new Apple("GREEN",250));
        appleList.add(new Apple("GREEN",150));
        appleList.add(new Apple("GREEN",290));
        appleList.add(new Apple("GREEN",270));
        appleList.sort((Apple a, Apple b) -> Integer.valueOf(a.weight).compareTo(b.weight));
        prettyPrintApple(appleList, new PrettyPrintColorWeight());
    }

    public static void prettyPrintApple(List<Apple> inventory, ApplePrettyPrint print){
        for(Apple a : inventory){
            String output = print.test(a);
            System.out.println(output);
        }

    }
    static class Apple{
        int weight;
        String color;

        public Apple(String color,int weight) {
            this.weight = weight;
            this.color = color;
        }
    }
    interface ApplePrettyPrint {
        String test(Apple a);
    }

    static class PrettyPrintColorWeight implements ApplePrettyPrint{
        @Override
        public String test(Apple a) {
            return String.format("[Apple color :%s, weight:%d ]", a.color,a.weight);
        }
    }

}

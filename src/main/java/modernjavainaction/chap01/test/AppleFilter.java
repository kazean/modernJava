package modernjavainaction.chap01.test;

import scala.App;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class AppleFilter {
    public static void main(String[] args) {
        List<Apple> appleList = new ArrayList<>();
        appleList.add(new Apple("RED",100));
        appleList.add(new Apple("RED",150));
        appleList.add(new Apple("RED",180));
        appleList.add(new Apple("RED",200));
        appleList.add(new Apple("RED",250));
        appleList.add(new Apple("GREEN",150));
        appleList.add(new Apple("GREEN",250));
        appleList.add(new Apple("GREEN",270));
        appleList.add(new Apple("GREEN",290));


//        List<Apple> resultList = filter(appleList, AppleFilter::isGreenApple);
//        List<Apple> resultList = filter(appleList, a-> "GREEN".equals(a.color));
        List<Apple> resultList = filter(appleList, Apple::isGreenApple);
//        resultList.stream().forEach(a-> System.out.println(a));
        System.out.println(resultList);
    }

    public static List<Apple> filter(List<Apple> appleList, Predicate<Apple> p){
        List<Apple> resultList = new ArrayList<>();
        for(Apple a:appleList){
            if(p.test(a)){
                resultList.add(a);
            }
        }

        return resultList;
    }

    public static boolean isGreenApple(Apple a){
        if("GREEN".equals(a.color)){
            return true;
        }else{
            return false;
        }
    }
}

class Apple{
    String color;
    int weight;

    public Apple(String color, int weight) {
        this.color = color;
        this.weight = weight;
    }

    public boolean isGreenApple(){
        return "GREEN".equals(this.color);
    }

    @Override
    public String toString() {
        return String.format("Apple{color='%s', weight=%d}", color, weight);
    }
}

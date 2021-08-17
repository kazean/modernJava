package modernjavainaction.chap08.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Test {
    public static void main(String[] args) {
        List<Integer> numList = new ArrayList<>();
        numList.add(1);
        numList.add(2);
        numList.add(3);
        numList.add(4);
        numList.add(5);

        numList.removeIf(i->i%2==0);
        numList.forEach(i-> System.out.println(i));

        HashMap<Integer,Integer> hashMap = new HashMap<>();
        hashMap.put(1,2);
        hashMap.put(2,4);

        hashMap.computeIfAbsent(2,key->key*3);
        hashMap.forEach((k,v) -> System.out.println(k+":"+v));

        System.out.println(">>>>>>");

        hashMap.computeIfAbsent(3,key->key*2);
        hashMap.forEach((k,v) -> System.out.println(k+":"+v));

        System.out.println(">>>>>>");

        hashMap.computeIfPresent(3,(k,v)->v*2);
        hashMap.forEach((k,v) -> System.out.println(k+":"+v));


        System.out.println(">>>>>>");

        hashMap.computeIfPresent(3,(k,v)->v*2);
        hashMap.forEach((k,v) -> System.out.println(k+":"+v));

        ConcurrentHashMap<Integer,Integer> concurrentHashMap = new ConcurrentHashMap<>();
    }

}

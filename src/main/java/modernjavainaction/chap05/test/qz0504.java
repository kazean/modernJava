package modernjavainaction.chap05.test;

import java.util.stream.Stream;

public class qz0504 {
    public static void main(String[] args) {
        Stream.iterate(new int[]{0,1}, n->new int[]{n[1], n[0]+n[1]})
                .limit(10)
//                .forEach(t -> System.out.println("(" + t[0] + "," + t[1] +")"));
                .map(t -> t[0])
                .forEach(System.out::println);

    }
}

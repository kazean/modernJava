package modernjavainaction.chap19.test;

import modernjavainaction.chap19.LazyLists;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class PracticePrime {
    public static void main(String[] args) {
        LazyList<Integer> numbers = from(2);
//        System.out.println(numbers.head);
//        System.out.println(numbers.tail().head());
        LazyList<Integer> primeList = (LazyList<Integer>) primes(numbers);

        System.out.println(primeList.head());
        System.out.println(primeList.tail().head());
        System.out.println(primeList.tail().tail().head());
        System.out.println(primeList.tail().tail().tail().head());
        System.out.println(primeList.tail().tail().tail().tail().head());


//        printAll(primes(from(2)));

    }

    public static LazyList<Integer> from(int n){
        return new LazyList<Integer>(n, ()->from(n+1));
    }

    public static MyList<Integer> primes(MyList<Integer> numbers){
        return new LazyList<>(numbers.head(), ()->primes(numbers.tail().filter(n ->n% numbers.head()!=0
        )));
    }

    static int cnt=0;
    static <T> void printAll(MyList<T> numbers) {
        if (numbers.isEmpty()) {
            return;
        }
        System.out.println(numbers.head());
        if(cnt >10){
            return;
        }
        cnt++;
        printAll(numbers.tail());
    }

}

interface MyList<T>{
    T head();
    MyList<T> tail();

    default boolean isEmpty(){
        return true;
    }

    MyList<T> filter(Predicate<T> p);
}

class LazyList<T> implements MyList<T>{
    final T head;
    final Supplier<MyList<T>> tail;

    public LazyList(T head, Supplier<MyList<T>> tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override
    public T head() {
        return head;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public MyList<T> tail() {
        return tail.get();
    }

    @Override
    public MyList<T> filter(Predicate<T> p) {
        return isEmpty() ? this : p.test(head()) ? new LazyList<>(head(), () -> tail().filter(p)) : tail().filter(p);
    }
}
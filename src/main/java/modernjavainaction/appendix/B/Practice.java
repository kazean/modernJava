package modernjavainaction.appendix.B;

import java.util.concurrent.atomic.AtomicInteger;

public class Practice {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        int min = atomicInteger.accumulateAndGet(10, Integer::min);
        System.out.println(min);
        atomicInteger = new AtomicInteger(1);
        int tmp = atomicInteger.getAndUpdate(i->i+1);
        System.out.println(tmp);
        System.out.println(atomicInteger.get());
        System.out.println(atomicInteger.updateAndGet(i->i+1));
    }
}

package modernjavainaction.chap17.test;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import modernjavainaction.chap17.TempInfo;

import java.util.concurrent.TimeUnit;

public class Practice {
    public static void main(String[] args) throws InterruptedException {
        Observable<String> strings = Observable.just("first","second");
        Observable<Long> onePerSec = Observable.interval(1, TimeUnit.SECONDS);
        onePerSec.subscribe(i-> System.out.println(TempInfo.fetch("New York")));
        Thread.sleep(10000);
//        onePerSec.blockingSubscribe(i-> System.out.println(TempInfo.fetch("New York")));
        ObservableEmitter emitter;

    }
}

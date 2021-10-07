package modernjavainaction.chap17.rxjava;

import static modernjavainaction.chap17.rxjava.TempObservable.getCelsiusTemperatures;
import static modernjavainaction.chap17.rxjava.TempObservable.getNegativeTemperature;

import io.reactivex.Observable;
import modernjavainaction.chap17.TempInfo;

public class MainCelsius {

  public static void main(String[] args) {
    mergeGetCelsusTemperatures();
//    filterGetCelsius();
    
  }

  public static void mergeGetCelsusTemperatures(){
    Observable<TempInfo> observable = getCelsiusTemperatures("New York", "Chicago", "San Francisco");
    observable.blockingSubscribe(new TempObserver());
  }

  public static void filterGetCelsius(){
    Observable<TempInfo> observable = getNegativeTemperature("New York");
    observable.blockingSubscribe(new TempObserver());
  }

}

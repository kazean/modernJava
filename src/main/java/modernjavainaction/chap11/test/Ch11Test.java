package modernjavainaction.chap11.test;

import modernjavainaction.chap11.Car;
import modernjavainaction.chap11.Insurance;
import modernjavainaction.chap11.Person;

import java.util.Optional;

public class Ch11Test {
    public static void main(String[] args) {
        Optional<Person> person = Optional.empty();
        String str = person.flatMap(Person::getCar).flatMap(Car::getInsurance).map(Insurance::getName).orElse("UNKNWON");
        System.out.println("str = " + str);
    }
}

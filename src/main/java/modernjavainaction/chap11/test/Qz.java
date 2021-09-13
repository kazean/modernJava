package modernjavainaction.chap11.test;

import modernjavainaction.chap11.Car;
import modernjavainaction.chap11.Insurance;
import modernjavainaction.chap11.Person;

import java.util.Optional;

public class Qz {
    public static void main(String[] args) {
        Optional<Person> opt = Optional.empty();
    }

    public Insurance findCheapestInsurance(Person person, Car car){
        Insurance cheapestComapany = new Insurance();
        return cheapestComapany;
    }

//    qz11-1
    public Optional<Insurance> nullSafeFindCheapestInstance(Optional<Person> person, Optional<Car> car){
        return person.flatMap(p-> car.map(c-> findCheapestInsurance(p,c)));
    }
//    qz11-2
    public String getCarInsuranceName(Optional<Person> person, int minAge){
        return person.filter(p->p.getAge()>=minAge)
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
    }
}

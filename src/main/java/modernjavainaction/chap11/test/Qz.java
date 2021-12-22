package modernjavainaction.chap11.test;

import modernjavainaction.chap11.Car;
import modernjavainaction.chap11.Insurance;
import modernjavainaction.chap11.Person;

import java.util.Optional;
import java.util.Properties;

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

//    qz11-3
    public int readDuration(Properties props, String name){
        return Optional.ofNullable(props.getProperty(name))
                .flatMap(OptionalUtility::stringToInt)
                .filter(i->i>0)
                .orElse(0);
    }

    static class OptionalUtility{
        public static Optional<Integer> stringToInt(String str){
            return Optional.of(str).map(s->Integer.parseInt(s));
        }
    }
}

package modernjavainaction.chap05.test;

import modernjavainaction.chap05.Trader;
import modernjavainaction.chap05.Transaction;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class RealTestCh05 {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul","Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian,2011,300),
                new Transaction(raoul,2012,1100),
                new Transaction(raoul,2011,400),
                new Transaction(mario,2012,710),
                new Transaction(mario,2012,700),
                new Transaction(brian,2011,950)
        );

        transactions.stream()
                .filter(t->t.getYear()==2011)
                .map(Transaction::getValue)
                .sorted();

        transactions.stream()
                .map(t-> t.getTrader().getCity())
                .distinct()
                .sorted();

        transactions.stream()
                .filter(t->t.getTrader().getCity().equals("Cambridge"))
                .map(t->t.getTrader().getName())
                .distinct()
                .sorted();

        transactions.stream()
                .map(t->t.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (n1,n2)->n1+n2);

        transactions.stream()
                .anyMatch(t->t.getTrader().getCity().equals("Milan"));

        transactions.stream()
                .filter(t->t.getTrader().getCity().equals("Cambridge"))
                .map(t->t.getValue())
                .collect(toList());

        transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);

        transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::min);

    }
}

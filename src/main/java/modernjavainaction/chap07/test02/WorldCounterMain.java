package modernjavainaction.chap07.test02;

import modernjavainaction.chap07.WordCount;

import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class WorldCounterMain {
    static final String SENTENCE =" Nel   mezzo del cammin  di nostra  vita "
            + "mi  ritrovai in una  selva oscura"
            + " che la  dritta via era   smarrita ";
    public static void main(String[] args) {
        Spliterator<Character> spliterator = new WordCountSpliterator(SENTENCE);
        Stream<Character> stream = StreamSupport.stream(spliterator, true);
        System.out.println(countWords(stream));
    }
    private static int countWords(Stream<Character> stream) {
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine);
        return wordCounter.getCounter();
    }



    private static class WordCounter {

        private final int counter;
        private final boolean lastSpace;

        public WordCounter(int counter, boolean lastSpace) {
            this.counter = counter;
            this.lastSpace = lastSpace;
        }

        public WordCounter accumulate(Character c) {
            if (Character.isWhitespace(c)) {
                return lastSpace ? this : new WordCounter(counter, true);
            }
            else {
                return lastSpace ? new WordCounter(counter + 1, false) : this;
            }
        }

        public WordCounter combine(WordCounter wordCounter) {
            return new WordCounter(counter + wordCounter.counter, wordCounter.lastSpace);
        }

        public int getCounter() {
            return counter;
        }

    }
}

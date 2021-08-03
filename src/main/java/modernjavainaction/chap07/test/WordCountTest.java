package modernjavainaction.chap07.test;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.*;
public class WordCountTest {
    public static final String SENTENCE =
            " Nel   mezzo del cammin  di nostra  vita "
                    + "mi  ritrovai in una  selva oscura"
                    + " che la  dritta via era   smarrita ";

    public static void main(String[] args) {

//    1 명령형 반복문
        System.out.println("interactvie word count : " + wordCountImperactive());
//    2 순차실행코드
//        int > Integer BOXING 사용 x
//        System.out.println("sequential word count1 : " + wordCount(SENTENCE.chars().mapToObj(i-> (char)i)) );
        System.out.println("sequential word count2 : " + wordCount(IntStream.range(0, SENTENCE.length()).mapToObj(SENTENCE::charAt)) );
//    3 병렬실행시 custom spliterator 활용
        System.out.println("parallerl custom Spliterator word count : " + wordCountParallelCustomSpliterator());
    }

    public static int wordCountImperactive(){
        int count = 0;
        boolean lastSpace = true;

        char[] charArr = SENTENCE.toCharArray();
        for(char c : charArr){
            if(Character.isWhitespace(c)){
                lastSpace = true;
            }else{
                if(lastSpace){
                    count++;
                }
                lastSpace = Character.isWhitespace(c);
            }
        }
        return count;
    }

    public static int wordCountParallelCustomSpliterator(){
        Spliterator<Character> spliterator = new WordCounterSpliteratorTest(SENTENCE);
        Stream<Character> stream = StreamSupport.stream(spliterator, true);
        return wordCount(stream);
    }

    public static int wordCount(Stream<Character> sc){
        return sc.reduce(new WordCounterTest(true,0),WordCounterTest::accmulate, WordCounterTest::combine)
                .getCounter();
    }



//    문자 카운트 클래스
    private static class WordCounterTest{
        private final boolean lastSpace;
        private final int counter;

        public WordCounterTest(boolean lastSpace, int counter) {
            this.lastSpace = lastSpace;
            this.counter = counter;
        }

        public WordCounterTest accmulate(Character c){
            if(Character.isWhitespace(c)){
                return lastSpace ? this : new WordCounterTest(true,counter);
            }else{
                return lastSpace ? new WordCounterTest(false,counter+1) : this;
            }
        }

        public WordCounterTest combine(WordCounterTest wordCounterTest){
            return new WordCounterTest(true, counter+wordCounterTest.counter);
        }

        public int getCounter() {
            return counter;
        }
    }

    private static class WordCounterSpliteratorTest implements Spliterator<Character>{
        private int curChar = 0;
        private final String string;

        public WordCounterSpliteratorTest(String string) {
            this.string = string;
        }

        @Override
        public boolean tryAdvance(Consumer<? super Character> action) {
            action.accept(string.charAt(curChar++));
            return curChar < string.length();
        }

        @Override
        public Spliterator<Character> trySplit() {
            int curSize = string.length()-curChar;
            if(curSize < 10){
                return null;
            }else{
                for(int splitPos = curSize/2+curChar; splitPos < string.length(); splitPos++){
                    if(Character.isWhitespace(string.charAt(splitPos))){
                        Spliterator<Character> spliterator = new WordCounterSpliteratorTest(string.substring(curChar,splitPos));
                        curChar = splitPos;
                        return spliterator;
                    }
                }
            }
            return null;
        }

        @Override
        public long estimateSize() {
            return string.length() - curChar;
        }

        @Override
        public int characteristics() {
            return ORDERED + SIZED + NONNULL + IMMUTABLE + SUBSIZED;
        }
    }


}

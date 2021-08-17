package modernjavainaction.chap09.test;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertEquals;
import static java.util.Comparator.*;
import static org.junit.Assert.assertTrue;

public class Practice {

    @Test
    public void testComparingTwoPoints() throws Exception {
        Point p1 = new Point(5,5);
        Point p2 = p1.moveRightBy(10);
        assertEquals(15,p2.getX());
        assertEquals(5,p2.getY());

        Point p3 = new Point(10,15);
        Point p4 = new Point(10,20);

        int result = Point.compareByXAndThenY.compare(p1,p2);
//        System.out.println("result >>" + result);
        assertTrue(result < 0);

//        int testReuslt = comparing(Point::getX).compare(new Point(2,0), new Point(1,0));
//        System.out.println("testResult >> " + testReuslt);
    }

    public static class Point{
        public final static Comparator<Point> compareByXAndThenY = comparing(Point::getX).thenComparing(Point::getY);
//        public final static Comparator<Point> compareByXAndThenY = comparing(Point::getX);

        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public Point moveRightBy(int x){
            return new Point(this.x+x, this.y);
        }
    }
}

package modernjavainaction.chap12.practice;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Main {
    public static void main(String[] args) {
//        deprecatedClass();
        javaTimePackage();
    }

    public static void deprecatedClass(){
//        CET, month index init 0
        Date date = new Date(117,8,21);
        System.out.println("java 1.0 Date >>" + date.toString());

        Calendar cal = Calendar.getInstance();
        cal.set(2017,8,21,0,0,0);
        System.out.println("java 1.1 cal.getTime() >>" + cal.getTime());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        System.out.println("SimpleDateFormat >>"+sdf.format(date));
    }

    public static void javaTimePackage(){
        LocalDate date1 = LocalDate.of(2017, 9, 21);

        date1.getYear();
        date1.getMonthValue();
        date1.getDayOfMonth();
        date1.get(ChronoField.YEAR);
        date1.get(ChronoField.MONTH_OF_YEAR);
        date1.get(ChronoField.DAY_OF_MONTH);
        date1.isLeapYear();
        date1.lengthOfMonth();

        date1 = LocalDate.parse("2011-08-22");
        date1 = date1.plus(1, ChronoUnit.DAYS);
        System.out.println(date1.toString());
        LocalDate date2 = date1.with(ChronoField.MONTH_OF_YEAR,9);
        System.out.println(date2.toString());

        LocalTime time1 = LocalTime.of(23, 0, 59);
        time1.getHour();
        time1.getMinute();
        time1.getSecond();
        time1.getNano();
        System.out.println(time1.toString());
        time1 = LocalTime.parse("22:50:01");
        System.out.println(time1.toString());

        LocalDateTime dt1 = LocalDateTime.of(2011, 8, 22, 23, 0, 0);
        dt1 = LocalDateTime.of(date1, time1);
        dt1 = date1.atTime(time1);
        dt1 = date1.atTime(22, 50, 01);
        dt1 = time1.atDate(date1);
        System.out.println(dt1.toString());
        date1 = dt1.toLocalDate();
        time1 = dt1.toLocalTime();

        Instant instant = Instant.ofEpochSecond(3);
        instant = Instant.ofEpochSecond(3, 1_000_000_000);
        System.out.println(instant.toString());
        instant = Instant.ofEpochSecond(3, 1);
        System.out.println(instant.toString());
        System.out.println(instant.getNano());

        LocalDateTime dt2 = LocalDateTime.of(2011, 8,22,23,50,01);
        Duration duration = Duration.between(dt1,dt2);
        System.out.println("dur >> " +duration.toString());
        System.out.println(duration.getSeconds());
        Duration.of(3, ChronoUnit.MINUTES);
        Duration.ofDays(3);
        Duration.ofHours(1);
        System.out.println();

        Period period = Period.between(date1, date2);
        System.out.println(date1.toString());
        System.out.println(date2.toString());
        System.out.println("period >>" + period.toString());
        System.out.println(period.getMonths());
        period = Period.of(2011, 8, 22);
        period = Period.ofDays(1);
        LocalDate date3 = (LocalDate) period.addTo(date2);
        System.out.println(date3.toString());

        duration.addTo(time1);
        period.addTo(date1);
        duration.get(ChronoUnit.SECONDS);
        duration.isNegative();
        duration.isZero();
        duration.minus(duration);
        duration.multipliedBy(2);
        duration.negated();
        duration.plus(duration);
        duration.subtractFrom(time1);

        LocalDate date4 = LocalDate.of(2021, 9, 21);
        date4 = date4.with(TemporalAdjusters.dayOfWeekInMonth(3, DayOfWeek.MONDAY));
        System.out.println(date4.toString());

        DateTimeFormatter dtf =DateTimeFormatter.BASIC_ISO_DATE;
        dtf = DateTimeFormatter.ISO_LOCAL_DATE;
        dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        ZonedDateTime zdf = dt1.atZone(TimeZone.getDefault().toZoneId());
        zdf.toInstant();
        Date date = new Date(2021, 9, 21);
        instant = dt1.toInstant(ZoneOffset.UTC);
        System.out.println(Date.from(instant).toString());
        ZoneId zoneId = TimeZone.getDefault().toZoneId();

    }
}

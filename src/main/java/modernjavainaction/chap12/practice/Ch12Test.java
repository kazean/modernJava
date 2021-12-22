package modernjavainaction.chap12.practice;

import java.time.*;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.Chronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;
import java.util.TimeZone;

import static java.time.temporal.TemporalAdjusters.*;

public class Ch12Test {
    public static void main(String[] args) {
//        localdate();
//        instant();
//        durationAndPeriod();
//        localdateAdjuster();
        zoneId();
    }

    public static void zoneId(){
        ZoneId romeZone = ZoneId.of("Europe/Rome");
        ZoneId defaultZone = TimeZone.getDefault().toZoneId();

        LocalDate date = LocalDate.now();
        ZonedDateTime zdt1 = date.atStartOfDay(romeZone);
        System.out.println("zdt1 = " + zdt1);
        LocalDateTime ldf = LocalDateTime.now();
        ZonedDateTime zdt2 = ldf.atZone(romeZone);
        System.out.println("zdt2 = " + zdt2);
        Instant instant = Instant.now();
        ZonedDateTime zdt3 = instant.atZone(romeZone);
        System.out.println("zdt3 = " + zdt3);
        System.out.println();

        LocalDateTime ldf2 = LocalDateTime.ofInstant(instant, romeZone);
        System.out.println("ldf2 = " + ldf2);
        System.out.println();

        ZoneOffset newYorkOffset = ZoneOffset.of("-05:00");
        LocalDateTime ldf3 = LocalDateTime.now();
        OffsetDateTime offsetDateTime = OffsetDateTime.of(ldf3, newYorkOffset);
        System.out.println("offsetDateTime = " + offsetDateTime);
        System.out.println();

        Chronology japaneseChronology = Chronology.ofLocale(Locale.JAPAN);
        ChronoLocalDate now = japaneseChronology.dateNow();
        System.out.println("now = " + now);
    }

    public static void localdateAdjuster(){
        LocalDate date1 = LocalDate.of(2021,12,22);
        LocalDate date2 = date1.withYear(2017);
        System.out.println("date2 = " + date2);
        LocalDate date3 = date1.with(ChronoField.YEAR, 2017);
        System.out.println("date3 = " + date3);
        LocalDate date4 = date1.with(Year.of(2021)).with(Month.DECEMBER).with(DayOfWeek.WEDNESDAY);
        System.out.println("date4 = " + date4);
        System.out.println();

//        TemperoalAdjusters
        LocalDate date5 = date1.with(lastDayOfMonth());
        System.out.println("date5 = " + date5);
        LocalDate date6 = date1.with(dayOfWeekInMonth(1, DayOfWeek.MONDAY));
        System.out.println("date6 = " + date6);
        LocalDate date7 = date1.with(firstInMonth(DayOfWeek.MONDAY));
        System.out.println("date7 = " + date7);
        System.out.println();

//        java.time.format DateTimeFormatter
        String basicIsoDate = date1.format(DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println("basicIsoDate = " + basicIsoDate);
        String isoLocalDate = date1.format(DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println("isoLocalDate = " + isoLocalDate);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formatterToString = date1.format(formatter);
        System.out.println("formatterToString = " + formatterToString);
        LocalDate parse = LocalDate.parse("22/12/2021", formatter);
        System.out.println("parse = " + parse);
        System.out.println();

        DateTimeFormatter italianFormatter = DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.ITALIAN);
        String italianStr = date1.format(italianFormatter);
        System.out.println("italianStr = " + italianStr);
        LocalDate parse1 = LocalDate.parse(italianStr, italianFormatter);
        System.out.println("parse1 = " + parse1);

        DateTimeFormatter italianFormatter2 = new DateTimeFormatterBuilder()
            .appendText(ChronoField.DAY_OF_MONTH)
            .appendLiteral(". ")
            .appendText(ChronoField.MONTH_OF_YEAR)
            .appendLiteral(" ")
            .appendText(ChronoField.YEAR)
            .parseCaseInsensitive()
            .toFormatter(Locale.ITALIAN);
    }

    public static void durationAndPeriod(){
//        Duration
        LocalTime localTime1 = LocalTime.of(13, 0, 0);
        LocalTime localTime2 = LocalTime.of(13, 18, 30);

        Duration durationBetween = Duration.between(localTime1, localTime2);
        System.out.println("durationBetween = " + durationBetween);
        long ldBetweenSeconds = durationBetween.getSeconds();
        System.out.println("ldBetweenSeconds = " + ldBetweenSeconds);
        System.out.println();

        Duration durationInstantBetween = Duration.between(Instant.ofEpochSecond(2), Instant.ofEpochSecond(5));
        long instantBetweenSeconds = durationInstantBetween.getSeconds();
        System.out.println("instantBetweenSeconds = " + instantBetweenSeconds);
        System.out.println();

//        Period
        Period betweenDays = Period.between(LocalDate.parse("2021-12-22"), LocalDate.parse("2021-12-23"));
        System.out.println("betweenDays = " + betweenDays);

        LocalDate startDate = LocalDate.parse("2021-12-22");
        LocalDate endDate = LocalDate.parse("2021-12-23");
        Period betweenDays2 = Period.between(startDate, endDate);
        Period from = Period.from(betweenDays2);
        System.out.println("from = " + from);
        Period of = Period.of(1, 1, 1);
        System.out.println("of = " + of);
        Period p1Y2M3D = Period.parse("P1Y2M3D");
        System.out.println("p1Y2M3D = " + p1Y2M3D);
        System.out.println();

        int years = betweenDays.getYears();
        System.out.println("years = " + years);
        int months = betweenDays.getMonths();
        System.out.println("months = " + months);
        int days = betweenDays.getDays();
        System.out.println("days = " + days);
        System.out.println();

        LocalDate makeFromStartDate = (LocalDate) betweenDays2.addTo(startDate);
        System.out.println("makeFromStartDate = " + makeFromStartDate);
        LocalDate subtractFromStartDate = (LocalDate) betweenDays2.subtractFrom(startDate);
        System.out.println("subtractFromStartDate = " + subtractFromStartDate);
        System.out.println();

        boolean negative = betweenDays2.isNegative();
        System.out.println("negative = " + negative);
        boolean zero = betweenDays2.isZero();
        System.out.println("zero = " + zero);
        System.out.println();

        Period periodPlus = betweenDays.plus(betweenDays2);
        System.out.println("periodPlus = " + periodPlus);
        Period periodMinus = betweenDays.minus(betweenDays2);
        System.out.println("periodMinus = " + periodMinus);
        boolean zero1 = periodMinus.isZero();
        System.out.println("zero1 = " + zero1);

    }

    public static void instant(){
        Instant instant = Instant.now();
        System.out.println("instant = " + instant);
    }

    public static void localdate(){
        LocalDate date1 = LocalDate.of(2021, 12, 22);
        System.out.println(date1.toString());

        int year = date1.getYear();
        year = date1.get(ChronoField.YEAR);
        System.out.println("year = " + year);

        Month month = date1.getMonth();
        System.out.println("month = " + month);
        int monthValue = date1.getMonthValue();
        monthValue = date1.get(ChronoField.MONTH_OF_YEAR);
        System.out.println("monthValue = " + monthValue);

        int dayOfMonth = date1.getDayOfMonth();
        dayOfMonth = date1.get(ChronoField.DAY_OF_MONTH);
        System.out.println("dayOfMonth = " + dayOfMonth);
        DayOfWeek dayOfWeek = date1.getDayOfWeek();
        System.out.println("dayOfWeek = " + dayOfWeek);
        int dayOfYear = date1.getDayOfYear();
        System.out.println("dayOfYear = " + dayOfYear);

        LocalDate date2 = LocalDate.parse("2021-12-23");
        System.out.println("date2 = " + date2);

        LocalDateTime localDateTime = date1.atTime(LocalTime.of(13, 8, 30));
        System.out.println("localDateTime = " + localDateTime);

        LocalDateTime ldf1 = LocalDateTime.of(2021, 12, 22, 13, 55, 0);
        LocalDateTime ldf2 = LocalDateTime.now();
        long betweenHours = ChronoUnit.YEARS.between(ldf1, ldf2);
        System.out.println("betweenHours = " + betweenHours);
        long betweenMinutes = ChronoUnit.MINUTES.between(ldf1, ldf2);
        System.out.println("betweenMinutes = " + betweenMinutes);
    }

}

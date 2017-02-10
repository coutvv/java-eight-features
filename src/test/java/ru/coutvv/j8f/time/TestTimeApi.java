package ru.coutvv.j8f.time;

import org.testng.annotations.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

/**
 * Created by lomovtsevrs on 10.02.2017.
 */
public class TestTimeApi {

    /**
     * Clock предоставляет доступ к текущей дате и времени. Этот тип знает о часовых поясах
     * и может использоваться вместо вызова System.currentTimeMillis() для возвращения
     * миллисекунд
     */
    @Test
    public void testClockForLegacy() {
        Clock clock = Clock.systemDefaultZone();
        long millis = clock.millis();
        System.out.println(millis);
        Instant instant = clock.instant();
        Date legacyDate = Date.from(instant);
    }

    ZoneId zone1;
    ZoneId zone2;
    /**
     * Часовые пояса представленытипом ZoneId. Доступ можно получить с помощью статических
     * фабричных методов.
     */
    @Test
    public void testTimeZoneId() {
        System.out.println(ZoneId.getAvailableZoneIds());
        //prints all available timezone ids

        zone1 = ZoneId.of("Europe/Berlin");
        zone2 = ZoneId.of("Brazil/East");

        System.out.println(zone1.getRules());
        System.out.println(zone2.getRules());
    }

    /**
     * Тип LocalTime представляет собой время с учётом часового пояса, например, 10pm или 17:30:40. В следующем тесте
     * создаются два местных времени для часовых поясов, определённых выше. Затем оба времени сравниваются и вычисляется
     * разница между ними в часах и минутах
     */
    @Test
    public void testLocalTime() {

        zone1 = ZoneId.of("Europe/Berlin");
        zone2 = ZoneId.of("Brazil/East");
        LocalTime now1 = LocalTime.now(zone1);
        LocalTime now2 = LocalTime.now(zone2);

        long hoursBetween = ChronoUnit.HOURS.between(now1, now2);
        long minutesBetween = ChronoUnit.MINUTES.between(now1, now2);

        System.out.println(hoursBetween);
        System.out.println(minutesBetween);


        LocalTime late = LocalTime.of(23, 59, 59);
        System.out.println(late);

        DateTimeFormatter germanFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(Locale.GERMAN);
        LocalTime leetTime = LocalTime.parse("13:37", germanFormatter);
        System.out.println(leetTime);
    }

    /**
     * Тип LocalDate представляет конкретную дату, например 2014-03-11. Объекты LocalDate неизменяемы и
     * являютя аналогом LocalTime. Пример демонстрирует вычисление новой даты путём сложения или
     * вычитания дней, месяцев, лет. Каждая операция возвращает новый объект
     */
    @Test
    public void testLocalDate() {
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
        LocalDate yesterday = tomorrow.minus(2, ChronoUnit.DAYS);

        LocalDate independenceDay = LocalDate.of(2014, Month.JUNE, 4);
        DayOfWeek dayOfWeek = independenceDay.getDayOfWeek();
        System.out.println(dayOfWeek);

        //Путём парсинга строк:
        DateTimeFormatter germanFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(Locale.GERMAN);
        LocalDate xmas = LocalDate.parse("24.12.2014", germanFormatter);
        System.out.println(xmas);
    }

    /**
     * Тип LocalDateTime представляет собой комбинацию даты и времени. Объекты LocalDateTime
     * неизменяемы и работают аналогично LocalTime и LocalDate. Мы можем использовать различные методы
     * для извлечения конкретных значений из даты-времени.
     */
    @Test
    public void testLocalDateTime() {
        LocalDateTime sylvester = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59);
        DayOfWeek dow = sylvester.getDayOfWeek();
        System.out.println(dow);

        Month month = sylvester.getMonth();
        System.out.println(month);

        long minuteOfDay = sylvester.getLong(ChronoField.MICRO_OF_DAY);
        System.out.println(minuteOfDay);

        //Путём добавления таймзоны можем получть инстанс и ковертировать в старый Date
        Instant instant = sylvester.atZone(ZoneId.systemDefault()).toInstant();
        Date legacyDate = Date.from(instant);
        System.out.println(legacyDate);

        //форматирование даты-времени
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy - HH:mm").withLocale(Locale.ENGLISH);

        LocalDateTime parsed = LocalDateTime.parse("Apr 03, 2014 - 07:13", formatter);
        String s = formatter.format(parsed);
        System.out.println(s);
    }


}

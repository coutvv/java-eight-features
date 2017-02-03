package ru.coutvv.j8f.nativefinterface;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.testng.Assert;
import org.testng.annotations.Test;

import ru.coutvv.j8f.lambda.Something;

/**
 * @author lomovtsevrs
 *
 */
public class TestNativeFunctionalInterface {

	/**
	 * Предикаты -- это функции, принимающие один аргумент,
	 * и возвращающие значение типа boolean. Интерфейс содержит
	 * различные методы по умолчанию, позволяющие строить
	 * сложные условия(and, or
	 */
	@Test
	public void testPredicate() {
		Predicate<String> predicate = (s) -> s.length() > 0;
		
		predicate.test("fuck off");
		assertTrue(predicate.test("fuck off"));
		assertFalse(predicate.negate().test("fuck off"));
		
		Predicate<Object> nonNull = Objects::nonNull;
		Predicate<Object> isNull = Objects::isNull;
		
		System.out.println(nonNull.test(new String()));
		
		
		Predicate<String> isEmpty = String::isEmpty;
		Predicate<String> isNotEmpty = isEmpty.negate();
	}
	/**
	 * Функции принимают один аргумент и возвращают некоторый результат.
	 * Методы по умолчанию могут использоваться для построения цепочек
	 * вызовов(compose, andThen)
	 */
	@Test(expectedExceptions = {NumberFormatException.class})
	public void testFunction() {
		Function<String, Integer> toInteger = Integer::valueOf;
		Assert.assertTrue(toInteger.apply("1") == 1);
		
		//compose(insert function before current)
		Function<Integer, String> doubleToInteger = String::valueOf;
		Function<Double, String> doubleIntegerPartToString = doubleToInteger.compose(Double::intValue);
		
		System.out.println(doubleIntegerPartToString.apply(2.123));
		
		//andThen
		Function<String, String> backToString = toInteger.andThen(String::valueOf);
		backToString.apply("12");
		backToString.apply("12a");
		Assert.fail("we need to kill ourselves");
	}
	
	/**
	 * Поставщики предоставляют результат заданного типа.
	 * В отличии от функций, поставщики не принимают аргументов.
	 */
	@Test
	public void testSupplier() {
		Supplier<Something> somethingSupplier = Something::new;
		System.out.println(somethingSupplier.get());
	}
	
	/**
	 * Потребители представляют собой операции, которые производятся
	 * над одним входным аргументом.
	 */
	@Test
	public void testConsumer() {
		Consumer<Something> greeter = (p) -> System.out.println("Hello, " + p.getFirstName()); 
		greeter.accept(new Something("Holson", "Denbroah"));
	}
	
	/**
	 * Java 8 добавляет в интерфейс Comparator различные методы по умолчанию
	 */
	@Test
	public void testNewComparator() {
		Comparator<Something> comparator = (p1, p2) -> p1.getFirstName().compareTo(p2.getFirstName());
		
		Something s1 = new Something("Alice", "Jones");
		Something s2 = new Something("Jane", "Brown");
		
		System.out.println(comparator.compare(s1, s2));
		System.out.println(comparator.reversed().compare(s1, s2));
	}
	
	/**
	 * Опциональные значения -- не являются функциональными интерфейсами, 
	 * однако являются удобным среством предотвращения NullPointerException.
	 * 
	 * Опциональные значения -- это по сути контейнер для значения, которое 
	 * может быть равно null. Например, вам нужен метод, который возвращает 
	 * какое-то значени, но иногда он должен возвращать пустое значение.
	 * Вместо того, чтобы возвращать null, в Java 8 можно вернуть опциональное
	 * значение
	 */
	@Test
	public void testOptional(){
		Optional<String> optional = Optional.of("bam");
		System.out.println(optional.isPresent());
		System.out.println(optional.get());
		System.out.println(optional.orElse("fallback"));
		optional.ifPresent((s) -> System.out.println(s.charAt(0)));
		

		Optional<String> opt = Optional.ofNullable(null);
		opt.orElseGet(() -> "fuck off");
		System.out.println(opt.orElseGet(() -> "strange"));
		System.out.println(opt.map(s -> "Hey " + s + "!").orElse("Hey Stranger"));
		System.out.println(opt.map(s -> "Hey " + s + "!"));

		System.out.println(opt.orElseGet(() -> "nope"));
	}
}











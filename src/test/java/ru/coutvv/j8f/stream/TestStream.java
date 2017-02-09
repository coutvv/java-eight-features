package ru.coutvv.j8f.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestStream {
	
	List<String> stringCollection;
	{
		
	}
	
	@BeforeMethod
	public void init() {
		stringCollection = new ArrayList<>();
		stringCollection.addAll(Arrays.asList("fuck", "dododo", "metaL!", "deathclock"));
	}
	
	@Test(enabled = false)
	public void testCollectionStream() {
		
		stringCollection.stream().filter((s) -> s.length() < 7).forEach(s -> s="holy shit");
		System.out.println(stringCollection);
	}
	
	/**
	 * Операция Filter принимает предикат, который фильтрует
	 * все элементы потока. та операция является <b>промежуточной</b>
	 * т.е. позволяет нам вызвать другую операцию(например forEach) над
	 * результатом. 
	 * 
	 * ForEach принимает функцию, которая вызывается для каждого элемента
	 * в (уже отфильтрованном) потоке. Она является конечной операцией. 
	 * Она не возвращает никакого значения, поэтому дальнейший вызов
	 * потоковых операций невозможен.
	 */
	@Test(enabled = false)
	public void testFilterStream() {
		stringCollection
			.stream()
			.filter((s) -> s.startsWith("d"))
			.forEach(System.out::println);
	}
	
	/**
	 * Операция sorted является промежуточной операцией, которая возвращает
	 * отсортированное представление потока. Элементы сортируются в обычном 
	 * порядке, если вы не предоставили свой компаратор
	 * 
	 * sorted не влияет на порядок элементов в исходной коллекции!!!
	 */
	@Test(enabled = false)
	public void testSorted() {
		stringCollection
			.stream()
			.sorted()
			.filter(s -> s.contains("t"))
			.forEach(TestStream::shitMehod);
		System.out.println();
		System.out.println(stringCollection);
	}
	
	private static void shitMehod(String s) {
		System.out.print(s + ", ");
	}
	
	int sum = 0;
	
	/**
	 * Промежуточная операция map преобразовывает каждый элемент в другой
	 * объект при помощи переданной функции. Тип результирующего потока
	 * зависит от типа функции, котора передаётся при вызове map.
	 */
	@Test
	public void testMapMethod() {
		stringCollection
			.stream()
			.map(String::toUpperCase)
			.sorted()
			.forEach(System.out::println);
		
		stringCollection.addAll(Arrays.asList("23", "32", "1", "32"));
		Predicate<String> isNumber = s -> s.matches("-?\\d+?");
		//переводим все строки которые числа в Integer и суммируем
		stringCollection
			.stream()
			.map(s -> Integer.valueOf( isNumber.test(s) ? s : "0"))
			.forEach(i -> sum = sum+i);
		System.out.println("14" + sum);
		
		//или тоже самое:
		stringCollection
			.stream()
			.filter(s -> s.matches("-?\\d+?"))
			.map(s -> Integer.valueOf(s))
			.forEach(i -> sum += i);
		System.out.println("14" + sum/2);
	}
	
	/**
	 * Для проверки, удовлетворяет ли поток заданному предикату, используются
	 * различные операции сопоставления(match). Все операции сопоставления 
	 * являются конечными и возвращают результат типа boolean.
	 */
	@Test
	public void testMatchMethod() {
		System.out.println("MATCH METHODS CONF");
		boolean anyStartsWithD =
				stringCollection.stream()
					.anyMatch(s -> s.startsWith("d"));
		System.out.println(anyStartsWithD);
		
		boolean allStartsWithD = stringCollection.stream()
									.allMatch(s -> s.startsWith("d"));
		System.out.println(allStartsWithD);
		
		boolean nonStartsWithA = stringCollection.stream()
									.noneMatch(s -> s.startsWith("a"));
		System.out.println(nonStartsWithA);
	}

	/**
	 * Операция Count является конечной операцией и возвращает количество элементов
	 * в потоке. Типом возвращаемого значения является long
	 */
	@Test
	public void testCount() {
		long startsWithD = stringCollection
				.stream()
				.filter((s) -> s.startsWith("d"))
				.count();
		Assert.assertEquals(startsWithD, 2L);
		System.out.println(startsWithD);
	}

	/**
	 * Эта конечная операция производит свертку элементов потока по заданной функции
	 * Результатом является опциональное значение
	 */
	@Test
	public void testReduce() {
		Optional<String> reduced =
				stringCollection
				.stream()
				.sorted()
				.reduce((s1, s2) -> s1 + "#" + s2);
		reduced.ifPresent(System.out::println);
	}
}

package pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_4.tx.controller;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class MyTest {

	public static void main(String[] args) {

		try {
			System.out.println("A");
			throw new RuntimeException();
		}catch (RuntimeException e) {
			System.out.println("B");
		}finally {
			System.out.println("C");
		}
		
		Integer[] array= new Integer[] {1,2,1,3,1,2,3};
		Map<Integer,Integer>map= Arrays.asList(array).stream().collect(Collectors.toMap(i->i, i->1, (a,b)->a+b)
				);
		map.forEach((k, v) -> System.out.println(k + " -> "+ v));

	}

}

package com.example.java8inaction.ch03;

import java.util.*;
import com.example.java8inaction.Apple;
import static java.util.Comparator.comparing;

public class Sorting {
	public static void main(String[] args) {
		test04();
	}

	public static List<Apple> inventory = Arrays.asList(new Apple(80, "green"), new Apple(155, "green"),
			new Apple(120, "red"));

	public static void test01() {
		inventory.sort(new AppleComparator());
		System.out.println(inventory);
	}

	public static void test02() {
		inventory.sort(new Comparator<Apple>() {
			public int compare(Apple a1, Apple a2) {
				return a1.getWeight().compareTo(a2.getWeight());
			}
		});
		System.out.println(inventory);
	}

	public static void test03() {
		inventory.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));
		System.out.println(inventory);
	}

	public static void test04() {
		inventory.sort(comparing(Apple::getWeight));
		System.out.println(inventory);
	}

	// 重量比较器
	static class AppleComparator implements Comparator<Apple> {
		public int compare(Apple a1, Apple a2) {
			return a1.getWeight().compareTo(a2.getWeight());
		}
	}
}

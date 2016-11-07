package com.cometoin.temp;

import java.util.Date;

public class Fib {
	// inside java /
	public static void main(String[] args) {
//		int i = Integer.valueOf(args[0]);
		System.out.println(new Date());
		int i = Integer.valueOf(47);
		System.out.println(fib(i));
		System.out.println(new Date());
	}

	private static int fib(int i) {
		if (i <= 0) {
			return 0;
		} else if (i == 1) {
			return 1;
		} else {
			return (fib(i - 1) + fib(i - 2));
		}
	}
}

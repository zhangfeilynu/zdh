package com.htf.zdh.utils;

public class MathUtil {

	public static void main(String[] args) {

		// double a = 9487.65;
		// double b = 478712.55;
		// System.out.println(a / b);
		System.out.print(formatDouble(10.666666,2));

	}

	public static String formatDoubleLeafFour(double d) {
		return String.format("%.4f", d);
	}

	public static String formatDouble(double d,Integer digit) {//数d保留digit位小数
		return String.format("%."+digit+"f", d);
	}

	public static String formatDoubleLeafN(double dividend, double divisor) {// dividend 被除数 divisor 除数
		if (divisor == 0L)
			return "除数不能为0";
		double result = dividend / divisor;
		return String.format("%.4f", result);
	}
}
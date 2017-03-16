package cn.pku.coolyr;

import java.util.Scanner;

public class Test
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		//
		// char [] chars = {'a','b','c','d','e'};
		// System.out.println(chars);
		// String s = "adbdef";
		// s.trim();

		// System.out.println('a' == 'a');

		// 输入一组数据并输出#######################################
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		sc.nextLine();
		System.out.printf("%s\n", str);

		// 输入多组数据并输出########################################
		Scanner scAll = new Scanner(System.in);
		String s;
		while (scAll.hasNextLine())
		{
			s = scAll.nextLine();
			System.out.printf("%s\n", str.trim());
		}

		// ################################################
		System.out.println("请输入若干个数,每输入一个数用回车确认");
		System.out.println("最后输入一个非数字结束输入操作");
		Scanner reader = new Scanner(System.in);
		double sum = 0;
		int m = 0;
		while (reader.hasNextDouble())
		{
			double x = reader.nextDouble();
			m = m + 1;
			sum = sum + x;
		}
		System.out.printf("%d个数的和为%f\n", m, sum);
		System.out.printf("%d个数的平均值是%f\n", m, sum / m);
	}
}

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

		// ����һ�����ݲ����#######################################
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		sc.nextLine();
		System.out.printf("%s\n", str);

		// ����������ݲ����########################################
		Scanner scAll = new Scanner(System.in);
		String s;
		while (scAll.hasNextLine())
		{
			s = scAll.nextLine();
			System.out.printf("%s\n", str.trim());
		}

		// ################################################
		System.out.println("���������ɸ���,ÿ����һ�����ûس�ȷ��");
		System.out.println("�������һ�������ֽ����������");
		Scanner reader = new Scanner(System.in);
		double sum = 0;
		int m = 0;
		while (reader.hasNextDouble())
		{
			double x = reader.nextDouble();
			m = m + 1;
			sum = sum + x;
		}
		System.out.printf("%d�����ĺ�Ϊ%f\n", m, sum);
		System.out.printf("%d������ƽ��ֵ��%f\n", m, sum / m);
	}
}

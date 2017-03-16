package cn.pku.coolyr;

import java.util.Stack;

public class ArrayProcessor
{

	// #2.3寻找和为定值的多个数
	// NOTE:输入两个数n和sum,要求从数列1,2,3,...,n中随意取出几个数，是的他们的和等于sum，
	// 找出所有的组合出来。
	// 《解法一 - n问题转换为n-1问题》
	// 类似于0-1背包的思想
	Stack<Integer> stack = new Stack<Integer>();

	void SumOfkNumber(int sum, int n)
	{
		// 递归出口
		if (n <= 0 || sum <= 0)
			return;
		// 找到一个输出的结果
		if (sum == n)
		{
			for (int i : stack)
				System.out.print(i);
			System.out.println(n);
		}
		// 分解问题-递归
		// list : 当做
		stack.push(n); // 类似0-1背包问题
		SumOfkNumber(sum - n, n - 1);// 放入n，前n-1个数填满sum-n
		stack.pop();
		SumOfkNumber(sum, n - 1);// 不放n，前n-1个数填满sum
	}

	// #2.6奇偶数排序
	// 给定一个整数数组，调整该数组中的数的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
	// 要求时间复杂度为O(n)

	// 判断是否为奇数
	boolean isOddNumber(int data)
	{
		return (data & 1) == 1;
	}

	void OddEventSort(int A[])
	{
		if (A == null || A.length == 0)
			return;
		int left = 0;
		int right = A.length - 1;
		while (left < right)
		{
			while (isOddNumber(A[left]))
				// 如果是奇数不停右移
				left++;
			while (!isOddNumber(A[right]))
				// 如果是偶数不停左移
				right--;
			if (right > left)// 一定要判断下！！[1,1,2,2]像这种情况
			{
				int temp = A[left];
				A[left] = A[right];
				A[right] = temp;
			}
		}
	}

	// #2.7荷兰国旗 - sortColor
	// Given an array with n objects colored red, white or blue, sort them so
	// that
	// objects of the same color are adjacent, with the colors in the order red,
	// white
	// and blue.
	// Here, we will use the integers 0, 1, and 2 to represent the color red,
	// white, and blue respectively.

	void sortColors(int[] nums)
	{

		int red = -1; // 永远指向当前最后一个red元素位置
		int blue = nums.length; // 永远指向当前最前一个blue元素的位置
		// int[] nums = {0,1,2,1,1,2,0,2,1,0};
		for (int i = 0; i < blue;)
		{
			switch (nums[i])
			{
				case 0:
					if (i == red)
					{
						++i;
						break;
					}
					swap(nums, ++red, i);
					break;
				case 1:
					++i;
					break;
				case 2:
					swap(nums, --blue, i);
					break;
			}
		}
	}

	public static void swap(int[] a, int i, int j)
	{
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	// ###########################################################################
	public static void main(String[] args)
	{
		ArrayProcessor arrayProcessor = new ArrayProcessor();
		// int n = 10;
		// int sum = 6;
		// arrayProcessor.SumOfkNumber(sum, n);

		// int A[] =
		// { 2, 8, 7, 1, 3, 5, 6, 4 };
		// arrayProcessor.OddEventSort(A);
		// for (int i : A)
		// System.out.print(i);

		int[] nums =
		{ 0, 1, 2, 1, 1, 2, 0, 2, 1, 0 };
		arrayProcessor.sortColors(nums);
		for (int num : nums)
			System.out.print(num);
	}
}

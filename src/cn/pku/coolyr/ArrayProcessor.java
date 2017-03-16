package cn.pku.coolyr;

import java.util.Stack;

public class ArrayProcessor
{

	// #2.3Ѱ�Һ�Ϊ��ֵ�Ķ����
	// NOTE:����������n��sum,Ҫ�������1,2,3,...,n������ȡ�����������ǵ����ǵĺ͵���sum��
	// �ҳ����е���ϳ�����
	// ���ⷨһ - n����ת��Ϊn-1���⡷
	// ������0-1������˼��
	Stack<Integer> stack = new Stack<Integer>();

	void SumOfkNumber(int sum, int n)
	{
		// �ݹ����
		if (n <= 0 || sum <= 0)
			return;
		// �ҵ�һ������Ľ��
		if (sum == n)
		{
			for (int i : stack)
				System.out.print(i);
			System.out.println(n);
		}
		// �ֽ�����-�ݹ�
		// list : ����
		stack.push(n); // ����0-1��������
		SumOfkNumber(sum - n, n - 1);// ����n��ǰn-1��������sum-n
		stack.pop();
		SumOfkNumber(sum, n - 1);// ����n��ǰn-1��������sum
	}

	// #2.6��ż������
	// ����һ���������飬�����������е�����˳��ʹ����������λ�������ǰ�벿�֣�����ż��λ������ĺ�벿�֡�
	// Ҫ��ʱ�临�Ӷ�ΪO(n)

	// �ж��Ƿ�Ϊ����
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
				// �����������ͣ����
				left++;
			while (!isOddNumber(A[right]))
				// �����ż����ͣ����
				right--;
			if (right > left)// һ��Ҫ�ж��£���[1,1,2,2]���������
			{
				int temp = A[left];
				A[left] = A[right];
				A[right] = temp;
			}
		}
	}

	// #2.7�������� - sortColor
	// Given an array with n objects colored red, white or blue, sort them so
	// that
	// objects of the same color are adjacent, with the colors in the order red,
	// white
	// and blue.
	// Here, we will use the integers 0, 1, and 2 to represent the color red,
	// white, and blue respectively.

	void sortColors(int[] nums)
	{

		int red = -1; // ��Զָ��ǰ���һ��redԪ��λ��
		int blue = nums.length; // ��Զָ��ǰ��ǰһ��blueԪ�ص�λ��
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

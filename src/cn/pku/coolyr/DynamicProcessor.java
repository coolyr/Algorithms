package cn.pku.coolyr;

import java.lang.Math;

public class DynamicProcessor
{
	// #5.1��������˻�������
	double MaxproductSubarray(double[] A)
	{
		double maxEnd = A[0];
		double minend = A[0];
		double maxResult = A[0];
		for (int i = 1; i < A.length; i++)
		{
			double end1 = maxEnd * A[i];
			double end2 = minend * A[i];
			maxEnd = Math.max(Math.max(end1, end2), A[i]);
			minend = Math.min(Math.min(end1, end2), A[i]);
			maxResult = Math.max(maxResult, maxEnd);
		}
		return maxResult;
	}

	double MaxproductSubarray_2(double[] A)
	{
		double maxEnd = A[0];
		double maxResult = A[0];
		for (int i = 1; i < A.length; i++)
		{
			if (A[i] > 0)
			{
				if (maxEnd > 0)
				{
					maxEnd = maxEnd * A[i];
					maxResult = Math.max(maxEnd, maxResult);
				} else
				{
					maxEnd = A[i];
					maxResult = Math.max(maxResult, A[i]);
				}
			} else if (A[i] == 0)
			{
				maxEnd = A[i];
				maxResult = Math.max(maxResult, A[i]);
			} else
			{
				if (maxEnd <= 0)
				{
					maxEnd = maxEnd * A[i];
					maxResult = Math.max(maxResult, maxEnd);
				} else
				{
					maxEnd = A[i];
					maxResult = Math.max(maxResult, A[i]);
				}
			}

		}
		return maxResult;
	}

	// #5.2�༭����
	// �ȸ����༭����Ķ��壺��A��B��2���ַ�����Ҫ�����ٵ��ַ��������ַ���Aת��Ϊ�ַ���B��������˵���ַ�����������
	// (1)ɾ��һ���ַ�(delete)��
	// (2)����һ���ַ�(insert)��
	// (3)��һ���ַ���Ϊ��һ���ַ�(substitute)��
	// �༭����������漸�����ʣ�
	// �����ַ�������С�༭���������������ַ����ĳ��Ȳ
	// �����ַ��������༭�������������ַ����нϳ��ַ����ĳ��ȣ�
	// �����ַ����ı༭��������ĳ�Ҫ�����������ַ�����ͬ��
	// ��������ַ����ȳ����༭����������Ǻ�������(Hamming distance)��
	// �༭�����������ǲ���ʽ����d(a, c) �� d(a, b) + d(b, c)��
	// ��������ַ�������ͬ��ǰ׺���׺����ȥ����ͬ��ǰ׺���׺�Ա༭����û��Ӱ�죬����λ�ò�������ɾ����

	// ��ʽ��
	// 1,dp[i][j] = dp[i-1][j-1] (A[i] = B[j])
	// 2, A[i] <> B[j], ����������ʽ�� min
	// <1> dp[i][j] = dp[i-1][j] + 1 (del)
	// <2> dp[i][j] = dp[i][j-1] + 1 (insert)
	// <3> dp[i][j] = dp[i-1][j-1] + 1 (replace)
	// ���ǽ���һ������Ĺ�ʽ��ǰ�����ǳ�ʼ�����ֱ��ʾ�ַ���b��aΪ��ʱ��Ӧ�ı༭������㡣���ַ���bΪ��ʱ�����ǽ�a��Ϊbֻ��Ҫ��ͣ��ɾ���ַ����ɣ���֮��ͣ������ַ���
	// ��ʼ�������d�����һ�к�һ��֮�����ǾͿ��԰��յ����й�ʽȥ��������ʣ��Ԫ�ء���(i,j)��Ԫ���ڼ����ʱ�������ں������ڵ�����λ��
	// (i-1,j-1)��(i,j-1)��(i-1,j)��
	// ��ʽ������ṹ��LCS��DTW���ǳ���Ψһ���ڵ��ѵ���ǵ�����һ��λ�ö�Ӧɾ����������һ��λ�ö�Ӧ����������ɹ�ʽ���Կ�����(i-1,j)��Ӧɾ��������(i,j-1)��Ӧ���������
	// ����������⣬���ںķ���di-1,j���������ַ���a(1,i-1)ת������b(1,j)�����ڽ�a(1,i)ת����b(1,j)ʱ�����ǿ���ֱ��ɾ���ַ�a(i)��������a(1,i-1)ת����b(1,j)��
	// �Ӷ�dij�͵���di-1,j+1��ͬ�����ںķ���di,j-1���������ַ���a(1,i)ת������b(1,j-1)�����ڽ�a(1,i)ת����b(1,j)ʱ�����ǿ��Խ�b(j)��ӵ�a(1,i)ĩβ
	// ����ʱa(1,i)��ת����b(1,j-1)������b(1,j)��
	int EditDistance(char[] s, char[] t)
	{
		int sLen = s.length;
		int tLen = t.length;
		int[][] dp = new int[sLen + 1][tLen + 1]; // Ĭ�ϴ�1��ʼ
		int i, j;
		// �߽�dp[i][0] = i (del); dp[0][j] = j (ins)
		for (i = 1; i <= sLen; i++)
			dp[i][0] = i;
		for (j = 1; j <= tLen; j++)
			dp[0][j] = j;

		for (i = 1; i <= sLen; i++)
		{
			for (j = 1; j <= tLen; j++)
			{
				if (s[i - 1] == t[j - 1]) // ��i���ַ�λ��[i-1]
					dp[i][j] = dp[i - 1][j - 1];
				else
					dp[i][j] = 1 + Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]);
			}
		}
		return dp[sLen][tLen];
	}

	// #5.4�����ַ���
	// ���������ַ���s1, s2, t; �ж�t�Ƿ���ǰ����s1��s2��������Ҳ��ı�s1��s2�и����ַ�ԭ�е����˳��
	// ��dp[i][j]��ʾt[i+j-1]�Ƿ���s1[0...i-1]��s2[...j-1]���ַ���ɣ����У�
	// <1>���s1�ĵ�ǰ�ַ�����s[i-1]������t�ĵ�ǰ�ַ�����t[i+j-1]���� dp[i-1][j] =
	// true;��ô����ȡs1�ĵ�ǰ�ַ�������s2�������dp[i][j] = true;
	// <2>���s2�ĵ�ǰ�ַ�����s[j-1]������t�ĵ�ǰ�ַ�����t[i+j-1]���� dp[i][j-1] =
	// true;��ô����ȡs2�ĵ�ǰ�ַ�������s1�������dp[i][j] = true;
	// <3>�������dp[i][j] = false;
	boolean IsIterLeave(char[] s1, char[] s2, char[] t)
	{
		int m = s1.length;
		int n = s2.length;
		int s = t.length;
		// ������Ȳ�һ�£���s3��������s1��s2�������
		if (m + n != s)
			return false;
		boolean dp[][] = new boolean[m + 1][n + 1];
		// �ڳ�ʼ���߽��ʱ��������Ϊ�մ������ɿմ���ɣ����dp[0][0]= true
		dp[0][0] = true;
		for (int i = 0; i < m + 1; i++)
		{
			for (int j = 0; j < n + 1; j++)
			{
				if (dp[i][j] || (i - 1 >= 0 && dp[i - 1][j] == true && s1[i - 1] == t[i + j - 1]) || (j - 1 >= 0 && dp[i][j - 1] == true && s2[j - 1] == t[i + j - 1]))
				{                                                                                                                                                                     
					dp[i][j] = true;
				} else
				{
					dp[i][j] = false;
				}

			}
		}
		return dp[m][n];
	}

	public static void main(String[] args)
	{
		DynamicProcessor dynamicProcessor = new DynamicProcessor();

		// double[] A =
		// { -2.5, 4, 0, 3, 0.5, 8, -1 };
		// double maxResult = dynamicProcessor.MaxproductSubarray(A);
		// double maxresult2 = dynamicProcessor.MaxproductSubarray_2(A);
		// System.out.print(maxResult + " --- " + maxresult2);

		// char[] s1 = {'a','a','b','c','c'};
		// char[] s2 = {'d', 'b', 'b', 'c', 'a'};
		// char[] t = {'a','a','d','b','b','c','b','c','a','c'};
		// boolean b = dynamicProcessor.IsIterLeave(s1, s2, t);
		// System.out.println(b);

		char[] s =
		{ 'A', 'L', 'G', 'O', 'R', 'I', 'T', 'H', 'M' };
		char[] t =
		{ 'A', 'L', 'T', 'R', 'U', 'I', 'S', 'T', 'T', 'C' };
		// char[] s =
		// { 'A', 'L', 'M' };
		// char[] t =
		// { 'A', 'L', 'T', 'R', 'U', 'I', 'S', 'T', 'M' };
		int editDistance = dynamicProcessor.EditDistance(s, t);
		System.out.println(editDistance);

	}

}

package cn.pku.coolyr;

import java.lang.Math;

public class DynamicProcessor
{
	// #5.1最大连续乘积子数组
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

	// #5.2编辑距离
	// 先给出编辑距离的定义：设A和B是2个字符串，要用最少的字符操作将字符串A转换为字符串B。这里所说的字符操作包括：
	// (1)删除一个字符(delete)；
	// (2)插入一个字符(insert)；
	// (3)将一个字符改为另一个字符(substitute)。
	// 编辑距离具有下面几个性质：
	// 两个字符串的最小编辑距离至少是两个字符串的长度差；
	// 两个字符串的最大编辑距离至多是两字符串中较长字符串的长度；
	// 两个字符串的编辑距离是零的充要条件是两个字符串相同；
	// 如果两个字符串等长，编辑距离的上限是汉明距离(Hamming distance)；
	// 编辑距离满足三角不等式，即d(a, c) ≤ d(a, b) + d(b, c)；
	// 如果两个字符串有相同的前缀或后缀，则去掉相同的前缀或后缀对编辑距离没有影响，其他位置不能随意删除。

	// 公式：
	// 1,dp[i][j] = dp[i-1][j-1] (A[i] = B[j])
	// 2, A[i] <> B[j], 求下面三公式的 min
	// <1> dp[i][j] = dp[i-1][j] + 1 (del)
	// <2> dp[i][j] = dp[i][j-1] + 1 (insert)
	// <3> dp[i][j] = dp[i-1][j-1] + 1 (replace)
	// 我们解释一下上面的公式，前两行是初始化，分别表示字符串b和a为空时对应的编辑距离计算。当字符串b为空时，我们将a变为b只需要不停地删除字符即可，反之则不停地添加字符。
	// 初始化完矩阵d的最初一行和一列之后我们就可以按照第三行公式去计算矩阵的剩余元素。第(i,j)个元素在计算的时候依赖于和它相邻的三个位置
	// (i-1,j-1)、(i,j-1)和(i-1,j)。
	// 公式的整体结构和LCS，DTW都非常像，唯一存在的难点就是到底哪一个位置对应删除操作，哪一个位置对应插入操作。由公式可以看出，(i-1,j)对应删除操作，(i,j-1)对应插入操作。
	// 可以这样理解，现在耗费了di-1,j步操作将字符串a(1,i-1)转换成了b(1,j)，则在将a(1,i)转换成b(1,j)时，我们可以直接删掉字符a(i)，问题变成a(1,i-1)转换成b(1,j)，
	// 从而dij就等于di-1,j+1。同理，现在耗费了di,j-1步操作将字符串a(1,i)转换成了b(1,j-1)，则在将a(1,i)转换成b(1,j)时，我们可以将b(j)添加到a(1,i)末尾
	// （此时a(1,i)已转换成b(1,j-1)）构成b(1,j)。
	int EditDistance(char[] s, char[] t)
	{
		int sLen = s.length;
		int tLen = t.length;
		int[][] dp = new int[sLen + 1][tLen + 1]; // 默认从1开始
		int i, j;
		// 边界dp[i][0] = i (del); dp[0][j] = j (ins)
		for (i = 1; i <= sLen; i++)
			dp[i][0] = i;
		for (j = 1; j <= tLen; j++)
			dp[0][j] = j;

		for (i = 1; i <= sLen; i++)
		{
			for (j = 1; j <= tLen; j++)
			{
				if (s[i - 1] == t[j - 1]) // 第i个字符位置[i-1]
					dp[i][j] = dp[i - 1][j - 1];
				else
					dp[i][j] = 1 + Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]);
			}
		}
		return dp[sLen][tLen];
	}

	// #5.4交替字符串
	// 输入三个字符串s1, s2, t; 判断t是否由前两个s1和s2交错而成且不改变s1和s2中各个字符原有的相对顺序。
	// 令dp[i][j]表示t[i+j-1]是否由s1[0...i-1]和s2[...j-1]的字符组成，则有：
	// <1>如果s1的当前字符（即s[i-1]）等于t的当前字符（即t[i+j-1]）且 dp[i-1][j] =
	// true;那么可以取s1的当前字符而忽略s2的情况，dp[i][j] = true;
	// <2>如果s2的当前字符（即s[j-1]）等于t的当前字符（即t[i+j-1]）且 dp[i][j-1] =
	// true;那么可以取s2的当前字符而忽略s1的情况，dp[i][j] = true;
	// <3>其它情况dp[i][j] = false;
	boolean IsIterLeave(char[] s1, char[] s2, char[] t)
	{
		int m = s1.length;
		int n = s2.length;
		int s = t.length;
		// 如果长度不一致，则s3不可能右s1和s2交错组成
		if (m + n != s)
			return false;
		boolean dp[][] = new boolean[m + 1][n + 1];
		// 在初始化边界的时候，我们认为空串可以由空串组成，因此dp[0][0]= true
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

package cn.pku.coolyr;

public class SearchProcessor
{

	// #4.2 行列递增序列的查找 《杨氏矩阵》
	// <解法一 - 分治法>
	// <解法二 - 定位法>
	// NOTE:
	// 首先定位到矩阵中的最右上角，如果这个元素比key大，就往左走；如果小，就往下走。
	// O(m+n)
	boolean YoungMartrixSearch(int[][] A, int key)
	{
		int COL = A[0].length;
		int ROW = A.length;
		int i = 0;
		int j = COL - 1;
		int var = A[i][j];
		while (true)
		{
			if (var == key)
				return true;
			else if (var < key && i < (ROW - 1))
				var = A[++i][j];
			else if (var > key && j > 0)
				var = A[i][--j];
			else
				return false;
		}
	}

	// #4.3出现次数超过一半的数字
	// 题目：数组中有一个数字出现的次数超过了数组长度的一半，找出这个数字。
	// <解法四 - 每次删除2个不同的数>
	// 更进一步，考虑到这个问题本身的特殊性，我们可以在遍历数组的时候保存两个值：一个candidate，用来保存数组
	// 中遍历到的某个数字；一个nTimes，表示当前数字的出现次数，其中，nTimes初始化为1。当我们遍历到数组中下一个数字的时候：
	// <1>如果下一个数字与之前candidate保存的数字相同，则nTimes加1；
	// <2>如果下一个数字与之前candidate保存的数字不同，则nTimes减1；
	// <3>每当出现次数nTimes变为0后，用candidate保存下一个数字，并把nTimes重新设为1。 直到遍历完数组中的所有数字为止。
	//
	// 举个例子，假定数组为{0, 1, 2, 1, 1}，按照上述思路执行的步骤如下：
	// 1.开始时，candidate保存数字0，nTimes初始化为1；
	// 2.然后遍历到数字1，与数字0不同，则nTimes减1变为0；
	// 3.因为nTimes变为了0，故candidate保存下一个遍历到的数字2，且nTimes被重新设为1；
	// 4.继续遍历到第4个数字1，与之前candidate保存的数字2不同，故nTimes减1变为0；
	// 5.因nTimes再次被变为了0，故我们让candidate保存下一个遍历到的数字1，且nTimes被重新设为1。最后返回的就是最后一次把nTimes设为1的数字1。

	int FindOneNumber(int[] A)
	{
		int length = A.length;
		int candidate = A[0];
		int nTimes = 1;
		for (int i = 1; i < length; i++)
		{
			if (nTimes == 0)
			{
				candidate = A[i];
				nTimes = 1;
			} else
			{
				if (candidate == A[i])
					nTimes++;
				else
					nTimes--;
			}
		}
		return candidate;
	}

	// #4.4字符串匹配
	// 《方法一 - 蛮力》
	int violentMatch(char[] S, char[] P)
	{
		int sLen = S.length;
		int pLen = P.length;
		int i = 0;
		int j = 0;
		while (i < sLen && j < pLen)
		{
			if (S[i] == P[j])
			{
				i++;
				j++;
			} else
			{
				i = i - j + 1;
				j = 0;
			}
		}
		if (j == pLen)
			return (i - j);
		else
			return -1;
	}

	// KMP - 模式匹配
	// 理解了kmp算法的基本原理，下一步就是要获得字符串f每一个位置的最大公共长度。这个最大公共长度在算法导论里面被记为next数组。
	// 在这里要注意一点，next数组表示的是长度，下标从1开始；但是在遍历原字符串时，下标还是从0开始。假设我们现在已经求得next[1]、
	// next[2]、……next[i]，分别表示长度为1到i的字符串的前缀和后缀最大公共长度，现在要求next[i+1]。由上图我们可以看到，如果
	// 位置i和位置next[i]处的两个字符相同（下标从零开始），则next[i+1]等于next[i]加1。如果两个位置的字符不相同，我们可以将长
	// 度为next[i]的字符串继续分割，获得其最大公共长度next[next[i]]，然后再和位置i的字符比较。这是因为长度为next[i]前缀和后缀都
	// 可以分割成上部的构造，如果位置next[next[i]]和位置i的字符相同，则next[i+1]就等于next[next[i]]加1。如果不相等，就可以
	// 继续分割长度为next[next[i]]的字符串，直到字符串长度为0为止
	int[] getNext(char[] p)
	{
		int pLen = p.length;
		int next[] = new int[pLen];
		next[0] = -1;
		int k = -1; // k = next[i]
		int i = 0;
		while (i < pLen - 1)
		{
			if (k == -1 || p[i] == p[k])
			{
				++k;
				++i;
				next[i] = k;
			} else
				k = next[k];
		}

		return next;
	}

	// 算法流程：S匹配到i,P匹配到j
	// <1>如果j == -1或者当前字符匹配成功(即s[i] == p[j]),都令i++, j++,继续匹配下一个字符.
	// <2>如果j != -1且当前字符匹配失败(即s[i] != p[j]),都令i不变, j=next[j].
	int searchKMP(char[] S, char[] P, int next[])
	{
		int i = 0;
		int j = 0;
		int sLen = S.length;
		int pLen = P.length;
		while (i < sLen && j < pLen)
		{
			// 如果j==-1或者当前字符匹配成功(即S[i]==P[j])，都令 i++, j++
			if (j == -1 || S[i] == P[j])
			{
				i++;
				j++;
			} else
			{
				// 如果j != -1 且当前字符匹配失败(s[i] != p[j]),都令i不变，j = next[j]
				j = next[j];
			}
		}
		if (j == pLen)
			return i - j;
		else
			return -1;
	}

	// #######################################################################################
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		SearchProcessor searchProcessor = new SearchProcessor();
		char[] S =
		{ 'B', 'B', 'C', 'A', 'B', 'C', 'A', 'D', 'C', 'D', 'A' };
		char[] P =
		{ 'A', 'B', 'C', 'D', 'A', 'B', 'D' };
		int pos = searchProcessor.violentMatch(S, P);
		System.out.println(">>>>>" + pos);

		int next[] = searchProcessor.getNext(P);
		for (int n : next)
			System.out.println(n);

		int position = searchProcessor.searchKMP(S, P, next);
		System.out.println(">>>>>" + position);
	}

}

package cn.pku.coolyr;

public class SearchProcessor
{

	// #4.2 ���е������еĲ��� �����Ͼ���
	// <�ⷨһ - ���η�>
	// <�ⷨ�� - ��λ��>
	// NOTE:
	// ���ȶ�λ�������е������Ͻǣ�������Ԫ�ر�key�󣬾������ߣ����С���������ߡ�
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

	// #4.3���ִ�������һ�������
	// ��Ŀ����������һ�����ֳ��ֵĴ������������鳤�ȵ�һ�룬�ҳ�������֡�
	// <�ⷨ�� - ÿ��ɾ��2����ͬ����>
	// ����һ�������ǵ�������Ȿ��������ԣ����ǿ����ڱ��������ʱ�򱣴�����ֵ��һ��candidate��������������
	// �б�������ĳ�����֣�һ��nTimes����ʾ��ǰ���ֵĳ��ִ��������У�nTimes��ʼ��Ϊ1�������Ǳ�������������һ�����ֵ�ʱ��
	// <1>�����һ��������֮ǰcandidate�����������ͬ����nTimes��1��
	// <2>�����һ��������֮ǰcandidate��������ֲ�ͬ����nTimes��1��
	// <3>ÿ�����ִ���nTimes��Ϊ0����candidate������һ�����֣�����nTimes������Ϊ1�� ֱ�������������е���������Ϊֹ��
	//
	// �ٸ����ӣ��ٶ�����Ϊ{0, 1, 2, 1, 1}����������˼·ִ�еĲ������£�
	// 1.��ʼʱ��candidate��������0��nTimes��ʼ��Ϊ1��
	// 2.Ȼ�����������1��������0��ͬ����nTimes��1��Ϊ0��
	// 3.��ΪnTimes��Ϊ��0����candidate������һ��������������2����nTimes��������Ϊ1��
	// 4.������������4������1����֮ǰcandidate���������2��ͬ����nTimes��1��Ϊ0��
	// 5.��nTimes�ٴα���Ϊ��0����������candidate������һ��������������1����nTimes��������Ϊ1����󷵻صľ������һ�ΰ�nTimes��Ϊ1������1��

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

	// #4.4�ַ���ƥ��
	// ������һ - ������
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

	// KMP - ģʽƥ��
	// �����kmp�㷨�Ļ���ԭ����һ������Ҫ����ַ���fÿһ��λ�õ���󹫹����ȡ������󹫹��������㷨�������汻��Ϊnext���顣
	// ������Ҫע��һ�㣬next�����ʾ���ǳ��ȣ��±��1��ʼ�������ڱ���ԭ�ַ���ʱ���±껹�Ǵ�0��ʼ���������������Ѿ����next[1]��
	// next[2]������next[i]���ֱ��ʾ����Ϊ1��i���ַ�����ǰ׺�ͺ�׺��󹫹����ȣ�����Ҫ��next[i+1]������ͼ���ǿ��Կ��������
	// λ��i��λ��next[i]���������ַ���ͬ���±���㿪ʼ������next[i+1]����next[i]��1���������λ�õ��ַ�����ͬ�����ǿ��Խ���
	// ��Ϊnext[i]���ַ��������ָ�������󹫹�����next[next[i]]��Ȼ���ٺ�λ��i���ַ��Ƚϡ�������Ϊ����Ϊnext[i]ǰ׺�ͺ�׺��
	// ���Էָ���ϲ��Ĺ��죬���λ��next[next[i]]��λ��i���ַ���ͬ����next[i+1]�͵���next[next[i]]��1���������ȣ��Ϳ���
	// �����ָ��Ϊnext[next[i]]���ַ�����ֱ���ַ�������Ϊ0Ϊֹ
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

	// �㷨���̣�Sƥ�䵽i,Pƥ�䵽j
	// <1>���j == -1���ߵ�ǰ�ַ�ƥ��ɹ�(��s[i] == p[j]),����i++, j++,����ƥ����һ���ַ�.
	// <2>���j != -1�ҵ�ǰ�ַ�ƥ��ʧ��(��s[i] != p[j]),����i����, j=next[j].
	int searchKMP(char[] S, char[] P, int next[])
	{
		int i = 0;
		int j = 0;
		int sLen = S.length;
		int pLen = P.length;
		while (i < sLen && j < pLen)
		{
			// ���j==-1���ߵ�ǰ�ַ�ƥ��ɹ�(��S[i]==P[j])������ i++, j++
			if (j == -1 || S[i] == P[j])
			{
				i++;
				j++;
			} else
			{
				// ���j != -1 �ҵ�ǰ�ַ�ƥ��ʧ��(s[i] != p[j]),����i���䣬j = next[j]
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

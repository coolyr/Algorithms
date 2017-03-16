package cn.pku.coolyr;

/**
 * 
 * @author Administrator 为了算法好理解有大部分的数组中[0]的位置不存储元素,是从[1]开始存储的
 */
public class Dynamic
{

	Object[] not01Bag(int N, int B, int W[], int V[])
	{
		int maxValue = 0;
		int[][] F = new int[N + 1][B + 1]; // 从[1]开始存
		int[][] I = new int[N + 1][B + 1]; // 记录所用到的最大标号
		// 初始化边界条件
		for (int b = 1; b <= B; b++)
			F[1][b] = (b / W[1]) * V[1];
		// 开始迭代计算
		for (int n = 2; n <= N; n++)
		{
			for (int b = 1; b <= B; b++)
			{
				if (b >= W[n])
					// F[n][b] = Math.max(F[n-1][b], F[n][b-W[n]] + V[n]);
					if (F[n - 1][b] > F[n][b - W[n]] + V[n])
					{
						F[n][b] = F[n - 1][b];
						I[n][b] = I[n - 1][b];
					} else
					{
						F[n][b] = F[n][b - W[n]] + V[n];
						I[n][b] = n;
					}
				else
				{
					F[n][b] = F[n - 1][b];
					I[n][b] = I[n - 1][b];
				}
			}
		}
		Object[] ansObjects = new Object[3];
		maxValue = F[N][B];
		ansObjects[0] = maxValue;
		ansObjects[1] = F;
		ansObjects[2] = I;
		return ansObjects;
	}

	int[] not01BagTrackSolution(int[][] I, int[] W, int N, int B)
	{
		int X[] = new int[N + 1];
		int b = B;
		int n = N;
		while (I[n][b] != 0) // 当前的[n,b]中最大种类非0 -> 还有
		{
			n = I[n][b]; // 取出[n,b]所装的最大类别
			X[n] = 1; // 相应类别计数加1
			b = b - W[n]; // 总重量减去该类别的W[n]
			while (I[n][b] == n) // 如果包含多个n类
			{
				b = b - W[n];
				X[n]++;
			}
		}
		return X;
	}

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		Dynamic dynamicAlgorithms = new Dynamic();
		int V[] =
		{ 0, 1, 3, 5, 9 };
		int W[] =
		{ 0, 2, 3, 4, 7 };
		int B = 10;
		// int N = 4;
		int N = V.length - 1;

		Object[] ans = dynamicAlgorithms.not01Bag(N, B, W, V);
		System.out.println(ans[0]);
		int[][] F = (int[][]) ans[1];
		int[][] I = (int[][]) ans[2];
		for (int n = 1; n <= N; n++)
			for (int b = 1; b <= B; b++)
				System.out.println("<" + n + "," + b + "> : " + F[n][b] + " <--> " + I[n][b]);

		int[] ks = dynamicAlgorithms.not01BagTrackSolution(I, W, N, B);
		for (int i : ks)
		{
			System.out.print(i);
		}
	}
}

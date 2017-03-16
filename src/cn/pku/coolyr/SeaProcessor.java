package cn.pku.coolyr;

public class SeaProcessor
{
	private int BYTESIZE = 8;

	void SetBit(char[] p, int n)
	{
		int i;
		for (i = 0; i < (n / BYTESIZE);)
			i++;
		// System.out.println(n + "  " + i);
		p[i] = (char) (p[i] | (1 << (n % BYTESIZE)));// 将该Bit位赋值1
	}

	void BitMapSort(int[] num)
	{
		// 为了简单起见，我们不考虑负数
		// BufferLen这个值是根据待排序的数据中最大值确定的
		// 待排序中的最大值是14，因此只需要2个Bytes(16个Bit)就可以了。
		int maxNum = getArrayMax(num);
		int bufferLen = maxNum / 8 + 1;
		// System.out.println(maxNum + "   " + bufferLen);
		char[] pBuffer = new char[bufferLen];

		// 要将所有的Bit位置为0，否则结果不可预知。
		// memset(pBuffer,0,BufferLen);

		for (int i = 0; i < num.length; i++)
			// 首先将相应Bit位上置为1
			SetBit(pBuffer, num[i]);

		// 输出排序结果
		for (int i = 0; i < bufferLen; i++)// 每次处理一个字节(Byte)
		{
			for (int j = 0; j < BYTESIZE; j++)// 处理该字节中的每个Bit位
			{
				// 判断该位上是否是1，进行输出，这里的判断比较笨。
				// 首先得到该第j位的掩码（0x01<<j），将内存区中的位和此掩码作与操作。最后判断掩码是否和处理后的 结果相同
	 			if ((pBuffer[i] & (1 << j)) == (1 << j))
					System.out.printf("%d ", i * BYTESIZE + j);
			}
		}
	}

	private int getArrayMax(int[] num)
	{
		int maxNum = num[0];
		for (int i = 1; i < num.length; i++)
			if (num[i] > maxNum)
				maxNum = num[i];
		return maxNum;
	}

	public static void main(String[] args)
	{
		SeaProcessor seaProcessor = new SeaProcessor();
		// 为了简单起见，我们不考虑负数
		int num[] =
		{ 3, 5, 2, 10, 6, 12, 81, 14, 9, 100 };
		seaProcessor.BitMapSort(num);
	}
}

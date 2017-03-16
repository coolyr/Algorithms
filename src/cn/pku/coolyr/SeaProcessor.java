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
		p[i] = (char) (p[i] | (1 << (n % BYTESIZE)));// ����Bitλ��ֵ1
	}

	void BitMapSort(int[] num)
	{
		// Ϊ�˼���������ǲ����Ǹ���
		// BufferLen���ֵ�Ǹ��ݴ���������������ֵȷ����
		// �������е����ֵ��14�����ֻ��Ҫ2��Bytes(16��Bit)�Ϳ����ˡ�
		int maxNum = getArrayMax(num);
		int bufferLen = maxNum / 8 + 1;
		// System.out.println(maxNum + "   " + bufferLen);
		char[] pBuffer = new char[bufferLen];

		// Ҫ�����е�Bitλ��Ϊ0������������Ԥ֪��
		// memset(pBuffer,0,BufferLen);

		for (int i = 0; i < num.length; i++)
			// ���Ƚ���ӦBitλ����Ϊ1
			SetBit(pBuffer, num[i]);

		// ���������
		for (int i = 0; i < bufferLen; i++)// ÿ�δ���һ���ֽ�(Byte)
		{
			for (int j = 0; j < BYTESIZE; j++)// ������ֽ��е�ÿ��Bitλ
			{
				// �жϸ�λ���Ƿ���1�����������������жϱȽϱ���
				// ���ȵõ��õ�jλ�����루0x01<<j�������ڴ����е�λ�ʹ������������������ж������Ƿ�ʹ����� �����ͬ
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
		// Ϊ�˼���������ǲ����Ǹ���
		int num[] =
		{ 3, 5, 2, 10, 6, 12, 81, 14, 9, 100 };
		seaProcessor.BitMapSort(num);
	}
}

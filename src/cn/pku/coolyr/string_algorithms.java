#1.2字符串包含
NOTE:判断字符串b中出现的字符是否都在a中出现过.
《解法四：位运算》
boolean StringContain(String a, String b)
{
    char[] aChars = a.toCharArray();
    char[] bCahrs = b.toCharArray();
    int hash = 0;
    for (int i=0; i<aChars.length; ++i)
        hash |= (1 << (aChars[i] - 'A')); //存储：‘或’
    for (int j=0; j<bCahrs.length ; ++i)
    {
        if ((hash & (1 << (bChars[j] - 'A'))) == 0)//查：‘与’
            return false;
    }
    return True;
}

#1.3字符串的全排列
NOTE:输出字符串a的全排列O(n!)
《解法一：递归》
void CalAllPermutation(char[] perm, int form, int to)
{
    if (to <= 1)
        return;
    if (from == to)
    {
        for (int i=0; i<=to; i++)
            print perm[i];
        //print perm.toString();
    }
    else
    {
        for (int j=from; j<=to; j++) //从from - to中选择一个字符作为第一个字符
        {
            swap(perm[j], perm[from]);//依次选中一个字符作为第一个字符
            CalAllPermutation(perm, from+1, to);//递归剩下的字符
            swap(perm[j], perm[from]);//还原上一步选择的字符，为重新选择做准备
        }
    }
}
《字典序排列》
NOTE:O(n!)
    <1>找到排列中的最后（最右）一个升序的首位置i, x= A[i]
    <2>找到排列中的第i位右边的最后一个比A[i]大的位置j, y=A[j]
    <3>交换x和y
    <4>把第i+1位到最后的部分翻转（执行此步骤前，因为第i位是最后一个升序的位置，
	所以从i+1到n一定是降序排列的，从而执行此步骤后，从i+1到n变成升序排列）
boolean CalAllPermutation(char[] perm)
{
    int size = perm.length;
    //STEP1：找到排列中的最后（最右）一个升序的首位置i
    int i;
    for (i=size-2; (i>=0)&&(perm[i]>=perm[i+1]); i++)
        ;//空操作
    //已经找到所有排列
    if (i<0)
        return false;
    //STEP2：找到佩列中的第i位右边最后一个比A[i]大的位置j
    int j;
    for (j=size-1; (j>i)&&(perm[j]<=perm[i]); j++)
        ;//空操作
    //STEP3：交换A[i]和A[j]
    swap(perm[i], perm[j]);
    //STEP4：把i+1到最后的元素翻转
    reverse(perm, i+1, size-1);
    return true;
}

#1.4字符串转换成整数
int StrToInt(String str)
{
    if (str == null || str == "")
        return 0;
    str = str.trim();
    if (str.length() == 0)
        return 0;

    char[] myChars = str.toCharArray();
    int length = mychars.length;
    int sign = 1;//判断正负
    int i = 0;
    if (myChars[0] == '-')
        sign = -1;
        i = 1;
    else if (myChars[0] == '+')
        i = 1;
    else if (!(isDigit(myChars[0]))
        return 0;
    
    int n = 0;//存储最后的数字
    while(isDigit(myChars[i]) && i<length)
    {
        int c = myChars[i] - '0';
        if(sign>0 && ((n>Integer.MAX_VALUE/10) || (n==Integer.MAX_VALUE/10 &&  
		c>Integer.MAX_VALUE%10)))
        {
            n = Integer.MAX_VALUE;
            break;
        }
        else if (sign<0 && ((n<Integer.MIN_VALUE/10) || (n==Integer.MIN_VALUE/10 
		&&  -c<Integer.MIN_VALUE%10)))
        {
            n = Integer.MIN_VALUE;
            break;
        }
        n = n*10 + c;
        i++;
    }
    return sign>0 ? n : -n;
}


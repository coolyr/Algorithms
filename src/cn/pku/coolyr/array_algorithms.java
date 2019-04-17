#2.1寻找最小的k个数
NOTE:在大数据集合中寻找最小的k个数. O(n*log(k))
    注意：是选择第k小还是前k小
《解法一：部分排序算法-堆》

void BuildMaxHeap(int A[], int len)
{
	//len = A.length;
	for (int i=len/2; i>0; i--)  #从[n/2] ~ 1反复调整
		AdjustDown(A, i, len)
}

void AdjustDown(int A[], int k, int len)
{
	A[0] = A[k];			#A[0]暂存
	for (int i=2*k; i<=len; i*=2)
	{
		if (i<len && A[i]<A[i-1])	#选出左右孩子较大的
			i++;
		if (A[0]>=A[i])		#筛选结束
			break;		
		else
		{
			A[k] = A[i];	#将A[i]调整到双亲节点上
			k = i;			#修改k值，继续向下筛选
		}
	}
	A[k] = A[0];			#放到最终位置
}

void HeapSort(int A[], int len)
{
	BuildMaxHeap(A, len);	#建初始堆
	for (i=len; i>1; i--)	#n-1趟的交换和建堆的过程
	{
		swap(A[i], A[1]);	#输出堆顶元素(和堆底元素交换)						
								
		AdjustDown(A, 1, i-1); #整理，把剩下的i-1个元素整理成堆
	}
}

int[] HeapSelectTopK(int A[] , int k)
{
    //空间复用，使用A本身的前k个空间,A[0]不存元素
    BuildMaxHeap(A, k); 
    for(int i=k+1; k<A.length; i++)
    {
        if (A[i] < A[1])
        {
            swap(A[i], A[1]);
            AdjustDown(A, 1, k);
        }
    }
    //把topK的元素复制出来
    int topK_A = int[k];
    for (int i=0; i<k; i++)
        topK_A[i] = A[i+1];
    return topK_A;
}

《解法二：线性选择算法-快排》
NOTE:平均时间复杂度O(n)
void QuickSort(int A[], int low, int high)
{
	if (low<high)
	{
		int pivotpos = Partition(A, low, high);
		QickSort(A, low, pivotpos-1);
		QickSort(A, pivotpos+1, high);
	}
}

int Partition(int A[], int low, int high)
{
	int pivot = A[low]; #曲轴
	while (low<high)
	{
		while(low<high && A[high]>=pivot) --high;
		A[low] = A[high];
		while (low<high && A[low]<=pivot) ++low;
		A[high] = A[low];
	}
	A[low] = pivot;
	return low;
}

#我们定义数据[0]不存储元素
boolean QuickSortSelectTopK(int A[] , int k, int low, int high)
{
    if (low<high)
	{
		int pivotpos = Partition(A, low, high);
        //[... k,k+1, ...]
        if (k+1 < pivotpos)
    		QickSort(A, k, low, pivotpos-1);
        else if (k > pivotpos)
		    QickSort(A, k, pivotpos+1, high);
        else// pivotpos = k | k+1 -> 找到了前k个
            return true;//最后A中的前k个元素就是所求 
	}
}

#2.3寻找和为定值的多个数
NOTE:输入两个数n和sum,要求从数列1,2,3,...,n中随意取出几个数，是的他们的和等于
sum，
找出所有的组合出来。
《解法一 - n问题转换为n-1问题》
类似于0-1背包的思想
 <解法二 - 回溯剪枝 待完善>

Stack<Integer> stack = new Stack<Integer>();
void SumOfkNumber(int sum, int n)
{
    //递归出口
    if(n<=0 || sum<=0)
        return;
    //找到一个输出的结果
    if(sum == n)
    {
        for(int i : stack)
            System.out.print(i);
        System.out.println(n);//该步很重要
    }
    //分解问题-递归
    stack.push(n); //类似0-1背包问题
    SumOfkNumber(sum-n, n-1);//放入n，前n-1个数填满sum-n
    stack.pop();
    SumOfkNumber(sum, n-1);//不放n，前n-1个数填满sum        
}

#2.6奇偶数排序
给定一个整数数组，调整该数组中的数的顺序，使得所有奇数位于数组的前半部分，
所有偶数位于数组的后半部分。要求时间复杂度为O(n)

boolean isOddNumber(int data)
{
    return (data&1) == 1;
}

void OddEventSort(int A[])
{
    if(A==null || A.length==0)
        return;
    int left = 0;
    int right = A.length - 1;
    while(left < right)
    {
        while(isOddNumber(A[left]))//如果是奇数不停右移
            left++;
        while(!isOddNumber(A[right]))//如果是偶数不停左移
            right--;
        if(right > left)//一定要判断下！！[1,1,2,2]像这种情况
        {
            int temp = A[left];
            A[left] = A[right];
            A[right] = temp;
        }
    }
}




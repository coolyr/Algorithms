《排序》
	一、内部排序
		1，插入排序：
			<1>直接插入排序
			<2>折半插入排序
			<3>希尔排序
		2，交换排序：
			<1>冒泡排序
			<2>快速排序
		3，选择排序：
			<1>简单选择排序
			<2>堆排序
		4，归并排序
		5，基数排序
	二、外部排序：
		1，多路归并排序

《直接插入排序》
NOTE：对L[1...n]的排序，可以将L(2)~L(n)依次插入到前面已经排好序的子序列中。
	<1>查找出L(i)在L[1...i-1]中的插入位置k
	<2>将L[k...i-1]中所有元素全部后移一个位置
	<3>将L(i)复制到L(k)
void InsertSort(int A[])
{
	for(int i=2; i<=A.length; i++)
	{
		if(A[i] < A[i-1])
		{
			A[0] = A[i]; #复制“哨兵”，A[0]不存放元素
			for(int j=i-1; A[0]<A[j]; --j)
				A[j+1] = A[j];
			A[j+1] = A[0];
		}
	}
}

《折半插入排序》
NOTE：首先折半查找出元素插入的位置。再统一地移动待插入位置之后的所有元素。
	<1>依次将A[2] ~ A[n]插入到前面排序序列
	<2>折半查找最后后的位置：最后位置 <->low = high+1
	<3>统一后移元素，空出插入位置，最后插入到正确位置
void InsertSort(int A[])
{
	int low, high, mid;
	for (int i=2; i<=A.length; ++i)
	{
		A[0] = A[i];
		low = 1;
		high = i - 1;
		while(low <= high)
		{
			mid = (low + high)/2;
			if(A[mid] > A[0])
				high = mid - 1;
			else
				low = mid + 1;
		}
		for (j=i-1; j>=high+1; --j)
			A[j+1] = A[j];
		A[high+1] = A[0];

	}
}

《希尔排序》
NOTE：又称缩小增量排序，先取一个小于n的步长d1,把表中全部记录分成d1个组，
所有距离为d1的倍数的记录放在同一个组中，在各组中进行直接插入排序；后再
取步长d2<d1,重复上述过程，直到所取到的d_t=1,即所有记录放到一个表中，然
后对全体记录进行排序。
	<1>关键字小的数是“跳跃式”前进的
	<2>前后记录位置的增量是dk,不是1
	<3>r[0]只是暂存元素，不是“哨兵”，当j<0时，插入位置已到
void ShellSort(int A[])
{
	for (dk=A.length/2; dk>=1; dk=dk/2) #步长变化
	{
		for (i=dk+1; i<=A.length; ++i)
		{
			if(A[i] < A[i-dk])
			{
				A[0] = A[i];
				for(j=i-dk; j>0&&A[0]<A[j]; j-=dk)
					A[j+dk] = A[j];
				A[j+dk] = A[0];
			}
		}
	}
}

《冒泡排序》
NOTE：假设待排序的表长为n，从后往前(从前往后)两两比较相邻的元素，若为逆序，
则交换它们，直到序列比较完称为一趟“冒泡”。找到“最大”或者“最小”元素，然后同
理排序其它的元素。
	<1>flag表示本趟“冒泡”是否发生交换的标志
	<2>一趟“冒泡”的过程，若为逆序则交换，flag=true
	<3>通过标志位判断本趟“冒泡”是否发生交换
void BubbleSort(int A[])
{
	for (int i=0; i<A.lengh-1; i++)
	{
		flag = false; #标志位
		for (int j=A.lengh-1; j>i; j--)
		{
			if(A[j-1] > A[j])
			{
				swap(A[j-1], A[j]);
				flag  = true; #发生交换
			}
		}
		if (flag == false)
			return;
	}
}

《快速排序》
NOTE：“分治法”思想，在待排序表L[1...n]中任取一个元素pivot作为基准，通过一趟排
序将待排序表划分为独立的两部分L[1...k-1]和L[k+1...n]，L[1...k-1]中所有的元素小
于pivot，L[k+1...n]都大于pivot,pivot放到了最终位置上L[k]，这个过程称为一趟快速
排序。然后递归对两个子表重复上述过程。
	<1>low<high 递归条件
	<2>关键在于划分操作partition，默认将第一个元素设置为pivot
	<3>递归对左右两个子表进行排序
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
	return pivot;
}

《简单选择排序》
NOTE：假设排序表为L[1...n]，第i趟排序即从L[i...n]中选择最小的元素与L(i)交换，
每一个排序可以确定一个元素的最终位置，经过n-1趟排序就可以使得整个排序表有序。
	<1>min记录最小元素的位置
	<2>在L[i...n-1]中选择最小的元素
	<3>跟新最小的元素，与第i个位置交换
void SelectSort(int A[])
{
	int min;
	for (int i=0; i<A.length-1; i++)
	{
		min = i;
		for(int j=i+1; j<n; j++)
			if (A[j] < A[min])
				min = j;
		if (min != i)
			swap(A[i], A[min]);
	}
}

《堆排序》
NOTE：堆排序是一种树形选择排序。首先是建堆O(n)，从最小的非叶结点n/2开始，
逐步（n/2 ~ 1；A[0]不存元素）向上，建立以当前节点为根节点的堆，每一次调整
是从上往下调整。之后把堆顶元素跟堆底元素交换，然后从上往下重新调整一次堆
结构。最后逆序[A[n] -> A[1]]为排好序的结果。
	<1>建堆函数
	<2>自上而下调整函数
	<3>堆排序函数
void BuildMaxHeap(int A[])
{
	len = A.length;
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

《归并排序》
NOTE：一趟归并排序的操作是，调用(n/2h)次算法merge()将L[1...n]中前后相邻且长度
为h的有序段进行两两归并，得到前后相邻、长度为2h的有序段，整个归并排序需要进行
log(n)趟。递归形式的2-路归并排序算法是基于分治的，将含有n个元素的待排序表分成
各含n/2个元素的子表，采用2-路归并排序算法对两个子表递归进行排序。
	<1>merge归并2个相邻的排好序的子表
	<2>MergeSort递归排序总函数

void Merge(int A[], int low, int mid, int high)
{
	for (int k=low;	k<=high; k++)
		B[k] = A[k]; # B是辅助数组
	for (int i=low, int j=mid+1, int k=i; i<=mid&&j<=high; k++)
	{
		if (B[i] <= B[j])
			A[k] = B[i++];
		else
			A[k] = B[j++];
	}
	while (i<=mid)
		A[k++] = B[j++];
	while (j<=high)
		A[k++] = B[j++];
}

void MergeSort(int A[], int low, int high)
{
	if (low<high)
	{
		int mid = (low+high)/2;		#从中间划分子表
		MergeSort(A, low, mid);		#对左侧子序列递归排序
		MergeSort(A, mid+1, high);  #对右侧子序列递归排序
		MergeSort(A, low, mid, high); #归并
	}
}

	




















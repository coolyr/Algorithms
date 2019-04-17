《顺序查找》
NOTE:在顺序表中顺序查找关键字key,若找到返回该元素在表中的位置。
	<1>[0]位置是不存元素的，这样会减少索引i是否越界的判断
	<2>从后往前查找
	<3>如果表中不存在关键字为key的元素，将查找到i=0的时候退出for循环
int Search_Seq(int[] num_list, int key)
{	
	num_list[0] = key; #哨兵
	for (i=num_list.length; num_list[i]!=key; --i)
		return i
}

《折半查找》
NOTE:在有序表中查找关键字key，如存在则返回其位置，不存在则返回-1。
	<1>取中间位置
	<2>查找成功返回所在位置
	<3>从前半部分继续查找
	<4>从后半部分继续查找
int Binary_Search(int[] seq_list, int key)
{
	//如果 right=n,循环条件需要修改
	// while(left < right)
	// right = mid;
	int low = 0;
	int high = seq_list.length - 1;
	int mid;
	while(low <= high)
	{
		//求中间位置，使用移位操作更高效，同时是为了防止《溢出！》。
		mid = (low + high)/2;
		//mid = left + ((right-left) >> 1);
		if (seq_list[mid] == key)
			return mid;
		else if (seq_list[mid] > key)
			high = mid - 1;
		else
			low = mid + 1;
	}
	return -1;
}

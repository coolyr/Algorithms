package cn.pku.coolyr;

import java.util.Arrays; //数组操作工具类
import java.util.Collections;//集合对象操作工具类

import java.util.Iterator;//迭代器对象 -<Collection>的父类
import java.util.Collection;//集合对象父类--继承Iterator
import java.util.List;//list对象的父类 --继承Collection
import java.util.Set;//set对象的父类 -- 继承Collection
import java.util.Map;//map对象的父类 -- 继承Collection
import java.util.Stack;//栈
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;//模拟--队列

public class MyTool
{
	// 链表节点
	class ListNode
	{
		int val;
		ListNode next;

		ListNode(int x)
		{
			val = x;
		}
	}

	// 二叉树节点
	public class TreeNode
	{
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x)
		{
			val = x;
		}
	}

	// 列表
	void ListTool()
	{
		ArrayList<String> list = new ArrayList<String>();
		list.add("coolyr"); // add(value)
		list.set(0, "hades"); // set(index, value)
		list.get(0); // get(index)
		list.remove(0); // remove(index)
		list.contains("coolwyr");// contains(object)
		list.clear(); // clear()
		list.indexOf(1); // indexOf(object) 返回object的索引
		list.size();
		for (int i = 0; i < list.size(); i++)
			System.out.println(i);
		for (String i : list)
			System.out.print(i);
		for (Iterator<String> iterator = list.iterator(); iterator.hasNext();)
			System.out.println(iterator.next());
	}

	// hash
	void HashTool()
	{
		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
		hashMap.get("coolyr"); // get(key)
		hashMap.put("coolyr", 100); // put(key, value)
		hashMap.containsKey("hades"); // containsKey(key)
		hashMap.containsValue(99); // containsValue(value)
		hashMap.clear();// clear()
		hashMap.isEmpty();//
		hashMap.size();//
		Set<String> keyset = hashMap.keySet();
		for (String key : keyset)
			System.out.println(key);
		Collection<Integer> values = hashMap.values();
		for (int v : values)
			System.out.println(v);

		HashMap<Character, String> map = new HashMap<Character, String>();
		// 方式1，Set<k> keySet：
		// 先获取map集合的所有键的Set集合,keySet();
		Set<Character> keySet = map.keySet();
		// 有了Set集合。就可以获取其迭代器。
		Iterator<Character> it = keySet.iterator();
		while (it.hasNext())
		{
			Character key = it.next();
			// 有了键可以通过map集合的get方法获取其对应的值。
			String value = map.get(key);
			System.out.println("key:" + key + ",value:" + value);
		}
		// 方式2，Set<Map.Entry<k,v>> entrySet
		// 将Map集合中的映射关系取出。存入到Set集合中。
		Set<Map.Entry<Character, String>> entrySet = map.entrySet();
		Iterator<Map.Entry<Character, String>> iterator = entrySet.iterator();
		while (it.hasNext())
		{
			Map.Entry<Character, String> me = iterator.next();
			Character key = me.getKey();
			String value = me.getValue();
			System.out.println(key + ":" + value);
		}
	}

	// set
	void SetTool()
	{
		HashSet<Integer> hashSet = new HashSet<Integer>();
		hashSet.add(1); // add(object)
		hashSet.contains(1);// contains(object)
		hashSet.remove(1);// remove(object)
		hashSet.isEmpty();
		hashSet.clear(); // clear
		Iterator<Integer> it = hashSet.iterator();
		while (it.hasNext())
			System.out.println(it.next());
		hashSet.size();// size or length
	}

	// 栈
	void StackTool()
	{
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(1); // push(object)
		int top_del = stack.pop(); // 删除栈顶并返回
		int top_get = stack.peek();// 获取栈顶
		boolean b = stack.isEmpty();// 判断栈是否为空
		boolean c = stack.contains(1); // contains(object)
	}

	// 队列
	void QueueTool()
	{
		LinkedList<Integer> queue = new LinkedList<Integer>();// 队列
		queue.add(1); // add(object)
		int head = queue.pop(); // 删除头元素
		int tail = queue.peek(); // 获取头元素
		boolean b = queue.isEmpty(); // 判断队列是否为空
		boolean c = queue.contains(1); // contains(object)
	}

	// Arrays:用于操作数组的工具类。里面都是静态方法。
	void ArraysTool()
	{
		int[] nums =
		{ 2, 3, 1, 8, 0, 9, 4, 6 };
		// 排序
		Arrays.sort(nums);// 直接对nums对象排序
		for (int n : nums)
			System.out.println(n);
		// 对部分元素排序
		Arrays.sort(nums, 5, 8); // 对【9,4,6】排序
		// 查找--二分查找
		int index = Arrays.binarySearch(nums, 0);// 返回位置
		
	}

	// Collections是集合框架中的一个工具类。该类中的方法都是静态的,提供对list集合进行排序，二分查找等方法
	void CollectionsTool()
	{
		ArrayList<Integer> list = new ArrayList<Integer>();
		//查找 -- 二分查找
		int index = Collections.binarySearch(list, 3);//返回索引
		//排序 
		Collections.sort(list);
	}

	public static void main(String[] args)
	{	
		
	}

}

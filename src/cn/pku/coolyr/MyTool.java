package cn.pku.coolyr;

import java.util.Arrays; //�������������
import java.util.Collections;//���϶������������

import java.util.Iterator;//���������� -<Collection>�ĸ���
import java.util.Collection;//���϶�����--�̳�Iterator
import java.util.List;//list����ĸ��� --�̳�Collection
import java.util.Set;//set����ĸ��� -- �̳�Collection
import java.util.Map;//map����ĸ��� -- �̳�Collection
import java.util.Stack;//ջ
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;//ģ��--����

public class MyTool
{
	// ����ڵ�
	class ListNode
	{
		int val;
		ListNode next;

		ListNode(int x)
		{
			val = x;
		}
	}

	// �������ڵ�
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

	// �б�
	void ListTool()
	{
		ArrayList<String> list = new ArrayList<String>();
		list.add("coolyr"); // add(value)
		list.set(0, "hades"); // set(index, value)
		list.get(0); // get(index)
		list.remove(0); // remove(index)
		list.contains("coolwyr");// contains(object)
		list.clear(); // clear()
		list.indexOf(1); // indexOf(object) ����object������
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
		// ��ʽ1��Set<k> keySet��
		// �Ȼ�ȡmap���ϵ����м���Set����,keySet();
		Set<Character> keySet = map.keySet();
		// ����Set���ϡ��Ϳ��Ի�ȡ���������
		Iterator<Character> it = keySet.iterator();
		while (it.hasNext())
		{
			Character key = it.next();
			// ���˼�����ͨ��map���ϵ�get������ȡ���Ӧ��ֵ��
			String value = map.get(key);
			System.out.println("key:" + key + ",value:" + value);
		}
		// ��ʽ2��Set<Map.Entry<k,v>> entrySet
		// ��Map�����е�ӳ���ϵȡ�������뵽Set�����С�
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

	// ջ
	void StackTool()
	{
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(1); // push(object)
		int top_del = stack.pop(); // ɾ��ջ��������
		int top_get = stack.peek();// ��ȡջ��
		boolean b = stack.isEmpty();// �ж�ջ�Ƿ�Ϊ��
		boolean c = stack.contains(1); // contains(object)
	}

	// ����
	void QueueTool()
	{
		LinkedList<Integer> queue = new LinkedList<Integer>();// ����
		queue.add(1); // add(object)
		int head = queue.pop(); // ɾ��ͷԪ��
		int tail = queue.peek(); // ��ȡͷԪ��
		boolean b = queue.isEmpty(); // �ж϶����Ƿ�Ϊ��
		boolean c = queue.contains(1); // contains(object)
	}

	// Arrays:���ڲ�������Ĺ����ࡣ���涼�Ǿ�̬������
	void ArraysTool()
	{
		int[] nums =
		{ 2, 3, 1, 8, 0, 9, 4, 6 };
		// ����
		Arrays.sort(nums);// ֱ�Ӷ�nums��������
		for (int n : nums)
			System.out.println(n);
		// �Բ���Ԫ������
		Arrays.sort(nums, 5, 8); // �ԡ�9,4,6������
		// ����--���ֲ���
		int index = Arrays.binarySearch(nums, 0);// ����λ��
		
	}

	// Collections�Ǽ��Ͽ���е�һ�������ࡣ�����еķ������Ǿ�̬��,�ṩ��list���Ͻ������򣬶��ֲ��ҵȷ���
	void CollectionsTool()
	{
		ArrayList<Integer> list = new ArrayList<Integer>();
		//���� -- ���ֲ���
		int index = Collections.binarySearch(list, 3);//��������
		//���� 
		Collections.sort(list);
	}

	public static void main(String[] args)
	{	
		
	}

}

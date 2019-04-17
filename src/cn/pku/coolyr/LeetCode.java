#1. <Two Sum> 
Given an array of integers, return indices of the two numbers such that they add 
up to a specific target.
You may assume that each input would have exactly one solution.
Example:
Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [1, 2].
public class Solution {
    public int[] twoSum(int[] nums, int target) {
        
		int size = nums.length;
		int needNum;
		int[] indexs = new int[2];
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < size; ++i)
		{
			needNum = target - nums[i];
			if (map.containsKey(needNum))
			{
				indexs[0] = map.get(needNum) + 1;
				indexs[1] = i + 1;
			}
			else
			{
				map.put(nums[i], i);
			}

		}

		return indexs;
	
    }
}


#8. <String to Integer (atoi)>
public class Solution {
    public int myAtoi(String str) {
		if (str == null || str == "")
			return 0;
		str = str.trim();
		if (str.length() == 0)
			return 0;

		char[] mychars = str.toCharArray();
		int length = mychars.length;

		if (length == 1)
		{
			if (mychars[0] >= '0' && mychars[0] <= '9')
				return Integer.parseInt(mychars[0] + "");
			else
				return 0;
		}

		int num = 0;
		int index = 1;
		switch (mychars[0])
		{

			case '+':

				for (; index < length && mychars[index] >= '0' && mychars[index] 
				<= '9'; ++index);
				if (index > 1)
				{
					String numString = str.toString().substring(1, index);
					if (numString.length() < 10)
						num = +Integer.parseInt(numString);
					else
					{
						if (numString.length() >= 11)
							num = Integer.MAX_VALUE;
						else
						{
							Long numLong = (long) Long.parseLong(numString);
							if (numLong >= Integer.MAX_VALUE)
								num = Integer.MAX_VALUE;
							else
								num = +Integer.parseInt(numString);
						}
					}
				}
				break;

			case '-':
				for (; index < length && mychars[index] >= '0' && mychars[index] 
				<= '9'; ++index);
				if (index > 1)
				{
					String numString = str.toString().substring(1, index);
					if (numString.length() < 10)
						num = -Integer.parseInt(numString);
					else
					{
						// System.out.println(numString + "  len = " + 
						numString.length());

						if (numString.length() >= 11)
							num = Integer.MIN_VALUE;
						else
						{
							Long numLong = (long) Long.parseLong(numString);

							if ((-numLong) <= Integer.MIN_VALUE)
								num = Integer.MIN_VALUE;
							else
								num = -Integer.parseInt(numString);
						}
					}
				}
				break;

			default:
				if (mychars[0] >= '0' && mychars[0] <= '9')
				{
					for (; index < length && mychars[index] >= '0' && 
					mychars[index] <= '9'; ++index);

					if (index >= 1)
					{
						String numString = str.toString().substring(0, index);

						if (numString.length() < 10)
							num = Integer.parseInt(numString);
						else
						{
							if (numString.length() >= 11)
								num = Integer.MAX_VALUE;
							else
							{
								long numLong = (long) Long.parseLong(numString);

								if (numLong >= Integer.MAX_VALUE)
									num = Integer.MAX_VALUE;
								else
									if (numLong <= Integer.MIN_VALUE)
										num = Integer.MIN_VALUE;
									else
										num = Integer.parseInt(numString);
							}
						}
					}
				}

		}
		return num;
	}
}

#9. <Palindrome Number>
Total Accepted: 137435
Total Submissions: 421762
Difficulty: Easy
Determine whether an integer is a palindrome. Do this without extra space.
public class Solution {
    public boolean isPalindrome(int x) {
		if (x < 0)
			return false;
		List<Integer> list = new ArrayList<Integer>();
		while (x != 0)
		{
			list.add(x % 10);
			x = x / 10;
		}
		int size = list.size();
		for (int i = 0; i < (size / 2); ++i)
			if (list.get(i) != list.get(size - i - 1))
				return false;
		return true;
	}
}

#15 <3Sum>
Given an array S of n integers, are there elements a, b, c in S such that a + b 
+ c = 0? Find all unique triplets in the array which gives the sum of zero.
Note: The solution set must not contain duplicate triplets.
For example, given array S = [-1, 0, 1, 2, -1, -4],
A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]
public class Solution {
    /**
	 * 快排
	 * 
	 * @param A
	 * @param low
	 * @param high
	 */
	public static void QuickSort(int A[], int low, int high)
	{
		if (low < high)
		{
			int pivotpos = Partition(A, low, high);
			QuickSort(A, low, pivotpos - 1);
			QuickSort(A, pivotpos + 1, high);
		}
	}

	/**
	 * 划分操作
	 * 
	 * @param a
	 * @param low
	 * @param high
	 * @return
	 */
	private static int Partition(int[] A, int low, int high)
	{
		// TODO Auto-generated method stub
		int pivot = A[low];
		while (low < high)
		{
			while (low < high && A[high] >= pivot)
				--high;
			A[low] = A[high];
			while (low < high && A[low] <= pivot)
				++low;
			A[high] = A[low];
		}
		A[low] = pivot;
		return low;
	}
    public List<List<Integer>> threeSum(int[] nums) { // [-2,0,0,2,2]

		// 排序 -- 快排
		// QuickSort(nums, 0, nums.length - 1);
		Arrays.sort(nums);
		int difference = 0;// 需要数字(差值)
		List<List<Integer>> lists = new ArrayList<List<Integer>>();
		for (int i = 0; i < nums.length; ++i)
		{
			difference = 0 - nums[i];
			for (int j = i + 1, k = nums.length - 1; j < k;)
			{
				int sum = nums[j] + nums[k];
				if (sum > difference)
					k--;
				else if (sum < difference)
					j++;
				else
				{
					ArrayList<Integer> list = new ArrayList<Integer>();
					list.add(nums[i]);
					list.add(nums[j]);
					list.add(nums[k]);
					lists.add(list);
					j++;
					k--;
					while (j < k && nums[j-1] == nums[j])
						j++; //过滤掉相同的
					while (j < k && nums[k+1] == nums[k])
						k--;
				}

			}
			while ((i + 1) < nums.length && nums[i] == nums[i + 1])
				i++; //过滤掉相同的
		}

		return lists;
	}
}

#18. 4Sum  QuestionEditorial Solution  My Submissions
Total Accepted: 81334
Total Submissions: 331588
Difficulty: Medium
Given an array S of n integers, are there elements a, b, c, and d in S such that 
a + b + c + d = target? Find all unique quadruplets in the array which gives the 
sum of target.
Note: The solution set must not contain duplicate quadruplets.
For example, given array S = [1, 0, -1, 0, -2, 2], and target = 0.
A solution set is:
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]
public class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
		// 排序
		Arrays.sort(nums);
		int difference = 0;// 需要数字(差值)
		List<List<Integer>> lists = new ArrayList<List<Integer>>();
		//迭代
		for (int i = 0; i < nums.length; ++i)
		{
			for (int j = i + 1; j < nums.length; ++j)
			{
				difference = target - nums[i] - nums[j];
				for (int m = j + 1, n = nums.length - 1; m < n;)
				{
					int sum = nums[m] + nums[n];
					if (sum > difference)
						n--;
					else if (sum < difference)
						m++;
					else
					{
						ArrayList<Integer> list = new ArrayList<Integer>();
						list.add(nums[i]);
						list.add(nums[j]);
						list.add(nums[m]);
						list.add(nums[n]);
						lists.add(list);
						m++;
						n--;
						while (m < n && nums[m-1] == nums[m])
							m++;
						while (m < n && nums[n+1] == nums[n])
							n--;
					}
				}
				//跳过j指针相同的变量
				while ((j + 1) < nums.length && nums[j] == nums[j + 1])
					j++;
			}
			//跳过i指针相同的变量
			while ((i + 1) < nums.length && nums[i] == nums[i + 1])
				i++;
		}

		return lists;
	}
}

#21. Merge Two Sorted Lists 
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if (l1 == null)
			return l2;
		if (l2 == null)
			return l1;
		ListNode head = null;
		if (l1.val < l2.val)
		{
			head = l1;
			l1 = l1.next;
		}
		else
		{
			head = l2;
			l2 = l2.next;
		}
		ListNode current = head;
		while (l1 != null && l2 != null)
		{
			if (l1.val < l2.val)
			{
				current.next = l1;
				current = l1;
				l1 = l1.next;
			}
			else
			{
				current.next = l2;
				current = l2;
				l2 = l2.next;
			}
		}
		if(l1 == null)
			current.next = l2;
		else 
			current.next = l1;
	
		return head;
	}
}

#66. Plus One
Given a non-negative number represented as an array of digits, plus one to the 
number.
The digits are stored such that the most significant digit is at the head of the 
list.
public class Solution {
    public int[] plusOne(int[] digits) {
        
        int len = digits.length;
        int carry = 0; 
        int i = len-1;
        int num = digits[i]+1+carry;
        digits[i] = num%10;
        carry = num/10;
        
        if(carry != 0)
         {
             --i;
            for(; i>=0; i--)
            {
                num = digits[i]+carry;
                digits[i] = num%10;
                carry = num/10;
                if(carry == 0)
                {
                    break;
                }
            }
            if(i >= 0)
            {
                return digits;
            }
            else 
            {
                if(carry == 1)
                {
                    int[] result = new int[len + 1];
                    for(int j=len-1; j>=0; j--)
                        result[j+1] = digits[j];
                    result[0] = 1;
                    return result;
                }
                else
                {
                    return digits;
                }
            }
        }
        else
        {
            return digits;
        }
        
    }
}

#70. Climbing Stairs
Difficulty: Easy
You are climbing a stair case. It takes n steps to reach to the top.
Each time you can either climb 1 or 2 steps. In how many distinct ways can you 
climb to the top?
public class Solution {
    public int climbStairs(int n) {
        
// 		if (n == 0 || n == 1)
// 			return 1;

		if (n == 1)
			return 1;
		else if (n == 2)
			return 2;
		else
		{
			//return climbStairs(n - 1) + climbStairs(n - 2);// 第n步，只能从第
			n-1或者n-2处走到
			int[] steps = new int[n];
			steps[0] = 1;
			steps[1] = 2;
			for(int i= 2; i<n; i++)
				steps[i] = steps[i-1] + steps[i-2];
			
			return steps[n-1];
		}
	
    }
}

#75. Sort Colors 
Difficulty: Medium
Given an array with n objects colored red, white or blue, sort them so that 
objects of the same color are adjacent, with the colors in the order red, white 
and blue.
Here, we will use the integers 0, 1, and 2 to represent the color red, white, 
and blue respectively.
public class Solution {
    public void sortColors(int[] nums) {
//         int[] colors =
// 		{ 0, 0, 0 };
// 		for (int i = 0; i < nums.length; ++i)
// 		{
// 			switch (nums[i])
// 			{
// 				case 0:
// 					colors[0]++;
// 					break;
// 				case 1:
// 					colors[1]++;
// 					break;
// 				case 2:
// 					colors[2]++;
// 					break;
// 			}
// 		}
// 		int index=0;
// 		for(int i=0; i<colors[0];++index,++i)
// 			nums[index] = 0;
// 		for(int i=0; i<colors[1];++index,++i)
// 			nums[index] = 1;
// 		for(int i=0; i<colors[2];++index,++i)
// 			nums[index] = 2;

		int red = -1; // 永远指向当前最后一个red元素位置
		int blue = nums.length; // 永远指向当前最前一个blue元素的位置
		for (int i = 0; red < blue && i < blue;)
		{
			switch (nums[i])
			{
				case 0:
					if(i == red)
					{
						++i;	
						break;
					}
					swap(nums, ++red, i);
					break;
				case 1:
					++i;
					break;
				case 2:
					swap(nums, --blue, i);
					break;

			}
		}
// 		for (int x : nums)
// 			System.out.println(x);
	
    }
    public static void swap(int[] a, int i, int j)
	{
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}

#83. Remove Duplicates from Sorted List
Difficulty: Easy
Given a sorted linked list, delete all duplicates such that each element appear 
only once.
For example,
Given 1->1->2, return 1->2.
Given 1->1->2->3->3, return 1->2->3.
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        
		if (head == null || head.next == null)
			return head;
		ListNode newTail = head;
		ListNode p = head.next;
		while (p != null)
		{
			if (newTail.val != p.val)
			{
				newTail.next = p;
				newTail = p;
			}
			p = p.next;
		}
		newTail.next = null;
		return head;
	
    }
}

#94. Binary Tree Inorder Traversal 
Difficulty: Medium
Given a binary tree, return the inorder traversal of its nodes' values.
For example:
Given binary tree [1,null,2,3],
   1
    \
     2
    /
   3
return [1,3,2].

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        
		// 二叉树中序遍历的非递归算法，需要借助一个栈
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode p = root;// p是遍历指针
		ArrayList<Integer> list = new ArrayList<Integer>();
		while ((p != null) || (!stack.isEmpty()))// 栈不空或者p非null时循环
		{
			if (p != null)//根指针进栈，遍历左子树 -- 向左走到底
			{
				stack.push(p); //没遇到非空的节点，进栈--向左走
				p = p.left;
			}
			else
			{
				p = stack.pop();//根指针退栈，访问根节点，遍历右子树
				list.add(p.val);//退栈，访问根节点(没有左孩子或者左孩子都已访问
				过)
				p = p.right;//再向右子树走
			}
		}
		return list;   
    }
}

#100. Same Tree  QuestionEditorial Solution  My Submissions
Difficulty: Easy
Given two binary trees, write a function to check if they are equal or not.
Two binary trees are considered equal if they are structurally identical and the 
nodes have the same value.
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        
// 		if (p == null && q != null)//迭代的方法
// 			return false;
// 		else if (p != null && q == null)
// 			return false;
// 		else if (p != null && q != null)
// 		{
// 			if (p.val != q.val)
// 				return false;
// 			else
// 				return (isSameTree(p.left, q.left) && isSameTree(p.right, 
q.right));
// 		}
// 		else
// 			return true;				
		
		if (p == null && q == null)
			return true;
		else if (p != null && q != null)
		{
			Stack<TreeNode> stackP = new Stack<TreeNode>();
			Stack<TreeNode> stackQ = new Stack<TreeNode>();

			stackP.push(p);
			stackQ.push(q);
			while (!stackP.isEmpty())// 使用先根遍历的非递归算法
			{
				p = stackP.pop();
				q = stackQ.pop();
				if (p.val == q.val)
				{
					//先把右子树压栈，先访问左子树
					if (p.right != null && q.right != null)
					{
						stackP.push(p.right);
						stackQ.push(q.right);
					}
					else if (p.right == null && q.right == null)
						;// 什么也不做
					else
						return false;
					//再把左子树压栈
					if (p.left != null && q.left != null)
					{
						stackP.push(p.left);
						stackQ.push(q.left);
					}
					else if (p.left == null && q.left == null)
						;// 什么也不做
					else
						return false;
				}
				else
				{
					return false;
				}
			}
			return true;
		}
		else
			return false;		
    }
}

#101. Symmetric Tree 
Difficulty: Easy
Given a binary tree, check whether it is a mirror of itself (ie, symmetric 
around its center).
For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
    1
   / \
  2   2
 / \ / \
3  4 4  3
But the following [1,2,2,null,3,null,3] is not:
    1
   / \
  2   2
   \   \
   3    3
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public boolean isSymmetric(TreeNode root) {
		if ((root == null) || (root.left == null && root.right == null))// 排除
		null和一个节点的情况
			return true;
		LinkedList<TreeNode> queue1 = new LinkedList<TreeNode>();
		LinkedList<TreeNode> queue2 = new LinkedList<TreeNode>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode node = null;
		TreeNode nodeCompare = null;
		int halfSize = 0;
		boolean isOver = false;
		queue1.add(root.left);
		queue1.add(root.right);
		while (!isOver)
		{
			halfSize = queue1.size() / 2;
			for (int i = 0; i < halfSize; i++)
			{
				node = queue1.pop();
				if (node != null)// 非空节点把它的左右孩子节点加入，左半边
				{
					queue2.add(node.left);
					queue2.add(node.right);
				}
				stack.push(node);
			}
			while (!queue1.isEmpty())
			{
				nodeCompare = stack.pop();
				node = queue1.pop();
				if ((node != null && nodeCompare != null) && (nodeCompare.val != 
				node.val))
					return false;
				else if (node == null && nodeCompare != null)
					return false;
				else if (node != null && nodeCompare == null)
					return false;

				if (node != null)// 非空节点把它的左右孩子节点加入，右半边
				{
					queue2.add(node.left);
					queue2.add(node.right);
				}
			}
			if (queue2.isEmpty())// 队列2为空时，结束
				isOver = true;
			else
			{
				LinkedList<TreeNode> queue = queue1; // 交换队列1 和 队列2
				queue1 = queue2;
				queue2 = queue;
			}
		}
		return true;
	}
}

#104. Maximum Depth of Binary Tree
Difficulty: Easy
Given a binary tree, find its maximum depth.
The maximum depth is the number of nodes along the longest path from the root 
node down to the farthest leaf node.
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public int maxDepth(TreeNode root) {
        
		if (root == null)
			return 0;
		else if (root.left == null && root.right == null)
			return 1;
		else if (root.left != null && root.right != null)
			return Math.max(maxDepth(root.left) + 1, maxDepth(root.right) + 1);
		else if (root.left != null)
			return maxDepth(root.left) + 1;
		else
			return maxDepth(root.right) + 1;

		//if (root == null)
		//	return 0;
		//else
		//	return Math.max(maxDepth(root.left) + 1, maxDepth(root.right) + 1);
    }
}

#107. Binary Tree Level Order Traversal II
Difficulty: Easy
Given a binary tree, return the bottom-up level order traversal of its nodes' 
values. (ie, from left to right, level by level from leaf to root).
For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its bottom-up level order traversal as:
[
  [15,7],
  [9,20],
  [3]
]
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
		if (root == null)
			return new ArrayList<List<Integer>>();
		TreeNode lasNode = root;
		TreeNode p;
		int level = 0;
		LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
		List<Integer> list = new ArrayList<Integer>();
		HashMap<Integer, List<Integer>> hashMap = new HashMap<Integer, 
		List<Integer>>();
		ArrayList<List<Integer>> lists = new ArrayList<List<Integer>>();

		queue.add(root);
		while (!queue.isEmpty())
		{
			p = queue.pop();
			list.add(p.val);

			if (p.left != null)
				queue.add(p.left);
			if (p.right != null)
				queue.add(p.right);

			if (p == lasNode)
			{
				level++;
				hashMap.put(level, list);
				list = new ArrayList<Integer>();
				if (queue.isEmpty())
					break;
				lasNode = queue.getLast();// lastNode指向下层的最后一个节点
			}
		}
		for (int i = level; i > 0; i--)//逆向：从低向上存入每层的节点
			lists.add(hashMap.get(i));
		return lists;
	}
}

#110. Balanced Binary Tree
Difficulty: Easy
Given a binary tree, determine if it is height-balanced.
For this problem, a height-balanced binary tree is defined as a binary tree in 
which the depth of the two subtrees of every node never differ by more than 1.
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
  
  //第一种方法  
//     private class Height
// 	{
// 		int h;

// 		public Height(int x)
// 		{
// 			h = x;
// 		}
// 	}
	
//     public boolean isBalanced(TreeNode root) {

// 		if (root == null)
// 			return true;
// 		return IsAVL(root, new Height(0));
	
//     }
    
    
//     private boolean IsAVL(TreeNode root, Height height)
// 	{
// 		if (root == null) // 空树，返回真
// 		{
// 			height.h = 0;
// 			return true;
// 		}
// 		Height heightLeft = new Height(0);
// 		boolean resultLeft = IsAVL(root.left, heightLeft);
// 		Height heightRight = new Height(0);;
// 		boolean resultRight = IsAVL(root.right, heightRight);
// 		if (resultLeft && resultRight && Math.abs(heightLeft.h - heightRight.h) 
<= 1) //左子树和右子树都是AVL，并且高度相差不大于1，返回真
// 		{
// 			height.h = Math.max(heightLeft.h, heightRight.h) + 1;
// 			return true;
// 		}
// 		else
// 		{
// 			height.h = Math.max(heightLeft.h, heightRight.h) + 1;
// 			return false;
// 		}
// 	}


//第二种方法 --第二种方法好
    public int getHeight(TreeNode root)
	{
		if (root == null)
			return 0;

		int left = getHeight(root.left);
		int right = getHeight(root.right);

		if (left == -1 || right == -1)
			return -1;

		if (Math.abs(left - right) > 1)
			return -1;

		return Math.max(left, right) + 1;

	}

	public boolean isBalanced(TreeNode root)
	{
		if (root == null)
			return true;

		if (getHeight(root) == -1)
			return false;

		return true;
	}
}

#111. Minimum Depth of Binary Tree  
Difficulty: Easy
Given a binary tree, find its minimum depth.
The minimum depth is the number of nodes along the shortest path from the root 
node down to the nearest leaf node.
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public int minDepth(TreeNode root) {
		if (root == null)
			return 0;
		TreeNode lastNode = root;
		TreeNode p;
		int level = 0;
		LinkedList<TreeNode> queue = new LinkedList<TreeNode>();

		queue.add(root);// 添加一个元素
		while (!queue.isEmpty()) // 队列不空则循环
		{
			p = queue.poll();// 移除并返回头部元素
			if (p.left == null && p.right == null)// 判断是不是第一个叶子节点
				return level+1;// 是第一个叶子节点返回当前叶节点的深度
			if (p.left != null)
				queue.add(p.left);// 左孩子入队
			if (p.right != null)
				queue.add(p.right);// 右孩子入队
			if (p == lastNode) // 处理该层的最有一个节点
			{
				level++; // 层数加1
				lastNode = queue.getLast(); // lastNode指向下层的最后一个节点
			}
		}
		//return level;//这个return没用
	}
}

#118. Pascal's Triangle
Difficulty: Easy
Given numRows, generate the first numRows of Pascal's triangle.
For example, given numRows = 5,
Return
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
public class Solution {
    public List<List<Integer>> generate(int numRows) {
        
		if (numRows < 0)
			return null;
		else if(numRows == 0)
		{
			List<List<Integer>> list = new ArrayList<List<Integer>>();
			return list;
		}
			
		else if (numRows == 1)
		{
			List<List<Integer>> list = new ArrayList<List<Integer>>();
			ArrayList<Integer> list0 = new ArrayList<Integer>();
			list0.add(1);
			list.add(list0);
			return list;
		}
		else if (numRows == 2)
		{
			List<List<Integer>> list = new ArrayList<List<Integer>>();
			ArrayList<Integer> list0 = new ArrayList<Integer>();
			ArrayList<Integer> list1 = new ArrayList<Integer>();
			list0.add(1);
			list1.add(1);
			list1.add(1);
			list.add(list0);
			list.add(list1);
			return list;
		}
		else
		{
			List<List<Integer>> list = new ArrayList<List<Integer>>();
			ArrayList<Integer> list0 = new ArrayList<Integer>();
			ArrayList<Integer> list1 = new ArrayList<Integer>();
			list0.add(1);
			list1.add(1);
			list1.add(1);
			list.add(list0);
			list.add(list1);

			ArrayList<Integer> listUp;
			for (int i = 2; i < numRows; ++i)
			{
				listUp = (ArrayList<Integer>) list.get(i - 1);
				ArrayList<Integer> listTemp = new ArrayList<Integer>();
				listTemp.add(1);
				int size = listUp.size();
				for (int j = 0; j < size - 1; ++j)
					listTemp.add(listUp.get(j) + listUp.get(j + 1));
				listTemp.add(1);
				list.add(listTemp);
			}
			return list;
		}	
    }
}

#119. Pascal's Triangle II
Difficulty: Easy
Given an index k, return the kth row of the Pascal's triangle.
For example, given k = 3,
Return [1,3,3,1].
public class Solution {
    public List<Integer> getRow(int rowIndex) {

		if (rowIndex < 0)
			return null;
		else if (rowIndex == 0)
		{
			List<Integer> list = new ArrayList<Integer>();
			list.add(1);
			return list;
		}

		else if (rowIndex == 1)
		{
			List<Integer> list = new ArrayList<Integer>();
			list.add(1);
			list.add(1);
			return list;
		}

		else
		{
			List<Integer> list = new ArrayList<Integer>();
			list.add(1);
			list.add(1);
			for (int index = 1; index < rowIndex; ++index)
			{
				list.add(1);
				for (int i = list.size() - 2; i > 0; --i)
					list.set(i, list.get(i) + list.get(i - 1));
			}
			return list;
		}
	}
}

#125. Valid Palindrome
Given a string, determine if it is a palindrome, considering only alphanumeric 
characters and ignoring cases.
For example,
"A man, a plan, a canal: Panama" is a palindrome.
"race a car" is not a palindrome.
public class Solution {
    public boolean isPalindrome(String s) {
		if (s == "")
			return true;
		int n = s.length();
		char[] ss = s.toCharArray();
		int i = 0, j = n - 1;
		while (i < j)
		{
			if (!isAlpha(ss,i)) //跳过非字母
			{
				++i;
				continue;
			}
			if (!isAlpha(ss,j))
			{
				--j;
				continue;
			}
			if (ss[i] != ss[j])
				return false;
			++i;
			--j;
		}
		return true;
	}
   // 判断是否是字母数字，如果是大写字母则将其转化为小写字母
	public static boolean isAlpha(char[] c,int i)
	{
		if ((c[i] >= 'A' && c[i] <= 'Z'))
		{
			c[i] = (char) (c[i] - 'A' + 'a');
			return true;
		}
		return (c[i] >= 'a' && c[i] <= 'z') || (c[i] >= '0' && c[i] <= '9');
	}
}

#136. Single Number
Given an array of integers, every element appears twice except for one. Find 
that single one.
public class Solution {
    public int singleNumber(int[] nums) {
        
		int sum = nums[0];
		for (int i = 1; i < nums.length; i++)
		{
			sum = sum ^ nums[i]; //异或操作，最终剩下的数就是
		}
		return sum;
	
    }
}

#141. Linked List Cycle
Given a linked list, determine if it has a cycle in it.
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
		ListNode ahead = head;
		ListNode back = head;
		if(ahead == null)
			return false;
		else if(ahead.next == null)
			return false;
		while (ahead != null)
		{
			ahead = ahead.next; //走1步
			if(ahead == null) //中间这一步需要判断下是否已为null
				return false;
			ahead = ahead.next; //再走一步
			back = back.next; //走一步
			if(ahead == back)
				return true;
		}
		return false;
	}
}
#144. Binary Tree Preorder Traversal
Given a binary tree, return the preorder traversal of its nodes' values.
For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [1,2,3].
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        
		// 二叉树前序遍历的非递归算法，需要借助一个栈
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode p = root;// p是遍历指针
		ArrayList<Integer> list = new ArrayList<Integer>();

		if(p == null)
			return list;
			
		stack.push(p);// 根节点入栈
		while (!stack.isEmpty())
		{
			p = stack.pop();// 出栈
			list.add(p.val);// 访问当前节点--栈中元素非空

			if (p.right != null)// 后访问的先入栈
				stack.push(p.right);

			if (p.left != null)
				stack.push(p.left);
		}
		return list;     
    }
}

#145. Binary Tree Postorder Traversal
Given a binary tree, return the postorder traversal of its nodes' values.
For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [3,2,1].
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {        
		ArrayList<Integer> list = new ArrayList<Integer>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode p = root;//遍历节点
		TreeNode r = null;//记录上次访问的几点（用以判断是从左子树返回的还是右子
		树）
		while ((p != null) || !stack.isEmpty())
		{
			if (p != null)//向左走到底
			{
				stack.push(p);
				p = p.left;
			}
			else//向右
			{
				p = stack.peek(); //取栈顶节点
				if ((p.right != null) && (p.right != r))//如果右子树存在，并且没
				有访问过
				{
					p = p.right;//向右走
					stack.push(p);//压入栈
					p = p.left;//向左走
				}
				else//当右子树不存在，或者右子树已经访问完
				{
					p = stack.pop();//弹出该节点
					list.add(p.val);//并访问
					r = p;//记录最近访问的节点
					p = null;//节点访问完，重置p指针
				}
			}
		}

		return list;
    }
}

#151. Reverse Words in a String
Given an input string, reverse the string word by word.
For example,
Given s = "the sky is blue",
return "blue is sky the".
public class Solution {
    public String reverseWords(String s) {
        
		if (s == null || s == "")
			return "";
		s = s.trim();
		if (s.length() == 0)
			return "";

		String[] words = s.split(" ");
		for (int i = 0, j = words.length - 1; i < j; i++, j--)
		{
		     swap(words, i, j);
		}
		s = "";
		for(int i=0; i<words.length; ++i)
		    if(!"".equals(words[i]))//使用split函数时，当中间有多一个空格时候，
			会切分出一个“”空串！
		    	s += words[i] + " ";
		return s.trim();
	
    }
    public static void swap(String[] a, int i, int j)
	{
		String temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}

#155. Min Stack
Design a stack that supports push, pop, top, and retrieving the minimum element 
in constant time.
push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.

class MinStack {  
	Stack<Integer> stack = new Stack<Integer>();// 实际进行存储的的栈
	Stack<Integer> stack_min = new Stack<Integer>();// 存储最小的元素
	public void push(int x)
	{
		stack.push(x);
		if (stack_min.isEmpty())
			stack_min.push(x);
		else if (stack_min.peek() > x)
			stack_min.push(x);
		else
			stack_min.push(stack_min.peek());
	}
	public void pop()
	{
		stack.pop();
		stack_min.pop();

	}
	public int top()
	{
		return stack.peek();
	}
	public int getMin()
	{
		return stack_min.peek();
	}
}

#169. Majority Element
Given an array of size n, find the majority element. The majority element is the 
element that appears more than ⌊ n/2 ⌋ times.
You may assume that the array is non-empty and the majority element always exist 
in the array.
public class Solution {
    public int majorityElement(int[] nums) {
		HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; ++i)
		{
			if (hashMap.containsKey(nums[i]))
				hashMap.put(nums[i], hashMap.get(nums[i]) + 1);
			else
				hashMap.put(nums[i], 1);
		}
		int half = nums.length/2;
		for(int x : hashMap.keySet())
			if(hashMap.get(x) > half)
				return x;
		return 0;//这句操作无用
    }
}
//每找出两个不同的element，则成对删除。最终剩下的一定就是所求的。
//可扩展到⌊ n/k ⌋的情况，每k个不同的element进行成对删除。
class Solution {
public:
    int majorityElement(vector<int> &num) {
        int elem = 0;
        int count = 0;      
        for(int i = 0; i < num.size(); i++)  {        
            if(count == 0)
			{
                elem = num[i];
                count = 1;
            }
            else
			{
                if(elem == num[i])
                    count++;
                else
                    count--;
            }            
        }
        return elem;
    }
　};

#189. Rotate Array
Rotate an array of n elements to the right by k steps.
For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to 
[5,6,7,1,2,3,4].
public class Solution {
    public void rotate(int[] nums, int k) {       
		int length = nums.length;
		int step = k % length;
		reverse(nums, 0, length-step-1);
		reverse(nums, length-step, length-1);
		reverse(nums, 0, length-1);	
    }
    public static void reverse(int[] nums, int begin, int end)
	{
		int temp;
		for(int i=begin, j=end; i<j; i++,j--)
		{
			temp = nums[i];
			nums[i] = nums[j];
			nums[j] = temp;
		}
	}
}

#191. Number of 1 Bits
Write a function that takes an unsigned integer and returns the number of ’1' 
bits it has (also known as the Hamming weight).
For example, the 32-bit integer ’11' has binary representation 
00000000000000000000000000001011, so the function should return 3.
public class Solution {
// you need to treat n as an unsigned value
    public int hammingWeight(int n) {
//      String str = Integer.toBinaryString(n);
// 		char[] strChars = str.toCharArray();
// 		int length = strChars.length;
// 		int count = 0;
// 		for (int i = 0; i < length; ++i)
// 		{
// 			if('1' == strChars[i])
// 				count++;
// 		}
// 		return count;

		int count = 0;
		while (n != 0)
		{
			count = count + (n&1);
			n = n>>>1;	//无符号又移
		}
		return count;
    }
}

#202. Happy Number
Write an algorithm to determine if a number is "happy".
A happy number is a number defined by the following process: Starting with any 
positive integer, replace the number by
the sum of the squares of its digits, and repeat the process until the number 
equals 1 (where it will stay), or it loops 
endlessly in a cycle which does not include 1. Those numbers for which this 
process ends in 1 are happy numbers.
Example: 19 is a happy number
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1
public class Solution {
    public boolean isHappy(int n) {     
			HashSet<Integer> circleSet = new HashSet<>();
			int sum = 0;
			while (n != 1)
			{
				sum = 0;
				while (n != 0)
				{
					sum += Math.pow((n % 10), 2);
					n = n / 10;
				}
				if (sum == 1)
					return true;
				else
				{
					if (circleSet.contains(sum))
						return false;
					else
					{
						n = sum;
						circleSet.add(sum);
					}
				}
			}
			return true;		
    }
}

#206. Reverse Linked List
Reverse a singly linked list.
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode reverseList(ListNode head) {
        //迭代 - 头插法
// 		if (head == null)
// 			return null;
// 		ListNode pHead = head;
// 		ListNode qHead = head.next;
// 		ListNode tNext = null;
// 		while (qHead!= null)
// 		{
// 			tNext = qHead.next;
// 			qHead.next = pHead;
// 			pHead = qHead;
// 			qHead = tNext;
// 		}
// 		head.next = null;
// 		return pHead;
    /**
	 * 采用递归的方式
	 * (1): 12354null
	 * (2): 12543null
	 * (3): 15432null
	 * (4): 54321null
	 * 
	 * @param head
	 * @return
	 */
		if (head == null || head.next == null)
			return head;

		ListNode headNext = head.next;
		ListNode newHead = reverseList(headNext);

		head.next = null;
		headNext.next = head;
		return newHead;//返回最终逆序后的新头部节点，不对其进行操作，只层层往上
		返回		
	}
}

#217. Contains Duplicate
Given an array of integers, find if the array contains any duplicates. Your 
function 
should return true if any value appears at least twice in the array, and it 
should return 
false if every element is distinct.
public class Solution {
    public boolean containsDuplicate(int[] nums) {
		if(nums == null)
			return false;
		//使用map结构存储元素是否出现过
		HashMap<Integer, Boolean> hashMap = new HashMap<Integer, Boolean>();
		for (int i = 0; i < nums.length; ++i)
		{
			if (hashMap.containsKey(nums[i]))
				return true;
			else
				hashMap.put(nums[i], true);
		}
		return false;
	}
}

225. Implement Stack using Queues
Implement the following operations of a stack using queues.
push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
empty() -- Return whether the stack is empty.
class MyStack {
	LinkedList<Integer> queue1 = new LinkedList<Integer>();// 队列
	LinkedList<Integer> queue2 = new LinkedList<Integer>();// 队列
	// Push element x onto stack.
	public void push(int x)
	{
		if (queue1.isEmpty())
		{
			queue1.add(x);
			while (!queue2.isEmpty())
				queue1.add(queue2.pop());
		}
		else
		{
			queue2.add(x);
			while (!queue1.isEmpty())
				queue2.add(queue1.pop());
		}
	}
	// Removes the element on top of the stack.
	public void pop()
	{
		if (!queue1.isEmpty())
			queue1.pop();
		else
			queue2.pop();

	}
	// Get the top element.
	public int top()
	{
		if (!queue1.isEmpty())
			return queue1.peek();
		else
			return queue2.peek();
	}
	// Return whether the stack is empty.
	public boolean empty()
	{
		if (queue1.isEmpty() && queue2.isEmpty())
			return true;
		else
			return false;
	}
}

#226. Invert Binary Tree
Invert a binary tree.
     4
   /   \
  2     7
 / \   / \
1   3 6   9
to
     4
   /   \
  7     2
 / \   / \
9   6 3   1
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public TreeNode invertTree(TreeNode root) {
		if (root != null)//当前节点非空
		{
			invertTree(root.left);//反转左子树
			invertTree(root.right);//反转右子树
			
			TreeNode tempNode = root.left;//反转当前节点
			root.left = root.right;
			root.right = tempNode;
			
			return root;//返回翻转后的以当前节点为根的树
		}
		return null;//为空返回null
	}
}

#231. Power of Two
Difficulty: Easy 判断一个数是否是2的幂
Given an integer, write a function to determine if it is a power of two.
public class Solution {
    public boolean isPowerOfTwo(int n) {
		// 判断一个数是不是一个整数的幂
		// if (n == 1)
		// return true;
		// int pow = 0;
		// for (int i = 2; i < n / 2; ++i)
		// {
		// pow = (int) Math.pow(i, 2);
		// if (pow == n)
		// return true;
		// else if (pow > n)
		// return false;
		// }
		// return false;

		if(n == 0)
			return false;
		while (n % 2 == 0)
			n = n / 2;
		if (n == 1)
			return true;
		else
			return false;
	}
}

#232. Implement Queue using Stacks
Difficulty: Easy
Implement the following operations of a queue using stacks.
push(x) -- Push element x to the back of queue.
pop() -- Removes the element from in front of queue.
peek() -- Get the front element.
empty() -- Return whether the queue is empty.
class MyQueue {
	Stack<Integer> stack = new Stack<Integer>();
	Stack<Integer> stackExc = new Stack<Integer>();
	// Push element x to the back of queue.
	public void push(int x)
	{
		if (stack.isEmpty())
			stack.push(x);
		else
		{
			while (!stack.isEmpty()) //倒2次
				stackExc.push(stack.pop());
			stack.push(x);
			while (!stackExc.isEmpty())
				stack.push(stackExc.pop());
		}

	}
	// Removes the element from in front of queue.
	public void pop()
	{
		stack.pop();
	}
	// Get the front element.
	public int peek()
	{
		return stack.peek();
	}
	// Return whether the queue is empty.
	public boolean empty()
	{
		return stack.isEmpty();
	}
}

#234. Palindrome Linked List
Given a singly linked list, determine if it is a palindrome（回文）.
Follow up:
Could you do it in O(n) time and O(1) space?
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public boolean isPalindrome(ListNode head) {
        
        if(head == null)
			return true;
		int length = 1;
		int mid;
		int size;
		ListNode left = head;
		ListNode right;
		while (left.next != null)
		{
			length++;
			left = left.next;
		}
		if (length % 2 == 0) //找到中间位置
			mid = length / 2;
		else
			mid = length / 2 + 1;

		left = head;
		right = head;
		size = length / 2;
		for (int i = 0; i <= mid; ++i)//找到右半边第一个节点
			right = right.next;

		ListNode p;
		ListNode q = left;
		ListNode current = left.next;
		left.next = null;
		for (int i = 1; i < size; ++i)//“头插法”逆序左半边节点
		{
			p = current;
			current = current.next;
			p.next = q;
			q = p;
		}
		left = q;

		for (int i = 0; i < size; ++i)//左右半边节点比较
		{
			if (left.val != right.val)
				return false;
			left = left.next;
			right = right.next;
		}
		return true;
    }
}

#235. Lowest Common Ancestor of a Binary Search Tree
Given a binary search tree (BST), find the lowest common ancestor (LCA) of two 
given nodes in the BST.
According to the definition of LCA on Wikipedia: “The lowest common ancestor is 
defined between two 
nodes v and w as the lowest node in T that has both v and w as descendants 
(where we allow a node to be
a descendant of itself).
        _______6______
       /              \
    ___2__          ___8__
   /      \        /      \
   0      _4       7       9
         /  \
         3   5
For example, the lowest common ancestor (LCA) of nodes 2 and 8 is 6. Another 
example is LCA of nodes 2 
and 4 is 2, since a node can be a descendant of itself according to the LCA 
definition.
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) 
	{

//使用后序遍求最近的公共祖先：普通二叉树
// 		Stack<TreeNode> stack = new Stack<TreeNode>();
// 		TreeNode current = root;// 遍历节点
// 		TreeNode r = null;// 记录上次访问的几点（用以判断是从左子树返回的还是右
子树）
// 		boolean isP = false;
// 		boolean isQ = false;
// 		Stack<TreeNode> pStack = new Stack<TreeNode>();// 记录p的祖先
// 		Stack<TreeNode> qStack = new Stack<TreeNode>();// 记录q的祖先

// 		Stack<TreeNode> pAncestor = new Stack<TreeNode>();// 记录p的祖先
// 		Stack<TreeNode> qAncestor = new Stack<TreeNode>();// 记录q的祖先

// 		while ((current != null) || !stack.isEmpty())
// 		{
// 			if (current != null)// 向左走到底
// 			{
// 				stack.push(current);
// 				current = current.left;
// 			}
// 			else
// 			// 向右
// 			{
// 				current = stack.peek(); // 取栈顶节点
// 				if ((current.right != null) && (current.right != r))// 如果右子
树存在，并且没有访问过
// 				{
// 					current = current.right;// 向右走
// 					stack.push(current);// 压入栈
// 					current = current.left;// 向左走
// 				}
// 				else
// 				// 当右子树不存在，或者右子树已经访问完
// 				{
// 					current = stack.pop();// 弹出该节点
// 					if (p == current)// 当前节点为p时候，记录栈中的所有节点（p的
所有祖先）
// 					{
// 						isP = true;
// 						pStack = (Stack<TreeNode>) stack.clone();
// 						pStack.push(current);//注意要把当前节点放入栈（自身也是
自身的祖先）
// 						while (!pStack.isEmpty())
// 							pAncestor.push(pStack.pop());
// 					}
// 					if (q == current)// 当前节点为q时候，记录栈中的所有节点（q的
所有祖先）
// 					{
// 						isQ = true;
// 						qStack = (Stack<TreeNode>) stack.clone();
// 						qStack.push(current);
// 						while (!qStack.isEmpty())
// 							qAncestor.push(qStack.pop());

// 					}
// 					if (isP && isQ)// 当q和p的祖先都找到时，寻找最近的公共祖先
// 					{
// 						TreeNode lowestCommonAncestor = null;
// 						while (!pAncestor.isEmpty() && !qAncestor.isEmpty())
// 						{
// 							if (pAncestor.peek() == qAncestor.peek())
// 							{
// 								lowestCommonAncestor = pAncestor.pop();
// 								qAncestor.pop();
// 							}
// 							else
// 							{
// 								return lowestCommonAncestor;
// 							}
// 						}
// 						return lowestCommonAncestor;//当一个为空时返回！！
// 					}
// 					r = current;// 记录最近访问的节点
// 					current = null;// 节点访问完，重置p指针
// 				}
// 			}
// 		}

// 		return null;


        //使用递的方法(使用递归的时候注意有无返回值！！)
		//因为是二分查找树，所有有优化方法。
		int min = Math.min(p.val, q.val);
		int max = Math.max(p.val, q.val);
		if(root == null)
			return null;
		else if(root.val >= min && root.val <= max)
			return root;
		else if(root.val < min)
			return lowestCommonAncestor(root.right, p, q);
		//else if(root.val > max)
		else
			return lowestCommonAncestor(root.left, p, q);
	}
}

#237. Delete Node in a Linked List
Write a function to delete a node (except the tail) in a singly linked list, 
given only access to that node.
Supposed the linked list is 1 -> 2 -> 3 -> 4 and you are given the third node 
with value 3, the linked list
should become 1 -> 2 -> 4 after calling your function.
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void deleteNode(ListNode node) {
		ListNode preNode = node;
		ListNode nextNode = node.next;
		while(nextNode != null)
		{
			preNode.val = nextNode.val;//用后面节点的值覆盖前面节点的值
			preNode = nextNode;
			nextNode = nextNode.next;
		}
		preNode.next = null;//把最后的尾节点删除<有问题，应该是把指向preNone的指
		针置为null>
	}
}

#258. Add Digits
Given a non-negative integer num, repeatedly add all its digits until the result 
has only one digit.
For example:
Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one 
digit, return it.
Follow up:
另一个方法比较简单，可以举例说明一下。假设输入的数字是一个5位数字num，则num的各
位分别为a、b、c、d、e。
有如下关系：num = a * 10000 + b * 1000 + c * 100 + d * 10 + e
即：num = (a + b + c + d + e) + (a * 9999 + b * 999 + c * 99 + d * 9)
因为 a * 9999 + b * 999 + c * 99 + d * 9 一定可以被9整除，因此num模除9的结果与 a 
+ b + c + d + e 模除9的结果是一样的。
对数字 a + b + c + d + e 反复执行同类操作，最后的结果就是一个 1-9 的数字加上一串
数字，最左边的数字是 1-9 之间的，
右侧的数字永远都是可以被9整除的。这道题最后的目标，就是不断将各位相加，相加到最
后，当结果小于10时返回因为最后结果在1-9之间，
得到9之后将不会再对各位进行相加，因此不会出现结果为0的情况。因为 (x + y) % z = 
(x % z + y % z) % z，又因为 x % z % z = x % z，
因此结果为 (num - 1) % 9 + 1，只模除9一次，并将模除后的结果加一返回。
Could you do it without any loop/recursion in O(1) runtime?
public class Solution {
    public int addDigits(int num) {
       
       //迭代法 
// 		int sum = 10;
// 		while(sum > 9)
// 		{
// 			sum = 0;
// 			while(num != 0)
// 			{
// 				sum += num%10;
// 				num = num/10;
// 			}
// 			num = sum;
// 		}
		
// 		return sum;

        //公式法 《数字相加 - 数字根》
        return (num - 9*((num-1)/9));
	
    }
}

#263. Ugly Number
Write a program to check whether a given number is an ugly number.
Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For 
example, 6, 8 are ugly 
while 14 is not ugly since it includes another prime factor 7.
Note that 1 is typically treated as an ugly number.
public class Solution {
    public boolean isUgly(int num) {
       
       //超时 
//      if (num <= 0)
// 			return false;
// 		if (num == 1)
// 			return true;

// 		int remainder; // 余数

// 		for (int i = 2; i < num / 2; i++)
// 		{
// 			remainder = num % i; 
// 			if (remainder == 0 && i > 5) //i是因数?
// 			{
// 				for(int j=2; j < i/2; j++)//i是质因数？
// 				{
// 					if((i % j) == 0)
// 						return false;
// 				}
// 			}
// 		}
// 		return true;
	
    	// Good 算法
    	if (num <= 0)
			return false;
		else if (num == 1)
			return true;
		while ((num % 2) == 0)
		{
			num = num / 2;
		}
		while ((num % 3) == 0)
		{
			num = num / 3;
		}
		while ((num % 5) == 0)
		{
			num = num / 5;
		}
		if (num == 1)
			return true;
		else
			return false;
    }
}
#283. Move Zeroes
Given an array nums, write a function to move all 0's to the end of it while 
maintaining the relative order of the non-zero elements.
For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums 
should be [1, 3, 12, 0, 0].
Note:
You must do this in-place without making a copy of the array.
Minimize the total number of operations.
public class Solution {
    public void moveZeroes(int[] nums) {
		int numZero = 0;// 记录0的个数
		int endZero = nums.length - 1;// 记录非0的最后一个数的位置
		for (int i = 0; i < nums.length; ++i)
		{
			if (nums[i] == 0)
				numZero++;
			else
			{
				endZero = i - numZero;
				nums[endZero] = nums[i];
			}
		}
		for (int i = endZero + 1; i < nums.length; ++i)
			nums[i] = 0;
	}
}
 














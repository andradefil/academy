import java.util.*;

class Program {
  public static int[] twoNumberSum(int[] array, int targetSum) {
    // Write your code here.
		Arrays.sort(array);
		int left=0, right=0;
		for (int i=0; i<array.length; i++) {
			if (i==0) {
				left=0;
				right=array.length-1;
			}
			int n = array[left]+array[right];
			if (n == targetSum) {
				return new int[]{array[left], array[right]};
			} else if (n < targetSum) {
				left++;
			} else {
				right--;
			}
		}
    return new int[0];
  }
}

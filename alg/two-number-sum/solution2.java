import java.util.*;

class Program {
  public static int[] twoNumberSum(int[] array, int targetSum) {
    // Write your code here.
		HashMap<Integer, Boolean> map = new HashMap<>();
		for (int i=0; i<array.length; i++) {
			int n = targetSum - array[i];

			if (map.get(n) != null) {
				return new int[]{n,array[i]};
			}
			map.put(array[i], true);
		}
    return new int[0];
  }
}

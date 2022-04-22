import java.util.*;

// O(n) time, O(1) complexity
class Program {
  public static boolean isValidSubsequence(List<Integer> array, List<Integer> sequence) {
    // Write your code here.
    int pa = 0;
    int ps = 0;
    while(pa < array.size() && ps < sequence.size()) {
      if (array.get(pa) == sequence.get(ps)) {
        ps += 1;

      } 
      pa += 1;

    }
    return ps == sequence.size();

  }

}


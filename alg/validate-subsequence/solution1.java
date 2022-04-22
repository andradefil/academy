import java.util.*;

// O(n) time, O(1) complexity
class Program {
  public static boolean isValidSubsequence(List<Integer> array, List<Integer> sequence) {
    int p = 0;
    for (int i=0; i<array.size(); i++) {
      if (p == sequence.size()) {
        break;

      }
      if (sequence.get(p) == array.get(i)) {
        p++;

      }

    }
    return p == sequence.size();

  }

}

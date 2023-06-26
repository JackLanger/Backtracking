package oszimt.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
  public static void main(String[] args) {

    List<int[]> result = damenProblem(8);

    System.out.println(Arrays.deepToString(result.toArray()));
    // todo: create chess boards.
  }


  public static List<int[]> damenProblem(int size) {

    List<int[]> res = new ArrayList<>();

    int[] kombination = new int[size];
    Arrays.fill(kombination, -1);

    outer:
    for (int x = 0; x < size; x++) {

      if (x < size - 1) {
        kombination[x + 1] = -1;
      }

      for (int y = kombination[x]; y < size; y++) {

        if (isValidPosition(kombination, x, y)) {
          kombination[x] = y;

          if (x == size - 1) { // is a full kombination of queens ?
            res.add(deepCopy(kombination));
            x--;

          } else break; // break out of inner loop to continue on horizontal

        } else if (y == size - 1) { // not valid and all possible combinations have been tried

          x -= 2; // reduce i to step back

        }
        if (x < 0) {
          // no valid combinations have been found
          if (kombination[0] == size - 1) break outer;
          else continue outer;
        }
      }
    }


    return res;
  }


  private static int[] deepCopy(int[] array) {

    int[] copy = new int[array.length];
    System.arraycopy(array, 0, copy, 0, copy.length);
    return copy;
  }


  private static boolean isValidPosition(int[] kombination, int i, int j) {


    // check if the placement is not a neighbor of any other node,
    // check if it is not diagonal to any other node, choose the smallest possible value,
    // that is bigger than the previous value. allow 0 !
    return canNotCheck(kombination, j, i) && (j > kombination[i]);
  }


  public static boolean canNotCheck(int[] kombination, int currentVertical, int currentHorizontal) {

    /*

    Diagonaler Vergleich: Ein element das in einem Koordinaten system diagonal zum ursprung liegt hat,
    weist stets ein verhältnis |X/Y| = 1 auf.
    Möchte man wissen, ob ein element zu einem anderem element diagonal benachbart ist,
    muss dieses element ebenfalls ein Verhältnis von X/Y = +-1 aufweisen.
    => x = b-a,  y = f(b)-f(a) , b-a/|f(b)-f(a)| = 1 <=> b-a = |f(b)-f(a)|

    */
    for (int k = currentHorizontal - 1; k >= 0; k--) {

      if (currentVertical == kombination[k]
          || currentHorizontal - k == Math.abs(currentVertical - kombination[k]))

        return false;

    }
    return true;
  }

}

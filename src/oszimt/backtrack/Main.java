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


    int[] path = new int[size];
    Arrays.fill(path, -1);
    outer:
    for (int x = 0; x < size; x++) {

      if (x < size - 1) {
        path[x + 1] = -1;
      }

      for (int y = path[x]; y < size; y++) {

        if (isValidPosition(path, x, y)) {
          path[x] = y;

          if (x == size - 1) {
            res.add(deepCopy(path));
            x -= 2;

          } else
            continue outer;

        } else if (y == size - 1) {

          x -= 2;// reduce i to step back

        }
        if (x < 0) {
          if (path[0] == size - 1) break outer;
          else continue outer;
        }
      }
    }


    return res;
  }


  private static int[] deepCopy(int[] path) {

    int[] copy = new int[path.length];
    System.arraycopy(path, 0, copy, 0, copy.length);
    return copy;
  }


  private static boolean isValidPosition(int[] path, int i, int j) {


    // check if the placement is not a neighbor of any other node,
    // check if it is not diagonal to any other node, choose the smallest possible value,
    // that is bigger than the previous value. allow 0 !
    return canNotCheck(path, j, i) && (j > path[i]);
  }


  private static boolean canNotCheck(int[] path, int currentVertical, int currentHorizontal) {

    /*

    compare all values to the left of the current x coordinate until the first collision is found.
    a collision happens if a row is already taken by another "queen" (path[k] == currentVertical)
    a diagonal collision exists if : currentVertical - (k - currentHorizontal) equals the value of path[k]
    this a diagonal can happen from top to bottom or bottom to top direction so the other direction has to be checked as well
    (currentVertical + (k - currentHorizontal))

    */
    for (int k = currentHorizontal - 1; k >= 0; k--) {
      if (path[k] == currentVertical - (k - currentHorizontal) || path[k] == currentVertical + (k - currentHorizontal) || path[k] - currentVertical == 0)
        return false;
    }
    return true;
  }

}

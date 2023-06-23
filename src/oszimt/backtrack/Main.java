package oszimt.backtrack;

import java.util.Arrays;

public class Main {
  public static void main(String[] args) {

    int[][] result = damenProblem(8);

    System.out.println(Arrays.deepToString(result));
    // todo: create chess boards.
  }


  public static int[][] damenProblem(int size) {
    //TODO: refactor, remove tags and simplify
    int[][] res = new int[size][];
    boolean[] isRevistedZeros = new boolean[size];

    for (int a = 0; a < size; a++) {

      int[] path = new int[size];
      path[0] = a;
      outer:
      for (int x = 1; x < size; x++) {

        if (x < size - 1) {
          path[x + 1] = 0;
          isRevistedZeros[x + 1] = false;
        }

        for (int y = 0; y < size; y++) {

          if (isValidPosition(path, x, y, isRevistedZeros[x])) {
            path[x] = y;
            continue outer;

          } else if (y == size - 1) {

            isRevistedZeros[x - 1] = true;
            x -= 2;// reduce i to step back

          }
        }

      }
      res[a] = path;
    }

    return res;
  }


  private static boolean isValidPosition(int[] path, int i, int j, boolean isRevistedZero) {


    // check if the placement is not a neighbor of any other node,
    // check if it is not diagonal to any other node, choose the smallest possible value,
    // that is bigger than the previous value. allow 0 !
    return canNotCheck(path, j, i) && (j > path[i] || (path[0] != 0 && j == 0 && !isRevistedZero));
  }


  private static boolean canNotCheck(int[] path, int j, int currentIndex) {

    for (int k = currentIndex; k >= 0; k--) {
      if (path[k] == j - (k - currentIndex) || path[k] == j + (k - currentIndex) || path[k] - j == 0)
        return false;
    }
    return true;
  }

}

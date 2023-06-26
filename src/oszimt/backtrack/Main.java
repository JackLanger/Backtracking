package oszimt.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
  public static void main(String[] args) {


    solveNQueens(12);
//    List<int[]> result = damenProblem(8);

//    System.out.println(Arrays.deepToString(result.toArray()));

    // todo: create chess boards.
  }



  public static List<int[]> solveNQueens(int n) {
    List<int[]> solutions = new ArrayList<>();
    int[] queens = new int[n];
    solveNQueensUtil(queens, 0, solutions);
    return solutions;
  }

  private static void solveNQueensUtil(int[] queens, int row, List<int[]> solutions) {
    if (row == queens.length) {
      solutions.add(Arrays.copyOf(queens, queens.length));
      return;
    }

    for (int col = 0; col < queens.length; col++) {
      if (isValidPosition(queens, row, col)) {
        queens[row] = col;
        solveNQueensUtil(queens, row + 1, solutions);
      }
    }
  }

  private static boolean isValidPosition(int[] queens, int row, int col) {
    for (int i = 0; i < row; i++) {
      if (queens[i] == col || queens[i] - col == i - row || queens[i] - col == row - i) {
        return false;
      }
    }
    return true;
  }

}

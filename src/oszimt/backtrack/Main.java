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
        mainLoop:

        for (int a = 0; a < size; a++) {

            int[] path = new int[size];
            path[0] = a;
            outer:
            for (int i = 1; i < size; i++) {

                if (i < size - 1) {
                    path[i + 1] = 0;
                    isRevistedZeros[i+1] = false;
                }

                for (int j = 0; j < size; j++) {

                    if (isValidPosition(path, i, j, isRevistedZeros[i])) {
                        path[i] = j;
                        continue outer;

                    } else if (j == size - 1) {

                        if (i <= 1) {
                            // if first index is already at 7 we do not have an answer.
                            if (path[0] == size - 1)

                                if (j == i) {
                                    continue mainLoop;

                                } else path[0]++;

                        } else {
                            isRevistedZeros[i-1] = true;
                            i -= 2;// reduce i to step back
                        }
                    }
                }

            }
            res[a] = path;
        }

        return res;
    }

    private static boolean isValidPosition(int[] path, int i, int j, boolean isRevistedZero) {
        // check horizontal

        for (int k = 0; k < i; k++) {
            if (path[k] == j) return false;
        }

        // check if the placement is not a neighbor of any other node,
        // check if it is not diagonal to any other node, choose the smallest possible value,
        // that is bigger than the previous value. allow 0 !
        return isNotInDiagonal(path, j, i) && (j > path[i] || (path[0] != 0 && j == 0 && !isRevistedZero));
    }

    private static boolean isNotInDiagonal(int[] path, int j, int currentIndex) {
        for (int k = 0; k < currentIndex; k++) {
            if (path[k] == j - (k - currentIndex) || path[k] == j + (k - currentIndex))
                return false;
        }
        return true;
    }

}

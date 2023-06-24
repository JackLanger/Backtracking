package oszimt.backtrack;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import javax.management.timer.Timer;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.IntToDoubleFunction;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class MainTest {

  void canNotCheck() {

    int[] case1 = {0,0,0,0};
    int[] case2 = {3,0,0,0};
    int[] case3 = {0,2,4,6};

    assertFalse(Main.canNotCheck(case1,4,4));
    assertFalse(Main.canNotCheck(case2,0,4));
    assertFalse(Main.canNotCheck(case2,0,3));
    assertTrue(Main.canNotCheck(case3,1,4));

  }
}
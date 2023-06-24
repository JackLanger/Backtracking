package oszimt.backtrack;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.RepeatedTest;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MainBench {


  static List<Integer> runs = new ArrayList<>();

  @AfterAll
  static void calculateAverage(){
    int avg = 0;
    for (Integer run : runs) {
      avg+= run;
    }
    if(!runs.isEmpty())
      avg /= runs.size();
    System.out.printf("Average runs per 100ms with n = 8: %d\n", avg);
  }
  @RepeatedTest(5)
  void benchmark(){
    int start = LocalTime.now().getNano();
    int i = 0;
    while(LocalTime.now().getNano() - start < 1e8 )
    {
      Main.damenProblem(8);
      i++;
    }
    runs.add(i);
  }
}

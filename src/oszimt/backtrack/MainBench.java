package oszimt.backtrack;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MainBench {


  static List<Integer> runs = new ArrayList<>();

  @AfterEach
  void calculateAverage(){
    int avg = 0;
    for (Integer run : runs) {
      avg+= run;
    }
    if(!runs.isEmpty())
      avg /= runs.size();
    System.out.printf("Average runs per 10ms: %d\n", avg);
    runs.clear();
  }

  @RepeatedTest(5)
  void benchmark8(){
    int start = LocalTime.now().getNano();
    int i = 0;
    while(Math.abs(LocalTime.now().getNano() - start) < 1e8 )
    {
      Main.damenProblem(8);
      i++;
    }
    runs.add(i);
  }

  @RepeatedTest(5)
  void benchmark5(){
    int start = LocalTime.now().getNano();
    int i = 0;
    while(Math.abs(LocalTime.now().getNano() - start) < 1e8 )
    {
      Main.damenProblem(5);
      i++;
    }
    runs.add(i);
  }

  @RepeatedTest(5)
  void benchmark12(){
    int start = LocalTime.now().getNano();
    int i = 0;
    while(Math.abs(LocalTime.now().getNano() - start) < 1e8 )
    {
      Main.damenProblem(12);
      i++;
    }
    runs.add(i);
  }
}

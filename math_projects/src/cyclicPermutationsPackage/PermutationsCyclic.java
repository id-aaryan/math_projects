package cyclicPermutationsPackage;

import java.util.*;

public class PermutationsCyclic {
  public final int[][] integers;

  public PermutationsCyclic(int[] integers) {
    var size = integers.length;
    int[][] integerArray = new int[2][size];
    for (int i = 0; i < size; i++) {
      integerArray[0][i] = i + 1;
    }
    System.arraycopy(integers, 0, integerArray[1], 0, size);
    this.integers = integerArray;
  }

  public Set<List<Integer>> algorithm() {
    System.out.println("Here are the set of products of pairwise-disjoint cycles for: ");
    System.out.println(this.toString() + "\n");
    Set<List<Integer>> setOfCycles = new HashSet<>();

    while (this.isNotEmpty()){
      List<Integer> cycle = new ArrayList<>();
      int index = this.findMinimum();
      final int indexOriginal = index;
      while (true) {
        cycle.add(index + 1);
        var oldIndex = index;
        index = integers[1][index] - 1;
        integers[0][oldIndex] = 0;
        if (integers[1][oldIndex] == indexOriginal + 1) {
          integers[1][oldIndex] = 0;
          setOfCycles.add(cycle);
          break;
        }
        integers[1][oldIndex] = 0;
      }
    }

    return setOfCycles;
  }

  public Integer findMinimum() {
    int index = Integer.MAX_VALUE;
    for (int i = 0; i < integers[0].length; i++) {
      if (0 != integers[0][i] && integers[0][i] < index) {
        index = integers[0][i];
      }
    }
    integers[0][index - 1] = 0;
    return index - 1;
  }

  public boolean isNotEmpty() {
    boolean result = true;
    for (int i = 0; i < integers[0].length; i++) {
      if (integers[0][i] != 0) {
        result = false;
        break;
      }
    }
    return !result;
  }

  @Override
  public String toString() {
    var columnSize = integers[0].length;
    int[] firstRow = new int[columnSize];
    int[] secondRow = new int[columnSize];
    System.arraycopy(integers[0], 0, firstRow, 0, columnSize);
    System.arraycopy(integers[1], 0, secondRow, 0, columnSize);
    return Arrays.toString(firstRow) + "\n" + Arrays.toString(secondRow);
  }
}

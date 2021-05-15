import cyclicPermutationsPackage.PermutationsCyclic;
import eigenPackage.eigenValuesVectors;
import phasePortraitPackage.phasePortrait;

import java.util.List;

public class Main {
  public static void main(String[] args) {

    int[] arr = {2, 1, 4, 3};
    System.out.println(new PermutationsCyclic(arr).algorithm());
    System.out.println("============================");

    // https://mathlets.org/mathlets/linear-phase-portraits-%20matrix-entry/
    var MatrixList = List.of(1.0, -2.0, 3.0, -3.0);

    phasePortrait matrix = new phasePortrait(MatrixList);
    System.out.println("This is the matrix: " + matrix);
    System.out.println(matrix.delta);
    System.out.println("This is the Phase Portrait : " + matrix.evaluate());
    eigenValuesVectors matrix1 = new eigenValuesVectors(MatrixList);
    System.out.println(
        "These are the eigenvalues: "
            + matrix1.eigenValues()
            + ".\nThese are the eigenvectors: "
            + matrix1.eigenVectors(matrix1.eigenValues()));
  }
}

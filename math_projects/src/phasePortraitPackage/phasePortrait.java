package phasePortraitPackage;


import eigenPackage.eigenValuesVectors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class phasePortrait {
  // TODO: only works with 2x2 matrices

  private final List<Double> listOfNumbers;
  public final double[][] matrixA = {{0, 0}, {0, 0}};
  public final double delta;
  public final double trace;
  public HashMap<Boolean, String> profile_type = new HashMap<Boolean, String>();

  public phasePortrait(List<Double> doubles) {
    this.listOfNumbers = doubles;
    for (int i = 0; i < 4; i++) {
      if (i < 2) {
        matrixA[0][i] = doubles.get(i);
      } else {
        matrixA[1][i - 2] = doubles.get(i);
      }
    }
    this.delta = matrixA[0][0] * matrixA[1][1] - matrixA[0][1] * matrixA[1][0];
    this.trace = matrixA[0][0] + matrixA[1][1];

    // building the profiles

    profile_type.put(delta < 0, "(1) Saddle-point");
    profile_type.put(
        delta > 0 && trace > 0 && Math.pow(trace, 2.0) / 4 > delta,
        "(2.1.1) Repelling/Unstable node");
    profile_type.put(
        delta > 0 && trace < 0 && Math.pow(trace, 2.0) / 4 > delta,
        "(2.1.2) Attracting/Stable node");
    profile_type.put(
        trace == 0 && delta > Math.pow(trace, 2) / 4, "(2.2.1) Centre elliptic profile");
    profile_type.put(
        delta > Math.pow(trace, 2) / 4 && trace > 0, "(2.2.2) Repelling or unstable spiral");
    profile_type.put(
        delta > Math.pow(trace, 2) / 4 && trace < 0, "(2.2.3) Attracting or stable spiral");
    profile_type.put(delta == 0 && trace > 0, "(3.1) Line of repelling/unstable fixed points");
    profile_type.put(delta == 0 && trace < 0, "(3.2) Line of attracting/stable fixed points");
    profile_type.put(
        Math.pow(trace, 2) - 4 * delta == 0 && this.diagonizability(),
        "Repelling (4.1.1) and attracting (4.1.2) star node.");
    profile_type.put(
        Math.pow(trace, 2) - 4 * delta == 0 && !this.diagonizability(),
        "Unstable (4.2.1) and stable (4.2.2) degenerate node.");
  }

  public String evaluate() {
    java.util.Set<Boolean> keySet = profile_type.keySet();
    java.util.Collection<String> stringValues = profile_type.values();
    for (int i = 0; i < keySet.size(); i++) {
      if (new ArrayList<>(keySet).get(i)) {
        return new ArrayList<>(stringValues).get(i);
      }
    }
    return "Not available sorry.";
  }

  public String toString() {
    return "\n"
        + matrixA[0][0]
        + " "
        + matrixA[0][1]
        + "\n"
        + matrixA[1][0]
        + " "
        + matrixA[1][1]
        + "\n";
  }

  public boolean diagonizability() {
    var eigen = new eigenValuesVectors(listOfNumbers);
    return eigen.eigenVectors(eigen.eigenValues()).size() == 2;
  }
}

package eigenPackage;

import java.util.ArrayList;
import java.util.List;

public class eigenValuesVectors {
  // COMPLETED: limitations with imaginary eigenvectors
  // TODO: only works with 2x2 matrices
  private final List<Double> listOfNums;
  private complexNumber[][] complexMatrix = {
    {new complexNumber(), new complexNumber()},
    {new complexNumber(), new complexNumber()}
  };
  private final double delta;
  private final double trace;

  public eigenValuesVectors(List<Double> doubles) {
    this.listOfNums = doubles;
    for (int i = 0; i < 4; i++) {
      if (i < 2) {
        //        realMatrix[0][i] = doubles.get(i);
        complexMatrix[0][i] = new complexNumber(doubles.get(i), 0);
      } else {
        //        realMatrix[1][i - 2] = doubles.get(i);
        complexMatrix[1][i - 2] = new complexNumber(doubles.get(i), 0);
      }
    }
    this.delta =
        (complexNumber.multiply(complexMatrix[0][0], complexMatrix[1][1]))
            .minus(complexNumber.multiply(complexMatrix[0][1], complexMatrix[1][0]))
            .getReal();
    this.trace = complexMatrix[0][0].add(complexMatrix[1][1]).negate().getReal();
  }

  public List<complexNumber> eigenValues() {
    List<complexNumber> eigenvalues = new ArrayList<>();

    double traceSquared = Math.pow(trace, 2);

    complexNumber discriminant = new complexNumber();
    var lambda1 = new complexNumber();
    var lambda2 = new complexNumber();

    if (traceSquared >= 4 * delta) {
      discriminant.setReal(Math.pow(traceSquared - 4 * delta, 0.5));
      lambda1.setReal((-trace + discriminant.getReal()) / 2);
      lambda2.setReal((-trace - discriminant.getReal()) / 2);
    } else {
      discriminant.setComplex(Math.pow(4 * delta - traceSquared, 0.5));
      lambda1.setNumber(-trace / 2, discriminant.getComplex() / 2);
      lambda2.setNumber(-trace / 2, -discriminant.getComplex() / 2);
    }

    eigenvalues.add(lambda1);
    eigenvalues.add(lambda2);

    return eigenvalues;
  }

  public List<Pair<complexNumber, complexNumber>> eigenVectors(List<complexNumber> eigenValues) {
    List<Pair<complexNumber, complexNumber>> eigenvectors = new ArrayList<>();
    eigenValuesVectors matrix = new eigenValuesVectors(listOfNums);

    for (complexNumber lambda : eigenValues) {
      matrix.complexMatrix[0][0] =
          matrix.complexMatrix[0][0].minus(lambda.getReal(), lambda.getComplex());
      matrix.complexMatrix[1][1] =
          matrix.complexMatrix[1][1].minus(lambda.getReal(), lambda.getComplex());

      var multiplier =
          new complexNumber(
              matrix.complexMatrix[0][0].getReal(),
              matrix.complexMatrix[0][0].negate().getComplex());
      matrix.complexMatrix[0][0] = complexNumber.multiply(multiplier, matrix.complexMatrix[0][0]);
      matrix.complexMatrix[0][1] = complexNumber.multiply(multiplier, matrix.complexMatrix[0][1]);
      if (checkMultiple(get2(matrix, 0, 0), get2(matrix, 1, 0))) {
        if (checkMultiple(get2(matrix, 1, 1), get2(matrix, 0, 1))) {
          var a = get2(matrix, 0, 0);
          var b = get2(matrix, 0, 1);
          var gcd = gcdByEuclidsAlgorithm(a.getReal(), b.getReal());
          var complexGCD = new complexNumber(1 / gcd);
          Pair<complexNumber, complexNumber> eigenVector =
              new Pair(
                  complexNumber.multiply(b, complexGCD).negate(),
                  complexNumber.multiply(a, complexGCD));
          eigenvectors.add(eigenVector);
        }
      }

      multiplier.invert();
      matrix.complexMatrix[0][0] = complexNumber.multiply(multiplier, matrix.complexMatrix[0][0]);
      matrix.complexMatrix[0][1] = complexNumber.multiply(multiplier, matrix.complexMatrix[0][1]);
      matrix.complexMatrix[0][0] =
          matrix.complexMatrix[0][0].add(lambda.getReal(), lambda.getComplex());
      matrix.complexMatrix[1][1] =
          matrix.complexMatrix[1][1].add(lambda.getReal(), lambda.getComplex());
    }

    return eigenvectors;
  }

  private static double get(eigenValuesVectors matrix, int indexRow, int indexCol) {
    return matrix.complexMatrix[indexRow][indexCol].getReal();
  }

  private complexNumber get2(eigenValuesVectors matrix, int indexRow, int indexCol) {
    return matrix.complexMatrix[indexRow][indexCol];
  }

  //  private static boolean checkRealMultiple(double first, double second) {
  //    return first % second == 0 || second % first == 0;
  //  }
  private static boolean checkMultiple(double first, double second) {
    return first % second == 0 || second % first == 0;
  }

  private double round(double num) {
    // :( Down-casting
    var twoDecimalPlaces = (double) Math.round(num * 1000);
    return twoDecimalPlaces / 1000;
  }

  private boolean checkMultiple(complexNumber first, complexNumber second) {
    var realMultiples =
        first.getReal() % second.getReal() == 0 || second.getReal() % first.getReal() == 0;
    if (first.getComplex() == 0 && second.getComplex() == 0) {
      return realMultiples;
    }
    var complexMultiples =
        round(first.getComplex()) % round(second.getComplex()) == 0
            || round(second.getComplex()) % round(first.getComplex()) == 0;

    return realMultiples && complexMultiples;
  }

  private double gcdByEuclidsAlgorithm(double n1, double n2) {
    if (n2 == 0) {
      return n1;
    }
    return gcdByEuclidsAlgorithm(n2, n1 % n2);
  }
}

package eigenPackage;

public class complexNumber {
  private double real;
  private double complex;

  public complexNumber() {
    this.real = 0;
    this.complex = 0;
  }

  public complexNumber(double real) {
    this.real = real;
    this.complex = 0;
  }

  public complexNumber(double real, double complex) {
    this.real = real;
    this.complex = complex;
  }

  public void setReal(double realComponent) {
    real = realComponent;
  }

  public void setComplex(double complexComponent) {
    complex = complexComponent;
  }

  public void setNumber(double realComponent, double complexComponent) {
    real = realComponent;
    complex = complexComponent;
  }

  public void setNumber(complexNumber number) {
    real = number.getReal();
    complex = number.getComplex();
  }

  public double getReal() {
    return real;
  }

  public double getComplex() {
    return complex;
  }

  public complexNumber add(double realComponent, double complexComponent) {
    return new complexNumber(real + realComponent, complex + complexComponent);
  }

  public complexNumber add(complexNumber number) {
    return new complexNumber(real + number.getReal(), complex + number.getComplex());
  }

  public complexNumber minus(double realComponent, double complexComponent) {
    return new complexNumber(real - realComponent, complex - complexComponent);
  }

  public complexNumber minus(complexNumber number) {
    return new complexNumber(real - number.getReal(), complex - number.getComplex());
  }

  public void invert() {
    var complexPre = getComplex();
    var realPre = getReal();
    complex = -getComplex() / (complexPre * complexPre + getReal() * getReal());
    real = getReal() / (complexPre * complexPre + getReal() * getReal());
  }

  @Override
  public String toString() {
    if (round(complex) == 0.0) {
      return "" + real;
    }
    if (complex < 0) {
      return real + " - " + -1 * round(complex) + "i";
    }
    return real + " + " + round(complex) + "i";
  }

  public double round(double num) {
    // :( Down-casting
    var twoDecimalPlaces = (double) Math.round(num * 1000);
    return twoDecimalPlaces / 1000;
  }

  public static complexNumber multiply(complexNumber multiply1, complexNumber multiply2) {
    // (a+ib)(x+iy) = ax - by + i(ay + bx)
    // real = ax - by; complex = ay + bx;
    var ax = multiply2.getReal() * multiply1.getReal();
    var by = multiply2.getComplex() * multiply1.getComplex();
    var bx = multiply2.getComplex() * multiply1.getReal();
    var ay = multiply2.getReal() * multiply1.getComplex();
    return new complexNumber(ax - by, ay + bx);
  }

  public complexNumber negate() {
    return new complexNumber(getReal() * -1, getComplex() * -1);
  }

}

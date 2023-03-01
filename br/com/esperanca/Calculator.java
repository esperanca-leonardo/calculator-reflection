package br.com.esperanca;

import java.math.BigDecimal;
import java.util.Arrays;

public class Calculator {

  private Long id;

  private String name;

  private String brand;

  private BigDecimal price;

  public Calculator() { }

  public Calculator(Long id, String name, String brand, BigDecimal price) {
    this.id = id;
    this.name = name;
    this.brand = brand;
    this.price = price;
  }

  public BigDecimal sum(BigDecimal n1, BigDecimal n2, BigDecimal... others) {
    BigDecimal result = n1.add(n2);

    return result.add(
      Arrays.stream(others)
        .reduce(BigDecimal.ZERO, BigDecimal::add)
    );
  }

  private BigDecimal add(BigDecimal n1, BigDecimal n2) {
    return n1.add(n2);
  }

  public BigDecimal subtract(BigDecimal n1, BigDecimal n2) {
    return n1.subtract(n2);
  }

  protected BigDecimal multiply(BigDecimal n1, BigDecimal n2) {
    return n1.multiply(n2);
  }

  private BigDecimal divide(BigDecimal n1, BigDecimal n2) throws
     NullPointerException, ArithmeticException {

    if (n1 == null || n2 == null) {
      throw new NullPointerException("Um ou mais campos estão nulos, corrija " +
        "e tente novamente"
      );
    }
    if (n2.equals(BigDecimal.ZERO)) {
      throw new ArithmeticException("Divisão por zero!");
    }
    return n1.divide(n2);
  }

  public BigDecimal mean(BigDecimal n1, BigDecimal n2, BigDecimal... others) {
    int totalNumbers = others.length != 0 ? 2 + others.length : 2;

    BigDecimal totalSum = sum(n1, n2, others);

    return totalSum.divide(BigDecimal.valueOf(totalNumbers));
  }

  public BigDecimal findMin(BigDecimal n1, BigDecimal n2, BigDecimal... others) {
    BigDecimal min = n1.min(n2);

    return Arrays.stream(others)
      .reduce(min, BigDecimal::min);
  }

  public BigDecimal findMax(BigDecimal n1, BigDecimal n2, BigDecimal... others) {
    BigDecimal max = n1.max(n2);

    return Arrays.stream(others)
      .reduce(max, BigDecimal::max);
  }
}

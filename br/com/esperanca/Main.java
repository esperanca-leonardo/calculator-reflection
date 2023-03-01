package br.com.esperanca;

import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

  /**
   * - Implementar uma classe Calculadora com métodos aritméticos
   * - adição, subtração, multiplicação, divisão
   * - (extra) média, menor número, maior número, ... (List)
   * - trabalhar com BigDecimal
   * - trabalhar com varargs...
   * - Realizar reflexão de Class, Field (?), Method
   */
  public static void main(String[] args) {
    try {
      final var aClass = Calculator.class;
//
//      printMethods(aClass);
//      printConstructors(aClass);

      BigDecimal num1 = new BigDecimal("4");
      BigDecimal num2 = new BigDecimal("3");
      BigDecimal num3 = new BigDecimal("2");
      BigDecimal num4 = new BigDecimal("1");

      var calculator = aClass.newInstance();
      System.out.println(calculator.findMax(num1, num2, num3, num4));
    }
    catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  private static void printConstructors(Class<Calculator> aClass) {
    for (var constructor : aClass.getDeclaredConstructors()) {
      int modifiers = constructor.getModifiers();

      final String modifier = getModifer(modifiers);
      System.out.print(getModifer(modifiers));

      String[] constructorNameSplitted = constructor.getName().split("\\.");
      int lastIndex = constructorNameSplitted.length - 1;

      System.out.print(constructorNameSplitted[lastIndex]);
      System.out.print("(");

      List<String> constructorParameters = getStringClassList(
        constructor.getParameterTypes(), " "
      );

      constructorParameters.forEach(System.out::print);
      System.out.println(")");
    }
  }

  private static void printMethods(Class<Calculator> aClass) {
    for (var method : aClass.getDeclaredMethods()) {
      int modifiers = method.getModifiers();

      final String modifer = getModifer(modifiers);

      System.out.print(modifer);
      System.out.print(method.getReturnType().getSimpleName() + " ");
      System.out.print(method.getName());
      System.out.print("(");

      getStringClassList(method.getParameterTypes(), " ")
        .forEach(System.out::print);

      System.out.print(")");

      if (method.getExceptionTypes().length != 0) {
        System.out.print(" throws ");

        getStringClassList(method.getExceptionTypes(), " ")
          .forEach(System.out::print);
      }
      System.out.println();
    }
  }

  private static String getModifer(int modifiers) {
    return Modifier.isPrivate(modifiers) ? "private "
      : Modifier.isPublic(modifiers) ? "public "
      : Modifier.isProtected(modifiers) ? "protected "
      : "default";
  }

  public static List<String> getStringClassList(Class<?>[] aClass,
      String stringToConcat) {

    return Arrays.stream(aClass)
      .map(Class::getSimpleName)
      .map(string -> string.concat(stringToConcat))
      .collect(Collectors.toList()
    );
  }
}

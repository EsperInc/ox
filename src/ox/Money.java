package ox;

import static ox.util.Utils.money;
import static ox.util.Utils.parseMoney;
import java.util.function.Function;

public class Money {

  private final int cents;

  private Money(int cents) {
    this.cents = cents;
  }

  public Money negate(){
    return new Money(-cents);
  }

  public boolean isGreaterThan(Money m) {
    return this.cents > m.cents;
  }

  public boolean isLessThan(Money m) {
    return this.cents < m.cents;
  }

  public int toInt() {
    return cents;
  }

  public double toDouble() {
    return cents / 100.0;
  }

  @Override
  public String toString() {
    return money(toDouble());
  }

  public static <T> Money sum(Iterable<T> items, Function<T, Money> mappingFunction) {
    int ret = 0;
    for (T item : items) {
      ret += mappingFunction.apply(item).toInt();
    }
    return fromInt(ret);
  }

  public static Money fromInt(int totalCents) {
    return new Money(totalCents);
  }

  public static Money dollars(int dollars) {
    return new Money(dollars * 100);
  }

  public static Money fromDouble(double d) {
    return fromInt((int) (d * 100));
  }

  public static Money parse(String s) {
    return fromDouble(parseMoney(s));
  }

}

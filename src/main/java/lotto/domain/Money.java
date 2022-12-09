package lotto.domain;

public class Money {
    private final int money;

    public Money(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public boolean isDivisible(int divisor) {
        return money % divisor == 0;
    }

    public boolean isZero() {
        return money == 0;
    }

}

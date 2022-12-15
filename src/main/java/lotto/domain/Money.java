package lotto.domain;

public class Money {
    private final int money;

    public Money(int money) {
        validate(money);
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    private void validate(int money) {
        if (money < 0){
            throw new IllegalArgumentException("[ERROR] 잘못된 금액입니다.");
        }
    }

    public boolean isDivisible(int divisor) {
        return money % divisor == 0;
    }

    public boolean isZero() {
        return money == 0;
    }

}

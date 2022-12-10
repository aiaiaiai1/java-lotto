package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class MoneyTest {
    @DisplayName("돈 객체 만들기")
    @Test
    void createMoney() {
        Money money = new Money(5000);
        int result = money.getMoney();
        assertThat(result).isEqualTo(5000);
    }

    @DisplayName("돈이 나누어 떨어지는 경우 확인")
    @ParameterizedTest(name = "5000원일때 {0}원 이면 {1}")
    @CsvSource(value = {"1000,true", "2000,false"})
    void checkDivisibility(int divisor, boolean isDivisible) {
        Money money = new Money(5000);
        boolean result = money.isDivisible(divisor);
        assertThat(result).isEqualTo(isDivisible);
    }

    @DisplayName("돈이 0원인지 아닌지 확인")
    @ParameterizedTest(name = "{0}원이면 {1}")
    @CsvSource(value = {"0,true", "2000,false"})
    void checkZero(int money, boolean isZero) {
        boolean result = new Money(money).isZero();
        assertThat(result).isEqualTo(isZero);
    }

}

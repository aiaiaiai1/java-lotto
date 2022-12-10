package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoMachineTest {
    LottoMachine lottoMachine = new LottoMachine() {
        protected Lotto generateRandomLotto() {
            return new Lotto(List.of(2, 1, 3, 4, 5, 6));
        }
    };

    @DisplayName("로또 구입금액이 0인 경우와 1000원으로 나누어 떨어지지 않는 경우 예외 발생")
    @ParameterizedTest(name = "{0}원인 경우")
    @ValueSource(ints = {0, 1500})
    void purchaseLottoByInValidMoney(int money) {
        Money purchasingMoney = new Money(money);
        assertThatThrownBy(
                () -> lottoMachine.purchaseLotto(purchasingMoney)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 구매하기")
    @Test
    void purchaseLotto() {
        lottoMachine.purchaseLotto(new Money(2000));
        List<Lotto> result = lottoMachine.getLottoTable();

        List<Lotto> expected = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(1, 2, 3, 4, 5, 6)));

        assertThat(result).usingRecursiveComparison().isEqualTo(expected);

    }

}

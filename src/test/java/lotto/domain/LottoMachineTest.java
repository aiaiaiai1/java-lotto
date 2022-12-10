package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoMachineTest {
    LottoMachine lottoMachine;

    @BeforeEach
    void setLottoTable() {
        lottoMachine = new LottoMachine(new NumbersGenerator() {
            List<List<Integer>> lottoTable = List.of(
                    List.of(1, 2, 3, 4, 6, 5),
                    List.of(7, 8, 9, 13, 11, 12),
                    List.of(11, 12, 13, 14, 15, 16),
                    List.of(1, 2, 3, 7, 5, 6),
                    List.of(1, 2, 8, 3, 11, 10)
            );
            int index = 0;

            @Override
            public List<Integer> generate() {
                return lottoTable.get(index++);
            }
        });
    }

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
                new Lotto(List.of(7, 8, 9, 11, 12, 13))
        );
        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }

    @DisplayName("로또 결과 확인하기")
    @Test
    void checkResult() {
        lottoMachine.purchaseLotto(new Money(5000));
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 5, 6, 45), 7);
        List<Rank> rankTable = List.of(
                Rank.THIRD,
                Rank.NONE,
                Rank.NONE,
                Rank.FIFTH,
                Rank.SECOND
        );
        LottoResult result = lottoMachine.getResult(winningLotto);
        LottoResult expected = new LottoResult(rankTable);
        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }
}

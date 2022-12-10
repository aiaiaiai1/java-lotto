package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class WinningLottoTest {
    @DisplayName("당첨번호에 보너스번호가 존재하면 예외 발생")
    @Test
    void createWinningLottoByDuplication() {
        assertThatThrownBy(() -> new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 6))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 등수 확인하기")
    @ParameterizedTest
    @MethodSource("setData")
    void checkLottoRank(List<Integer> numbers, Rank expected) {
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
        Lotto lotto = new Lotto(numbers);
        Rank result = winningLotto.getRank(lotto);
        assertThat(result).isEqualTo(expected);
    }

    static Stream<Arguments> setData() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), Rank.FIRST),
                Arguments.of(List.of(1, 2, 3, 4, 5, 7), Rank.SECOND),
                Arguments.of(List.of(1, 2, 3, 4, 5, 8), Rank.THIRD),
                Arguments.of(List.of(1, 2, 3, 4, 9, 7), Rank.FOURTH),
                Arguments.of(List.of(1, 2, 3, 7, 12, 13), Rank.FIFTH),
                Arguments.of(List.of(1, 2, 7, 15, 16, 17), Rank.NONE));
    }
}

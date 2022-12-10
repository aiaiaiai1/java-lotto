package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class RankTest {

    @DisplayName("로또번호 일치개수가 2개이하인 경우 꽝")
    @ParameterizedTest(name = "{0}개인 경우")
    @ValueSource(ints = {2, 1, 0})
    void checkNoneRank(int countOfMatch) {
        Rank result = Rank.of(countOfMatch, 1);
        assertThat(result).isEqualTo(Rank.NONE);
    }

    @DisplayName("로또번호 일치개수가 0~6사이가 아닌경우 예외발생")
    @ParameterizedTest(name = "{0}개인 경우")
    @ValueSource(ints = {-1, 7})
    void checkRankByInvalidCountOfMatch(int countOfMatch) {
        assertThatThrownBy(() -> Rank.of(countOfMatch, 1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스번호 일치개수가 0~1사이가 아닌경우 예외발생")
    @ParameterizedTest(name = "{0}개인 경우")
    @ValueSource(ints = {-1, 2})
    void checkRankByInvalidCountOfBonusMatch(int countOfBounsMatch) {
        assertThatThrownBy(() -> Rank.of(4, countOfBounsMatch))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또등수 확인")
    @ParameterizedTest
    @MethodSource("setData")
    void checkNoneRank(int countOfMatch, int countOfBonusMatch, Rank expected) {
        Rank result = Rank.of(countOfMatch, countOfBonusMatch);
        assertThat(result).isEqualTo(expected);
    }

    static Stream<Arguments> setData() {
        return Stream.of(
                Arguments.of(6, 0, Rank.FIRST),
                Arguments.of(5, 1, Rank.SECOND),
                Arguments.of(5, 0, Rank.THIRD),
                Arguments.of(4, 0, Rank.FOURTH),
                Arguments.of(3, 0, Rank.FIFTH)
        );
    }

}

package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoResultTest {
    @DisplayName("로또 당첨등수 횟수 확인하기")
    @ParameterizedTest
    @MethodSource("setData")
    void checkResult(Rank rank, int expected) {
        List<Rank> rankTable = List.of(
                Rank.FIFTH,
                Rank.NONE,
                Rank.FIFTH,
                Rank.NONE,
                Rank.NONE,
                Rank.FIRST
        );
        LottoResult lottoResult = new LottoResult(rankTable);
        int result = lottoResult.getCountOfRank(rank);
        assertThat(result).isEqualTo(expected);
    }

    static Stream<Arguments> setData() {
        return Stream.of(
                Arguments.of(Rank.FIRST, 1),
                Arguments.of(Rank.SECOND, 0),
                Arguments.of(Rank.THIRD, 0),
                Arguments.of(Rank.FOURTH, 0),
                Arguments.of(Rank.FIFTH, 2),
                Arguments.of(Rank.NONE, 3)
        );
    }

}

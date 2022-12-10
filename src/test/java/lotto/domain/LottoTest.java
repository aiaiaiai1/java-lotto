package lotto.domain;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @DisplayName("로또 번호의 개수가 6개가 넘어가면 예외가 발생한다.")
    @Test
    void createLottoByOverSize() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void createLottoByDuplicatedNumber() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또가 올바르게 발행되는 경우")
    @Test
    void createLotto() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        List<Integer> result = new ArrayList<>();
        for (LottoNumber lottoNumber : lotto.getNumbers()) {
            result.add(lottoNumber.getNumber());
        }
        assertThat(result).isEqualTo(List.of(1, 2, 3, 4, 5, 6));
    }

    @DisplayName("로또에 해당 로또번호가 있는지 확인")
    @ParameterizedTest(name = "{displayName} {index}")
    @CsvSource(value = {"6,true", "7,false"})
    void checkContainLottoNumber(int number, boolean expected) {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        boolean result = lotto.contains(new LottoNumber(number));
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("일치하는 로또번호 개수 확인")
    @Test
    void checkCountOfMatch() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int result = lotto.compare(new Lotto(List.of(1, 2, 3, 7, 8, 9)));
        assertThat(result).isEqualTo(3);
    }
}

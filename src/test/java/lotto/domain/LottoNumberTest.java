package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {
    @DisplayName("로또번호가 1~45 사이의 번호가 아닌 경우 예외 발생")
    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    void createLottoNumberByInvalidNumber(int number) {
        assertThatThrownBy(
                () -> new LottoNumber(number)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또번호가 1~45 사이의 번호인 경우")
    @ParameterizedTest
    @ValueSource(ints = {1, 20, 45})
    void createLottoNumber(int number) {
        LottoNumber lottoNumber = new LottoNumber(number);
        int result = lottoNumber.getNumber();
        assertThat(result).isEqualTo(number);
    }
}

package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RandomLottoNumbersGeneratorTest {
    static RandomLottoNumbersGenerator randomLottoNumbersGenerator = new RandomLottoNumbersGenerator();
    static List<Integer> numbers;

    @DisplayName("6자리 중복없는 1~45의 랜덤번호 생성하기")
    @BeforeAll
    static void generateNumbers() {
        numbers = randomLottoNumbersGenerator.generate();
    }

    @DisplayName("1~45범위 인지 확인")
    @Test
    void isInRange() {
        boolean result = numbers.stream().allMatch(number -> number >= 1 && number < 45);
        assertThat(result).isTrue();
    }

    @DisplayName("6자리 인지 확인")
    @Test
    void isCountSix() {
        int result = numbers.size();
        assertThat(result).isEqualTo(6);
    }

    @DisplayName("중복이 아닌지 확인")
    @Test
    void isUnique() {
        boolean result = numbers.stream().allMatch(number -> number >= 1 && number < 45);
        assertThat(result).isTrue();
    }

}

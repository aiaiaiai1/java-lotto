package lotto.domain;

import java.util.*;

public class Lotto {
    private static final int COUNT_OF_NUMBERS = 6;
    private final List<LottoNumber> numbers = new ArrayList<>();

    public Lotto(List<Integer> numbers) {
        validateCount(numbers);
        validateDuplication(numbers);
        set(numbers);
    }

    private void set(List<Integer> numbers) {
        List<Integer> mutableNumbers = new ArrayList<>(numbers);
        Collections.sort(mutableNumbers);
        for (int number : mutableNumbers) {
            this.numbers.add(new LottoNumber(number));
        }
    }

    private void validateCount(List<Integer> numbers) {
        if (numbers.size() != COUNT_OF_NUMBERS) {
            throw new IllegalArgumentException();
        }
    }

    private void validateDuplication(List<Integer> numbers) {
        if (numbers.size() != numbers.stream().distinct().count()) {
            throw new IllegalArgumentException();
        }
    }
    public List<LottoNumber> getNumbers(){
        return Collections.unmodifiableList(numbers);
    }
}

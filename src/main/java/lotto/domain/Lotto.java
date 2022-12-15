package lotto.domain;

import java.util.*;

public class Lotto {
    public static final int PRICE = 1000;
    public static final int COUNT_OF_NUMBERS = 6;
    private static final String INVALID_COUNT_OF_NUMBERS_ERROR = "[ERROR] 잘못된 로또번호 개수입니다.";
    private static final String DUPLICATE_NUMBER_ERROR = "[ERROR] 로또 번호에 중복된 번호가 존재합니다";
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
            throw new IllegalArgumentException(INVALID_COUNT_OF_NUMBERS_ERROR);
        }
    }

    private void validateDuplication(List<Integer> numbers) {
        if (numbers.size() != numbers.stream().distinct().count()) {
            throw new IllegalArgumentException(DUPLICATE_NUMBER_ERROR);
        }
    }

    public List<LottoNumber> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

    public boolean contains(LottoNumber targetLottoNumber) {
        for (LottoNumber lottoNumber : numbers) {
            if (lottoNumber.equals(targetLottoNumber))
                return true;
        }
        return false;
    }

    public int compare(Lotto lotto) {
        int count = 0;
        for (LottoNumber lottoNumber : lotto.getNumbers()) {
            if (contains(lottoNumber)) {
                count++;
            }
        }
        return count;
    }

}

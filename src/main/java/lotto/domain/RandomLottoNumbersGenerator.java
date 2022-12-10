package lotto.domain;

import java.util.List;

import camp.nextstep.edu.missionutils.Randoms;

public class RandomLottoNumbersGenerator implements NumbersGenerator {
    public List<Integer> generate() {
        return Randoms.pickUniqueNumbersInRange(
                LottoNumber.LOWER_INCLUSIVE, LottoNumber.UPPER_INCLUSIVE, Lotto.COUNT_OF_NUMBERS);
    }

}

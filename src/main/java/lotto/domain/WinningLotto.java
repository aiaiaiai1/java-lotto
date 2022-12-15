package lotto.domain;

import java.util.List;

public class WinningLotto {
    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(List<Integer> numbers, int bounsNumber) {
        validate(numbers, bounsNumber);
        this.winningLotto = new Lotto(numbers);
        this.bonusNumber = new LottoNumber(bounsNumber);
    }

    private void validate(List<Integer> numbers, int bounsNumber) {
        if (numbers.contains(bounsNumber)) {
            throw new IllegalArgumentException("[ERROR] 로또 번호에 보너스 번호가 포함되어 있습니다.");
        }
    }

    public Rank getRank(Lotto lotto) {
        int countOfmatch = lotto.compare(winningLotto);
        int countOfBonusMatch = 0;
        if (lotto.contains(bonusNumber)) {
            countOfBonusMatch++;
        }
        return Rank.of(countOfmatch, countOfBonusMatch);
    }
}

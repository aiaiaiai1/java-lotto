package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoMachine {
    private List<Lotto> lottoTable;
    private final NumbersGenerator numbersGenerator;

    public LottoMachine(NumbersGenerator numbersGenerator) {
        this.numbersGenerator = numbersGenerator;
    }

    public void purchaseLotto(Money money) {
        validateMoney(money);
        int countOfLotto = money.getMoney() / Lotto.PRICE;
        List<Lotto> lottoTable = new ArrayList<>();
        for (int count = 0; count < countOfLotto; count++) {
            lottoTable.add(generateRandomLotto());
        }
        this.lottoTable = lottoTable;
    }

    private Lotto generateRandomLotto() {
        return new Lotto(numbersGenerator.generate());
    }

    private void validateMoney(Money money) {
        if (!money.isDivisible(Lotto.PRICE) || money.isZero()) {
            throw new IllegalArgumentException("[ERROR] 1000원 단위로 입력해주세요");
        }
    }

    public List<Lotto> getLottoTable() {
        return Collections.unmodifiableList(lottoTable);
    }

    public LottoResult getResult(WinningLotto winningLotto) {
        List<Rank> rankTable = new ArrayList<>();
        for (Lotto lotto : lottoTable) {
            rankTable.add(winningLotto.getRank(lotto));
        }
        return new LottoResult(rankTable);
    }


}

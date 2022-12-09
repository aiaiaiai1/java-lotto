package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoMachine {
    private List<Lotto> lottoTable;
    private final RandomNumbersGenerator randomNumbersGenerator = new RandomNumbersGenerator();

    public void purchaseLotto(Money money) {
        validateMoney(money);
        int countOfLotto = money.getMoney() / Lotto.PRICE;
        List<Lotto> lottoTable = new ArrayList<>();
        for (int count = 0; count < countOfLotto; count++) {
            lottoTable.add(generateRandomLotto());
        }
        this.lottoTable = lottoTable;
    }

    protected Lotto generateRandomLotto() {
        return new Lotto(randomNumbersGenerator.generateUniqueNumbers());
    }

    private void validateMoney(Money money) {
        if (!money.isDivisible(Lotto.PRICE) || money.isZero()) {
            throw new IllegalArgumentException();
        }
    }

    public List<Lotto> getLottoTable() {
        return Collections.unmodifiableList(lottoTable);
    }
}

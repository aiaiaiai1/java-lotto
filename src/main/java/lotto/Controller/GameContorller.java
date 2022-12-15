package lotto.Controller;

import lotto.domain.LottoMachine;
import lotto.domain.RandomLottoNumbersGenerator;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class GameContorller {
    OutputView outputView = new OutputView();
    InputView inputView = new InputView();
    LottoMachine lottoMachine = new LottoMachine(new RandomLottoNumbersGenerator());


    public void purchaseLotto() {
        try {
            lottoMachine.purchaseLotto(inputView.readMoney());
        } catch (IllegalArgumentException invalidMoney) {
            inputView.printErrorBy(invalidMoney);
            purchaseLotto();
        }
    }

    public void printPurchasedLottoResult() {
        outputView.printNewLine();
        outputView.printPurchasedLottoAmount(lottoMachine.getLottoTable());
        outputView.printLottoTable(lottoMachine.getLottoTable());
        outputView.printNewLine();
    }

    public WinningLotto getWinningLotto() {
        try {
            inputView.notifyInputWinningLottoNumbers();
            List<Integer> lottoNumbers = inputView.readCsvNumbers();
            inputView.notifyInputBounusNumber();
            int bonusNumber = inputView.readOnlyNumber();
            return new WinningLotto(lottoNumbers, bonusNumber);
        } catch (IllegalArgumentException invalidNumbers) {
            inputView.printErrorBy(invalidNumbers);
            return getWinningLotto();
        }
    }

    public void checkResult() {
        outputView.printLottoResult(lottoMachine.getResult(getWinningLotto()));
    }

    public void run() {
        purchaseLotto();
        printPurchasedLottoResult();
        checkResult();
    }

}

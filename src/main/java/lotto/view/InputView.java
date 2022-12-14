package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.domain.Money;
import lotto.domain.WinningLotto;

import java.util.ArrayList;
import java.util.List;

public class InputView {
    public static final String INPUT_WINNING_LOTTO_NUMBERS_NOTICE = "당첨 번호를 입력해 주세요.";
    public static final String INPUT_BONUS_NUMBER_NOTICE = "보너스 번호를 입력해 주세요.";
    public static final String INPUT_MONEY_FOR_PURCAHING_NOTICE = "구입금액을 입력해 주세요.";
    public static final String HAS_NO_COMMA_ERROR = ",로 구분해주세요";
    public static final String INPUT_ONLY_NUMBERS_ERROR = "숫자만 입력해주세요";


    public WinningLotto readWinningLotto() {
        List<Integer> numbers = readCsvNumbers();
        int bonusNumber = readOnlyNumber();
        return new WinningLotto(numbers, bonusNumber);
    }

    public Money readMoney() {
        int money = readOnlyNumber();
        return new Money(money);
    }

    public List<Integer> readCsvNumbers() {
        List<Integer> numbers = new ArrayList<>();
        try {
            for (String string : readCsv()) {
                numbers.add(toInteger(string));
            }
            return numbers;
        } catch (IllegalArgumentException notNumber) {
            printErrorBy(notNumber);
            return readCsvNumbers();
        }
    }

    public String[] readCsv() {
        try {
            String csv = Console.readLine();
            if (!csv.contains(",")) {
                throw new IllegalArgumentException(HAS_NO_COMMA_ERROR);
            }
            return csv.split(",");
        } catch (IllegalArgumentException notCsv) {
            printErrorBy(notCsv);
            return readCsv();
        }
    }

    public int readOnlyNumber() {
        try {
            return toInteger(Console.readLine());
        } catch (IllegalArgumentException notNumber) {
            printErrorBy(notNumber);
            return readOnlyNumber();
        }
    }

    public void printErrorBy(Exception e) {
        System.out.println(e.getMessage());
    }

    private int toInteger(String string) {
        try {
            return Integer.parseInt(string);
        } catch (IllegalArgumentException notNumber) {
            throw new IllegalArgumentException(INPUT_ONLY_NUMBERS_ERROR);
        }
    }
}

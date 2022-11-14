package lotto.domain;

import java.util.List;

public class ProfitCalculator {
    private static final int FIRST_PRIZE_MONEY = 2_000_000_000;
    private static final int SECOND_PRIZE_MONEY = 30_000_000;
    private static final int THIRD_PRIZE_MONEY = 1_500_000;
    private static final int FOURTH_PRIZE_MONEY = 50_000;
    private static final int FIFTH_PRIZE_MONEY = 5_000;
    private static final int ONE_HUNDRED = 100;

    public float toRateOfProfit(List<Integer> result) {
        int amount = 0;
        float sum = 0;
        int[] prizeMoney = {
                FIFTH_PRIZE_MONEY, FOURTH_PRIZE_MONEY, THIRD_PRIZE_MONEY, SECOND_PRIZE_MONEY, FIRST_PRIZE_MONEY};
        for (int i = 0; i < result.size(); i++) {
            if (i == result.size() - 1) {
                amount = result.get(i);
                continue;
            }
            sum += result.get(i) * prizeMoney[i];
        }
        return sum / (amount * Lotto.PRICE) * ONE_HUNDRED;
    }
}



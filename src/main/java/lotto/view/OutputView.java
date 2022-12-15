package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.LottoResult;
import lotto.domain.Rank;

import java.util.ArrayList;
import java.util.List;

public class OutputView {
    public static final String NEW_LINE = "\n";
    public static final String PURCAHED_LOTTO_AMOUNT_NOTICE = "%s개를 구매했습니다." + NEW_LINE;

    enum RankNotice {
        FIFTH("3개 일치 (5,000원) - %d개" + NEW_LINE, Rank.FIFTH),
        FOURTH("4개 일치 (50,000원) - %d개" + NEW_LINE, Rank.FOURTH),
        THIRD("5개 일치 (1,500,000원) - %d개" + NEW_LINE, Rank.THIRD),
        SECOND("5개 일치, 보너스 볼 일치 (30,000,000원) - %d개" + NEW_LINE, Rank.SECOND),
        FIRST("6개 일치 (2,000,000,000원) - %d개" + NEW_LINE, Rank.FIRST);
        private final String notice;
        private final Rank rank;

        RankNotice(String string, Rank rank) {
            notice = string;
            this.rank = rank;
        }
    }


    public void printLottoTable(List<Lotto> lottoTable) {
        for (Lotto lotto : lottoTable) {
            List<Integer> numbers = new ArrayList<>();
            for (LottoNumber lottoNumber : lotto.getNumbers()) {
                numbers.add(lottoNumber.getNumber());
            }
            System.out.println(numbers);
        }

    }

    public void printPurchasedLottoAmount(List<Lotto> lottoTable) {
        System.out.printf(PURCAHED_LOTTO_AMOUNT_NOTICE, lottoTable.size());
    }

    public void printLottoResult(LottoResult lottoResult) {
        for (RankNotice rankNotice : RankNotice.values())
            System.out.printf(rankNotice.notice, lottoResult.getCountOfRank(rankNotice.rank));
    }

    public void printNewLine() {
        System.out.println();
    }
}

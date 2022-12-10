package lotto.domain;

import java.util.*;

public class LottoResult {
    private final Map<Rank, Integer> result = new HashMap<>() {
        {
            for (Rank rank : Rank.values()) {
                put(rank, 0);
            }
        }
    };

    public LottoResult(List<Rank> rankTable) {
        for (Rank rank : rankTable) {
            result.put(rank, Collections.frequency(rankTable, rank));
        }
    }

    public int getCountOfRank(Rank rank) {
        return result.get(rank);
    }
}


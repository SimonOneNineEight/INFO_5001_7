package model.MarketModel;

import java.util.Comparator;

public class MarketSummaryComparator implements Comparator<MarketSummary> {

    @Override
    public int compare(MarketSummary mkt1, MarketSummary mkt2) {
        return Integer.compare(mkt2.getRevenue(), mkt1.getRevenue());
    }

}

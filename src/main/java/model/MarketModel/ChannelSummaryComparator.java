package model.MarketModel;

import java.util.Comparator;

public class ChannelSummaryComparator implements Comparator<ChannelSummary> {

    @Override
    public int compare(ChannelSummary channel1, ChannelSummary channel2) {
        return Integer.compare(channel2.getRevenue(), channel1.getRevenue());
    }

}

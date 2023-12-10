
package model.MarketModel;

import java.util.ArrayList;
import java.util.Collections;

import model.OrderManagement.Order;
import model.OrderManagement.OrderSummaryComparator;

/**
 *
 * @author kal bugrara
 */
public class ChannelOrderReport {
    ArrayList<ChannelSummary> channelSummaryList;

    public ChannelOrderReport() {
        channelSummaryList = new ArrayList<ChannelSummary>();
    }

    public void generateOrderReport(ArrayList<Order> orders, ArrayList<MarketChannelAssignment> mcas) {
        ChannelSummary channelSummary;

        ChannelSummaryComparator comparator = new ChannelSummaryComparator();

        for (MarketChannelAssignment mca : mcas) {
            channelSummary = new ChannelSummary(mca, orders);
            channelSummaryList.add(channelSummary);
        }
        Collections.sort(channelSummaryList, comparator);
    }

    public void printOrderReport() {
        System.out.println("Channel Order Report");
        for (ChannelSummary channelSummary : channelSummaryList) {
            int index = channelSummaryList.indexOf(channelSummary);
            System.out.print((index + 1) + ". ");
            channelSummary.printOrderSummary();
        }
    }
}

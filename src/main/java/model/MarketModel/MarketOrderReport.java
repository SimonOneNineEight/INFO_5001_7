
package model.MarketModel;

import java.util.ArrayList;
import java.util.Collections;

import model.OrderManagement.Order;
import utils.Utils;

/**
 *
 * @author kal bugrara
 */
public class MarketOrderReport {
    ArrayList<MarketSummary> marketSummaryList;

    public MarketOrderReport() {
        marketSummaryList = new ArrayList<MarketSummary>();
    }

    public void generateOrderReport(ArrayList<Order> orders, ArrayList<Market> markets) {
        MarketSummary marketSummary;

        MarketSummaryComparator comparator = new MarketSummaryComparator();

        for (Market market : markets) {
            marketSummary = new MarketSummary(market, orders);
            marketSummaryList.add(marketSummary);
        }
        Collections.sort(marketSummaryList, comparator);
    }

    public void printOrderReport() {
        System.out.println("Market Order Report: ");
        for (MarketSummary marketSummary : marketSummaryList) {
            int index = marketSummaryList.indexOf(marketSummary);
            System.out.print("Market " + (index + 1) + ". ");
            marketSummary.printOrderSummary();
            Utils.printDoubleDivisionLine();
        }
    }
}

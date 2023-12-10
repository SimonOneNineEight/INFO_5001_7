/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.MarketModel;

import java.util.ArrayList;

import model.OrderManagement.Order;
import utils.Utils;

/**
 *
 * @author kal bugrara
 */
public class MarketSummary {
    private Market market;
    private ArrayList<ChannelSummary> channelSummaries;
    private int revenue;
    private int revenueWithAds;

    public MarketSummary(Market market, ArrayList<Order> orders) {
        ArrayList<Order> ordersInThisMarket = new ArrayList<>();

        for (Order order : orders) {
            if (order.getMarketChannelAssignment().getMarket().equals(market)) {
                ordersInThisMarket.add(order);
            }
        }

        this.market = market;
        this.revenue = market.getRevenue(ordersInThisMarket);
        this.revenueWithAds = market.getRevenueWithAds(ordersInThisMarket);
        this.channelSummaries = market.generateChannelSummaries(ordersInThisMarket);
    }

    public int getRevenue() {
        return this.revenue;
    }

    public void printOrderSummary() {
        System.out.println(this.market.getName());
        System.out.println("Market Revenue: " + this.revenue);
        System.out.println("Market Revenue With Advertisement: " + this.revenueWithAds);

        for (int i = 0; i < this.channelSummaries.size(); i++) {
            ChannelSummary channelSummary = this.channelSummaries.get(i);
            Utils.printDivisionLine();
            System.out.print("Channel " + (i + 1) + ". ");
            channelSummary.printOrderSummary();
        }
    }

}

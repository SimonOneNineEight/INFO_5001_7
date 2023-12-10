/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.MarketModel;

import java.util.ArrayList;

import model.OrderManagement.Order;

/**
 *
 * @author kal bugrara
 */
public class ChannelSummary {
    private Channel channel;
    private MarketChannelAssignment mca;
    private int revenue;
    private int revenueWithAds;

    public ChannelSummary(MarketChannelAssignment mca, ArrayList<Order> orders) {
        ArrayList<Order> ordersInThisChannel = new ArrayList<>();

        for (Order order : orders) {
            if (order.getMarketChannelAssignment().equals(mca)) {
                ordersInThisChannel.add(order);
            }
        }

        this.channel = mca.getChannel();
        this.mca = mca;
        this.revenue = this.channel.getRevenue(ordersInThisChannel);
        this.revenueWithAds = this.channel.getRevenueWithAds(ordersInThisChannel);
    }

    public int getRevenue() {
        return this.revenue;
    }

    public void printOrderSummary() {
        System.out.println(this.channel.getName());
        System.out.println("Channel Revenue: " + this.revenue);
        System.out.println("Channel Revenue With Advertisement: " + this.revenueWithAds);
    }

}

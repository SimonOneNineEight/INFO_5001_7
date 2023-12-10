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
public class Channel {
    private String name;
    private MarketChannelAssignment market;

    public Channel(String name, Market market, double ratio) {
        this.name = name;
        this.market = new MarketChannelAssignment(market, this, ratio);
        this.market.getMarket().addChannel(this);
    }

    public String getName() {
        return this.name;
    }

    public MarketChannelAssignment getMarket() {
        return this.market;
    }

    public double getRatio() {
        return this.market.getChannelRatio();
    }

    public int getRevenue(ArrayList<Order> orders) {
        return Utils.getTotalOrderRevenue(orders);
    }

    public int getRevenueWithAds(ArrayList<Order> orders) {
        return Utils.getTotalOrderRevenueWithAds(orders);
    }

}

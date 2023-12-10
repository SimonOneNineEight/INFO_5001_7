/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.OrderManagement;

import java.util.ArrayList;

import model.CustomerManagement.CustomerProfile;
import model.MarketModel.ChannelOrderReport;
import model.MarketModel.Market;
import model.MarketModel.MarketChannelAssignment;
import model.MarketModel.MarketOrderReport;
import model.SalesManagement.SalesPersonProfile;

/**
 *
 * @author kal bugrara
 */
public class MasterOrderList {
    ArrayList<Order> orders;
    MasterOrderReport masterOrderReport;
    MarketOrderReport marketOrderReport;
    ChannelOrderReport channelOrderReport;

    public MasterOrderList() {
        orders = new ArrayList<Order>();

    }

    public Order newOrder(CustomerProfile cp, MarketChannelAssignment mca) {
        Order o = new Order(cp, mca);
        orders.add(o);
        return o;
    }

    public Order newOrder(CustomerProfile cp, MarketChannelAssignment mca, Boolean isAdvertised) {
        Order o = new Order(cp, mca, isAdvertised);
        orders.add(o);
        return o;
    }

    public Order newOrder(CustomerProfile cp, SalesPersonProfile spp) {
        Order o = new Order(cp, spp);
        orders.add(o);

        return o;
    }

    public MasterOrderReport generateMasterOrderReport() {
        masterOrderReport = new MasterOrderReport();
        masterOrderReport.generateOrderReport(orders);

        return masterOrderReport;
    }

    public MarketOrderReport generateMarketOrderReport(ArrayList<Market> markets) {
        this.marketOrderReport = new MarketOrderReport();
        this.marketOrderReport.generateOrderReport(orders, markets);

        return this.marketOrderReport;
    }

    public ChannelOrderReport generateChannelOrderReport(ArrayList<MarketChannelAssignment> mcas) {
        this.channelOrderReport = new ChannelOrderReport();
        this.channelOrderReport.generateOrderReport(orders, mcas);

        return this.channelOrderReport;
    }

    public int getSalesVolume() {

        int sum = 0;
        for (Order order : orders) {
            sum = sum + order.getOrderTotal();
        }
        return sum;
    }

    public void printShortInfo() {
        System.out.println("Checking what's inside the master order list.");
        System.out.println("There are " + orders.size() + " order.");
    }

}

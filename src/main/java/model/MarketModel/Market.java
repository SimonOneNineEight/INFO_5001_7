/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.MarketModel;

import java.util.ArrayList;

import model.OrderManagement.Order;
import model.ProductManagement.Product;
import model.ProductManagement.SolutionOffer;
import utils.Utils;

/**
 *
 * @author kal bugrara
 */
public class Market {
    private String name;
    private ArrayList<SolutionOffer> so;
    private ArrayList<MarketChannelAssignment> channels;
    private ArrayList<Product> products;
    private ArrayList<Market> submarkets;
    private int size;

    public Market(String name) {
        this.name = name;
        this.so = new ArrayList<>();
        this.channels = new ArrayList<>();
        this.products = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public void addChannel(Channel c) {
        this.channels.add(c.getMarket());
    }

    public void addSolutionOffer(SolutionOffer solutionOffer) {
        this.so.add(solutionOffer);
    }

    public ArrayList<MarketChannelAssignment> getMarketChannelList() {
        return this.channels;
    }

    public void addProduct(Product p) {
        this.products.add(p);
    }

    public ArrayList<SolutionOffer> getSolutionOffers() {
        return this.so;
    }

    public MarketChannelAssignment pickRandomChannel() {
        MarketChannelAssignment mca = Utils.pickRandom(this.channels);
        return mca;
    }

    public SolutionOffer pickRandomSolutionOffer() {
        SolutionOffer solutionOffer = Utils.pickRandom(this.so);
        return solutionOffer;
    }

    public Product pickRandomProduct() {
        Product product = Utils.pickRandom(this.products);
        return product;
    }

    public int getRevenue(ArrayList<Order> orders) {
        return Utils.getTotalOrderRevenue(orders);
    }

    public int getRevenueWithAds(ArrayList<Order> orders) {
        return Utils.getTotalOrderRevenueWithAds(orders);
    }

    public ArrayList<Product> getProducts() {
        return this.products;
    }

    public ArrayList<ChannelSummary> generateChannelSummaries(ArrayList<Order> orders) {
        ArrayList<ChannelSummary> channelSummaries = new ArrayList<>();

        for (MarketChannelAssignment mca : channels) {
            ChannelSummary channelSummary = new ChannelSummary(mca, orders);
            channelSummaries.add(channelSummary);
        }

        return channelSummaries;
    }

}

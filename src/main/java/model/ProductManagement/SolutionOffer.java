/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ProductManagement;

import java.util.ArrayList;

import model.MarketModel.MarketChannelAssignment;

/**
 *
 * @author kal bugrara
 */
public class SolutionOffer {
    private ArrayList<Product> products;
    private int price;// floor, ceiling, and target ideas
    private MarketChannelAssignment marketChannelComb;
    private String ads;

    public SolutionOffer(MarketChannelAssignment m) {
        this.marketChannelComb = m;
        this.products = new ArrayList<Product>();
        this.ads = "nothing!";
    }

    public SolutionOffer(MarketChannelAssignment m, ArrayList<Product> products) {
        this.marketChannelComb = m;
        this.products = products;
        this.ads = "nothing!";
    }

    public SolutionOffer(MarketChannelAssignment m, ArrayList<Product> products, String ads) {
        this.marketChannelComb = m;
        this.products = products;
        this.ads = ads;
    }

    public void addProduct(Product p) {
        this.products.add(p);
    }

    public void setPrice(int p) {
        this.price = p;
    }

    public int getPrice() {
        return this.price;
    }

    public String getAds() {
        return this.ads;
    }

    public ArrayList<Product> getProducts() {
        return this.products;
    }

    public MarketChannelAssignment getMarketChannel() {
        return this.marketChannelComb;
    }

    public String getProductsListString() {
        ArrayList<String> productNames = new ArrayList<>();
        for (Product p : products) {
            productNames.add(p.getName());
        }

        return String.join(", ", productNames);
    }

}

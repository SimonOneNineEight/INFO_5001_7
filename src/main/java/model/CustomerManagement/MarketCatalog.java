/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.CustomerManagement;

import java.util.ArrayList;
import java.util.Random;

import model.MarketModel.Market;
import utils.Utils;
import model.Business.Business;

/**
 *
 * @author kal bugrara
 */
public class MarketCatalog {
    private Business business;
    private ArrayList<Market> markets;

    public MarketCatalog(Business business) {
        this.business = business;
        this.markets = new ArrayList<>();
    }

    public Market newMarket(String name) {
        Market market = new Market(name);
        this.markets.add(market);
        return market;
    }

    public ArrayList<Market> getMarketList() {
        return this.markets;
    }

    public Market pickRandomMarket() {
        Market market = Utils.pickRandom(this.markets);
        return market;
    }

}

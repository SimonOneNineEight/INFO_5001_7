/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.CustomerManagement;

import java.util.ArrayList;

import model.Business.Business;
import model.MarketModel.Channel;
import model.MarketModel.Market;

/**
 *
 * @author kal bugrara
 */
public class ChannelCatalog {
    private Business business;
    private ArrayList<Channel> channels;

    public ChannelCatalog(Business business) {
        this.business = business;
        this.channels = new ArrayList<>();
    }

    public Channel newChannel(String name, Market m, double ratio) {
        Channel channel = new Channel(name, m, ratio);
        this.channels.add(channel);

        return channel;
    }
}

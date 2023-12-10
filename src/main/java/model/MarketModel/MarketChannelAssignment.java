/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.MarketModel;

/**
 *
 * @author kal bugrara
 */
public class MarketChannelAssignment {
    private Market market;
    private Channel channel;
    private double ratio;

    public MarketChannelAssignment(Market m, Channel c, double ratio) {
        this.market = m;
        this.channel = c;
        this.ratio = ratio;
    }

    public Market getMarket() {
        return this.market;
    }

    public Channel getChannel() {
        return this.channel;
    }

    public double getChannelRatio() {
        return this.ratio;
    }

}

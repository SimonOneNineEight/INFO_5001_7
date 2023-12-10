/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ProductManagement;

import java.util.ArrayList;

import model.Business.Business;
import model.MarketModel.MarketChannelAssignment;

/**
 *
 * @author kal bugrara
 */
public class SolutionOfferCatalog {
    private Business business;
    private ArrayList<SolutionOffer> solutionoffers;

    public SolutionOfferCatalog(Business b) {
        this.business = b;
        this.solutionoffers = new ArrayList<>();
    }

    public void addSolutionOffer(SolutionOffer s) {
        this.solutionoffers.add(s);
    }

    public SolutionOffer newSolutionOffer(MarketChannelAssignment m, ArrayList<Product> products) {
        SolutionOffer solutionOffer = new SolutionOffer(m, products);
        this.solutionoffers.add(solutionOffer);

        return solutionOffer;
    }

    public SolutionOffer newSolutionOffer(MarketChannelAssignment m, ArrayList<Product> products, String ads) {
        SolutionOffer solutionOffer = new SolutionOffer(m, products, ads);
        this.solutionoffers.add(solutionOffer);

        return solutionOffer;
    }
}

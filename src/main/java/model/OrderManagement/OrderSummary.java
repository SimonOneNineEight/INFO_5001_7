/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.OrderManagement;

/**
 *
 * @author kal bugrara
 */
public class OrderSummary {
    private int salesvolume;
    private int numberOfItems;
    private boolean totalabovetarget;
    private int orderpriceperformance;
    private int numberofOrderitemsabovetarget;
    private String customerId;
    private boolean usedAds;

    public OrderSummary(Order o) {
        this.salesvolume = o.getOrderTotal();
        this.numberOfItems = o.getNumberOfItems();
        this.totalabovetarget = o.isOrderAboveTotalTarget();
        this.orderpriceperformance = o.getOrderPricePerformance();
        this.numberofOrderitemsabovetarget = o.getNumberOfOrderItemsAboveTarget();
        this.customerId = o.getCustomerId();
        this.usedAds = o.getIsOrderWithAds();

    }

    public int getOrderProfit() {
        return orderpriceperformance;
    }

    public int getNumberOfItems() {
        return this.numberOfItems;
    }

    public int getSalesVolume() {
        return this.salesvolume;
    }

    public void printOrderSummary() {
        System.out.println("Customer Name: " + customerId + " | Number of Items: " + numberOfItems + " | Sales Volumn: "
                + salesvolume + "| Used Discount: " + this.usedAds);
    }

}

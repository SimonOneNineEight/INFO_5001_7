package utils;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import model.MarketModel.Market;
import model.MarketModel.MarketChannelAssignment;
import model.OrderManagement.Order;
import model.ProductManagement.Product;
import model.ProductManagement.SolutionOffer;

public class Utils {
    public static Market selectMarket(Scanner sc, ArrayList<Market> marketList) {
        System.out.println("Hello Customer! Please choose a department you want to survey");

        int marketListSize = marketList.size();
        for (int i = 0; i < marketListSize; i++) {
            System.out.println((i + 1) + ". " + marketList.get(i).getName());
        }
        System.out.println((marketListSize + 1) + ". I can't find the department I want! bye bye ~");

        String marketInput = sc.next();

        if (marketInput.equals(Integer.toString(marketListSize + 1)))
            return null;

        return marketList.get(Integer.valueOf(marketInput) - 1);
    }

    public static MarketChannelAssignment getMarketChannel(Scanner sc, Market market,
            ArrayList<MarketChannelAssignment> marketChannelList) {
        String marketName = market.getName();
        int channelListSize = marketChannelList.size();
        System.out.println("Welcome to department " + marketName + ", What is your favorite color?");
        for (int i = 0; i < channelListSize; i++) {
            System.out.println((i + 1) + ". " + marketChannelList.get(i).getChannel().getName());
        }
        System.out.println((channelListSize + 1) + ". I can't find any color I like, so ciao~~");

        String channelInput = sc.next();
        if (channelInput.equals(Integer.toString(channelListSize + 1)))
            return null;

        return marketChannelList.get(Integer.valueOf(channelInput) - 1);

    }

    public static ArrayList<SolutionOffer> filterSolutionOffers(ArrayList<SolutionOffer> solutionOffers,
            MarketChannelAssignment mca) {
        ArrayList<SolutionOffer> result = new ArrayList<>();

        for (SolutionOffer so : solutionOffers) {
            if (so.getMarketChannel().equals(mca))
                result.add(so);
        }

        return result;
    }

    public static SolutionOffer getSolutionOffer(Scanner sc, ArrayList<SolutionOffer> solutionOffers) {
        int solutionOfferSize = solutionOffers.size();
        System.out.println("Now you can select the bundle you want?");
        for (int i = 0; i < solutionOfferSize; i++) {
            SolutionOffer solutionOffer = solutionOffers.get(i);
            System.out
                    .println((i + 1) + ". " + solutionOffer.getProductsListString() + ", Ads: " + solutionOffer.getAds()
                            + ".");
        }
        System.out.println((solutionOfferSize + 1) + ". I don't like any of these.");
        String solutionInput = sc.next();
        if (solutionInput.equals(Integer.toString(solutionOfferSize + 1)))
            return null;

        return solutionOffers.get(Integer.valueOf(solutionInput) - 1);
    }

    public static Order loadSolutionOfferToOrder(Order order, SolutionOffer solutionOffer, double ratio) {
        ArrayList<Product> products = solutionOffer.getProducts();
        for (Product product : products) {
            order.newOrderItem(product, (int) Math.round(product.getTargetPrice() * ratio), 1);
        }
        return order;
    }

    public static <T> T pickRandom(ArrayList<T> list) {
        if (list.size() == 0)
            return null;
        Random r = new Random();
        int randomIndex = r.nextInt(list.size());
        return list.get(randomIndex);
    }

    public static int getTotalOrderRevenue(ArrayList<Order> orders) {
        int revenue = 0;

        for (Order order : orders) {
            revenue += order.getOrderTotal();
        }

        return revenue;
    }

    public static int getTotalOrderRevenueWithAds(ArrayList<Order> orders) {
        ArrayList<Order> ordersWithAds = new ArrayList<>();

        for (Order order : orders) {
            if (order.getIsOrderWithAds()) {
                ordersWithAds.add(order);
            }
        }

        int revenue = 0;

        for (Order order : ordersWithAds) {
            revenue += order.getOrderTotal();
        }

        return revenue;
    }

    public static void printDivisionLine() {
        System.out.println("--------------------------------------------------");
    }

    public static void printDoubleDivisionLine() {
        System.out.println("--------------------------------------------------");
        System.out.println("--------------------------------------------------");
    }
}

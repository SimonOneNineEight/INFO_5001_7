/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.util.ArrayList;
import java.util.Scanner;

import model.Business.Business;
import model.Business.ConfigureABusiness;
import model.CustomerManagement.ChannelCatalog;
import model.CustomerManagement.CustomerDirectory;
import model.CustomerManagement.CustomerProfile;
import model.CustomerManagement.MarketCatalog;
import model.MarketModel.Channel;
import model.MarketModel.ChannelOrderReport;
import model.MarketModel.Market;
import model.MarketModel.MarketChannelAssignment;
import model.MarketModel.MarketOrderReport;
import model.OrderManagement.MasterOrderList;
import model.OrderManagement.MasterOrderReport;
import model.OrderManagement.Order;
import model.Personnel.Person;
import model.Personnel.PersonDirectory;
import model.ProductManagement.Product;
import model.ProductManagement.ProductCatalog;
import model.ProductManagement.ProductsReport;
import model.ProductManagement.SolutionOffer;
import model.ProductManagement.SolutionOfferCatalog;
import model.Supplier.Supplier;
import model.Supplier.SupplierDirectory;
import utils.Utils;

/**
 *
 * @author kal bugrara
 */
public class DigitalMarketingApplication {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        // TODO:
        // Part1.
        // 1. Create a business include suppliers, markets, channels, items.
        // 2. Predefine some user types (Channels, items, etc), Create some product,
        // services and bundles for each user type
        // 3. Use the scanner to get user profiles (Market, Channel) and categorize them
        // to our predefined user type (Can also make user pick their user type base on
        // the option we provided)
        // 4. Show items for the user type and allow user to create order based on their
        // user type

        // Part2.
        // 1. Generate sales 50 orders for the report
        // 2. Generate sales report (include ad result, how did the user purchase
        // because they click an ad)

        // 1. Populate the model +

        Business business = ConfigureABusiness.createABusinessAndLoadALotOfData("HAKUNAMATATA", 3, 3, 3, 1, 10, 30, 50,
                10);

        SupplierDirectory supplierDirectory = business.getSupplierDirectory();
        CustomerDirectory customerDirectory = business.getCustomerDirectory();
        PersonDirectory personDirectory = business.getPersonDirectory();
        MarketCatalog marketCatalog = business.getMarketCatalog();
        ChannelCatalog channelCatalog = business.getChannelCatalog();
        SolutionOfferCatalog solutionOfferCatalog = business.getSolutionOfferCatalog();

        MasterOrderList mol = business.getMasterOrderList();
        MasterOrderReport orderReport = mol.generateMasterOrderReport();
        ArrayList<Market> marketList = marketCatalog.getMarketList();

        ArrayList<MarketChannelAssignment> crossMarketChannelList = new ArrayList<>();
        for (Market market : marketList) {
            crossMarketChannelList.addAll(market.getMarketChannelList());
        }

        // 2. Maybe some interaction with the user (optional)
        Scanner sc = new Scanner(System.in);

        boolean exitCode = false;

        while (!exitCode) {
            System.out.println("Welcome to Hakuna Matata, please select who are you");
            System.out.println("1. I'm a customer, I want to buy some awesome things");
            System.out.println("2. I'm a staff, I need some reports about the firm.");
            System.out.println("3. I want some freedom!");

            String row = sc.next();

            switch (row) {
                case "1":
                    Market market = Utils.selectMarket(sc, marketList);

                    if (market == null)
                        break;

                    ArrayList<MarketChannelAssignment> marketChannelList = market.getMarketChannelList();
                    ArrayList<SolutionOffer> solutionOffers = market.getSolutionOffers();

                    MarketChannelAssignment mca = Utils.getMarketChannel(sc, market, marketChannelList);

                    if (mca == null)
                        break;

                    double channelRatio = mca.getChannelRatio();
                    Channel channel = mca.getChannel();

                    ArrayList<SolutionOffer> filteredSolutionOffers = Utils.filterSolutionOffers(solutionOffers, mca);

                    SolutionOffer solutionOffer = Utils.getSolutionOffer(sc, filteredSolutionOffers);

                    if (solutionOffer == null)
                        break;

                    long totalPrice = Math.round(solutionOffer.getPrice() * channelRatio);

                    System.out.println("This is your order: " + solutionOffer.getProductsListString()
                            + ", the color is: " + channel.getName() + ". The total price is: " + totalPrice + ".");
                    System.out.println("1. Proceed Checkout ~");
                    System.out.println("2. Cancel Order.");

                    if (!sc.next().equals("1"))
                        break;

                    System.out.println("Please enter your name: ");
                    String name = sc.next();

                    Person newPerson = personDirectory.newPerson(name);
                    CustomerProfile customer = customerDirectory.newCustomerProfile(newPerson);

                    Order order = mol.newOrder(customer, mca, true);
                    order = Utils.loadSolutionOfferToOrder(order, solutionOffer, channelRatio);

                    System.out.println("Thank you for placing your order! Hope to see you soon!\n");
                    break;
                case "2":
                    System.out.println("What kind of report do you need?");
                    System.out.println("1. General Report");
                    System.out.println("2. Market Report");
                    System.out.println("3. Channel Report");
                    System.out.println("4. Exit");

                    String reportSelection = sc.next();

                    if (reportSelection.equals("4"))
                        break;

                    switch (reportSelection) {
                        case "1":
                            orderReport.printOrderReport();
                            break;
                        case "2":
                            MarketOrderReport marketOrderReport = mol.generateMarketOrderReport(marketList);
                            marketOrderReport.printOrderReport();
                            break;
                        case "3":
                            ChannelOrderReport channelOrderReport = mol
                                    .generateChannelOrderReport(crossMarketChannelList);
                            channelOrderReport.printOrderReport();
                            break;
                        default:
                            break;
                    }

                    break;
                case "3":
                    exitCode = true;
                    break;
                default:
                    break;
            }
        }

        System.out.println("Thank you, have a nice day.");

        // 3. Show some analytics (Summarizing, comparing, sorting, grouping data by
        // some criteria)

        // business.printShortInfo();

        // Faker magicBox = new Faker();

        // System.out.println("================== First, Last name ==================
        // ");
        // for (int index=0; index < 50; index++){
        // String fullName = magicBox.name().fullName();
        // System.out.println(fullName);
        // }

        // System.out.println("================== Companies ================== ");

        // for (int index=0; index < 50; index++){
        // String companyName = magicBox.company().name();
        // System.out.println(companyName);
        // }

        // System.out.println("================== Products ================== ");

        // for (int index=0; index < 50; index++){
        // String product = magicBox.commerce().productName();
        // System.out.println(product);
        // }

        // System.out.println("================== Yoda Quotes ================== ");

        // for (int index=0; index < 50; index++){
        // String quote = magicBox.yoda().quote();
        // System.out.println(quote);
        // }

        sc.close();
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Business;

import java.util.ArrayList;
import java.util.Random;

import com.github.javafaker.Faker;

import model.CustomerManagement.ChannelCatalog;
import model.CustomerManagement.CustomerDirectory;
import model.CustomerManagement.CustomerProfile;
import model.CustomerManagement.MarketCatalog;
import model.MarketModel.Channel;
import model.MarketModel.Market;
import model.MarketModel.MarketChannelAssignment;
import model.OrderManagement.MasterOrderList;
import model.OrderManagement.Order;
import model.Personnel.Person;
import model.Personnel.PersonDirectory;
import model.ProductManagement.Product;
import model.ProductManagement.ProductCatalog;
import model.ProductManagement.SolutionOffer;
import model.ProductManagement.SolutionOfferCatalog;
import model.Supplier.Supplier;
import model.Supplier.SupplierDirectory;
import utils.Utils;

/**
 *
 * @author kal bugrara
 */
public class ConfigureABusiness {

    static int upperPriceLimit = 50000;
    static int lowerPriceLimit = 100;
    static int range = 5;
    static int productMaxQuantity = 5;
    private static Faker faker = new Faker();
    private static Random random = new Random();

    public static Business createABusinessAndLoadALotOfData(String name, int marketCount, int channelCount,
            int solutionOfferCount,
            int supplierCount, int productCount,
            int customerCount, int orderCount, int itemCount) {
        Business business = new Business(name);

        // Add Suppliers +
        loadSuppliers(business, supplierCount);

        // Add Markets
        loadMarkets(business, marketCount, channelCount, solutionOfferCount);

        // Add Products +
        loadProducts(business, productCount);

        // Add Customers
        loadCustomers(business, customerCount);

        // Add Order
        loadOrders(business, orderCount, itemCount);

        return business;
    }

    public static void loadSuppliers(Business b, int supplierCount) {
        SupplierDirectory supplierDirectory = b.getSupplierDirectory();
        for (int index = 1; index <= supplierCount; index++) {
            supplierDirectory.newSupplier(faker.company().name());
        }
    }

    public static void loadMarkets(Business b, int marketCount, int channelCount, int solutionOfferCount) {
        MarketCatalog marketCatalog = b.getMarketCatalog();
        ChannelCatalog channelCatalog = b.getChannelCatalog();
        SolutionOfferCatalog solutionOfferCatalog = b.getSolutionOfferCatalog();
        SupplierDirectory supplierDirectory = b.getSupplierDirectory();
        Supplier supplier = supplierDirectory.getSupplierList().get(0);
        ProductCatalog productCatalog = supplier.getProductCatalog();

        for (int i = 0; i < marketCount; i++) {
            Market market = marketCatalog.newMarket(faker.commerce().department());

            for (int j = 0; j < channelCount; j++) {
                Channel channel = channelCatalog.newChannel(faker.commerce().color(), market,
                        1.0 * getRandom(80, 120) / 100);
                for (int k = 0; k < solutionOfferCount; k++) {
                    MarketChannelAssignment mca = channel.getMarket();

                    int productCount = getRandom(2, 3);
                    String solutionOfferAds = faker.company().catchPhrase();
                    ArrayList<Product> products = new ArrayList<>();
                    int totalPrice = 0;
                    for (int l = 0; l < productCount; l++) {
                        String productName = faker.commerce().productName();
                        int randomFloor = getRandom(lowerPriceLimit, lowerPriceLimit + range);
                        int randomCeiling = getRandom(upperPriceLimit - range, upperPriceLimit);
                        int randomTarget = getRandom(randomFloor, randomCeiling);

                        Product product = productCatalog.newProduct(productName, randomFloor, randomCeiling,
                                randomTarget, market);
                        products.add(product);
                        totalPrice += randomTarget;
                    }

                    SolutionOffer solutionOffer = solutionOfferCatalog.newSolutionOffer(mca, products,
                            solutionOfferAds);
                    market.addSolutionOffer(solutionOffer);
                    solutionOffer.setPrice(totalPrice);
                }
            }
        }
    }

    public static void loadProducts(Business b, int productCount) {
        SupplierDirectory supplierDirectory = b.getSupplierDirectory();
        MarketCatalog marketCatalog = b.getMarketCatalog();

        for (Supplier supplier : supplierDirectory.getSupplierList()) {

            int randomProductNumber = getRandom(1, productCount);
            ProductCatalog productCatalog = supplier.getProductCatalog();

            for (int index = 1; index <= randomProductNumber; index++) {
                Market randomMarket = marketCatalog.pickRandomMarket();

                String productName = faker.commerce().productName();
                int randomFloor = getRandom(lowerPriceLimit, lowerPriceLimit + range);
                int randomCeiling = getRandom(upperPriceLimit - range, upperPriceLimit);
                int randomTarget = getRandom(randomFloor, randomCeiling);

                productCatalog.newProduct(productName, randomFloor, randomCeiling, randomTarget, randomMarket);
            }
        }
    }

    static int getRandom(int lower, int upper) {
        // nextInt(n) will return a number from zero to 'n'. Therefore e.g. if I want
        // numbers from 10 to 15
        // I will have result = 10 + nextInt(5)
        int randomInt = lower + random.nextInt(upper - lower);
        return randomInt;
    }

    static void loadCustomers(Business b, int customerCount) {
        CustomerDirectory customerDirectory = b.getCustomerDirectory();
        PersonDirectory personDirectory = b.getPersonDirectory();

        for (int index = 1; index <= customerCount; index++) {
            Person newPerson = personDirectory.newPerson(faker.name().fullName());
            customerDirectory.newCustomerProfile(newPerson);
        }
    }

    static void loadOrders(Business b, int orderCount, int itemCount) {

        // reach out to masterOrderList
        MasterOrderList mol = b.getMasterOrderList();

        // pick a random customer (reach to customer directory)
        CustomerDirectory customerDirectory = b.getCustomerDirectory();
        MarketCatalog marketCatalog = b.getMarketCatalog();

        for (int index = 0; index < orderCount; index++) {
            CustomerProfile randomCustomer = customerDirectory.pickRandomCustomer();
            if (randomCustomer == null) {
                System.out.println("Cannot generate orders. No customers in the customer directory.");
                return;
            }
            Market randomMarket = marketCatalog.pickRandomMarket();
            MarketChannelAssignment randomChannel = randomMarket.pickRandomChannel();
            double channelRatio = randomChannel.getChannelRatio();

            // create an order for that customer
            Order randomOrder = mol.newOrder(randomCustomer, randomChannel);

            // add order items
            // -- pick a supplier first (randomly)
            // -- pick a product (randomly)
            // -- actual price, quantity
            Boolean isSolutionOffer = getRandom(1, 3) == 1;

            if (isSolutionOffer) {
                randomOrder.setIsAdvertised(true);
                SolutionOffer solutionOffer = randomMarket.pickRandomSolutionOffer();

                randomOrder = Utils.loadSolutionOfferToOrder(randomOrder, solutionOffer, channelRatio);
            } else {
                int randomItemCount = getRandom(1, itemCount);
                for (int itemIndex = 0; itemIndex < randomItemCount; itemIndex++) {
                    Product randomProduct = randomMarket.pickRandomProduct();
                    if (randomProduct == null) {
                        System.out.println("Cannot generate orders. No products in the product catalog.");
                        continue;
                    }

                    int price = (int) Math.round(randomProduct.getTargetPrice() * channelRatio);
                    int randomQuantity = getRandom(1, productMaxQuantity);

                    randomOrder.newOrderItem(randomProduct, price, randomQuantity);
                }
            }
        }
        // Make sure order items are connected to the order

    }

}

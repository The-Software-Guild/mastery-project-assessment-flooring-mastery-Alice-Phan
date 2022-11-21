package com.sal.fm.dao;

import com.sal.fm.model.Order;
import com.sal.fm.model.Product;
import com.sal.fm.model.Tax;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FlooringMasteryDaoImpl implements FlooringMasteryDao {
    Map<Integer, Order> orderMap;
    Map<String, Tax> taxMap;
    Map<String, Product> productMap;
    FileDao fio;

    public FlooringMasteryDaoImpl() throws FlooringMasteryException {
        fio = new FileDaoImpl();
    }

    @Override
    public Order getOrder(LocalDate orderDate, int orderNumber) throws FlooringMasteryException {
        try {
            orderMap = fio.readOrders(orderDate);
            Order order = orderMap.get(orderNumber);
            order.getArea(); // test that it's valid
            return order;
        }
        catch (NullPointerException e) {
            throw new FlooringMasteryException("Could not find order number " + orderNumber + "on " + orderDate.toString());

        }
    }

    @Override
    public List<Order> listAllOrders(LocalDate orderDate) throws FlooringMasteryException {

        orderMap = fio.readOrders(orderDate);
        return new ArrayList<>(orderMap.values());

    }

    public Order buildOrder(LocalDate orderDate, String customerName, Tax tax, Product product, BigDecimal area) {
        BigDecimal costPerSqft, laborCostPerSqft, materialCost, laborCost, totalPreTax, totalTax;

        costPerSqft = product.getCostPerSqf();
        laborCostPerSqft = product.getLaborCostPerSqf();
        materialCost = costPerSqft.multiply(area).setScale(2, RoundingMode.HALF_UP);
        laborCost = laborCostPerSqft.multiply(area).setScale(2, RoundingMode.HALF_UP);
        totalPreTax = materialCost.add(laborCost).divide(new BigDecimal(100).setScale(2, RoundingMode.HALF_UP));
        totalTax = totalPreTax.multiply(tax.getTaxRate().setScale(2, RoundingMode.HALF_UP));

        return new Order(fio.getNextOrderNumber(orderDate),
                customerName,
                tax.getStateAbbreviation(),
                tax.getTaxRate(),
                product.getProductType(),
                area,
                costPerSqft,
                laborCostPerSqft,
                materialCost,
                laborCost,
                totalTax,
                totalPreTax.add(totalTax).setScale(2, RoundingMode.HALF_UP));
    }
    @Override
    public Order addNewOrder(LocalDate orderDate, String customerName, String state, String productType, String area) throws FlooringMasteryException {

        orderMap = fio.readOrders(orderDate);
        Order order = buildOrder(orderDate, customerName, getTax(state), getProduct(productType),
                new BigDecimal(area).setScale(2, RoundingMode.HALF_UP));

        Order addedOrder = orderMap.put(order.getOrderNumber(), order);
        fio.writeOrders(orderDate, new ArrayList<Order>(orderMap.values()));

        return addedOrder;
    }

    @Override
    public Order editOrder(LocalDate orderDate, int orderNumber, String customerName, String state, String productType, String area) throws FlooringMasteryException {

        Order order = getOrder(orderDate, orderNumber);

        Order newOrder = buildOrder(orderDate,
                customerName.isBlank() ? order.getCustomerName() : customerName,
                state.isBlank() ? getTax(order.getState()) : getTax(state.toUpperCase()),
                productType.isBlank() ? getProduct(order.getProductType()) : getProduct(productType),
                area.isBlank() ? order.getArea() : new BigDecimal(area).setScale(2, RoundingMode.HALF_UP));

        newOrder.setOrderNumber(orderNumber);

        newOrder = orderMap.put(newOrder.getOrderNumber(), newOrder);
        fio.writeOrders(orderDate, new ArrayList<Order>(orderMap.values()));

        return newOrder;

    }

    @Override
    public Order removeOrder(LocalDate orderDate, int orderNumber) throws FlooringMasteryException {
        Order order = getOrder(orderDate, orderNumber);

        orderMap = fio.readOrders(orderDate);
        Order newOrderMap = orderMap.remove(order.getOrderNumber());
        fio.writeOrders(orderDate, new ArrayList<Order>(orderMap.values()));
        return newOrderMap;

    }

    public Tax getTax(String stateAbbre) throws FlooringMasteryException {
        try {
            taxMap = fio.readTaxes();
            Tax tax = taxMap.get(stateAbbre);
            tax.getTaxRate(); // test that it's valid
            return tax;
        }
        catch (NullPointerException e) {
            throw new FlooringMasteryException("Could not find a state with the abbreviation " + stateAbbre + " on file.");

        }
    }

    public List<Tax> listAllTaxes() throws FlooringMasteryException {
        taxMap = fio.readTaxes();

        return new ArrayList<>(taxMap.values());
    }

    public Product getProduct(String productType) throws FlooringMasteryException {
        try {
            productMap = fio.readProducts();
            Product product = productMap.get(productType);
            product.getCostPerSqf(); // test that it's valid

            return product;
        }
        catch (NullPointerException e) {
            throw new FlooringMasteryException(" Could not find the product " + productType + " on file.");

        }
    }

    public List<Product> listAllProducts() throws FlooringMasteryException {
        productMap = fio.readProducts();
        return new ArrayList<>(productMap.values());
    }
}

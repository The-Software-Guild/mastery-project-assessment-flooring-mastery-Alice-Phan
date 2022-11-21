package com.sal.fm.dao;

import com.sal.fm.model.Order;
import com.sal.fm.model.Product;
import com.sal.fm.model.Tax;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface FileDao {
    public String marshallOrder(Order order);
    public Order unmarshallOrder(String line);
    public void writeOrders(LocalDate orderDate, List<Order> list) throws FlooringMasteryException;
    public Map<Integer, Order> readOrders(LocalDate orderDate) throws  FlooringMasteryException;

    public int getNextOrderNumber(LocalDate orderDate);
    public File getFile(String path) throws FlooringMasteryException;
    public Tax unmarshallTax(String line);
    public Map<String, Tax> readTaxes() throws FlooringMasteryException;
    public Product unmarshallProduct(String line);
    public Map<String, Product> readProducts() throws FlooringMasteryException;

}

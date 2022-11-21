package com.sal.fm.dao;

import com.sal.fm.model.Order;

import java.time.LocalDate;
import java.util.List;

public interface FlooringMasteryDao {
    Order getOrder(LocalDate orderDate, int orderNumber) throws FlooringMasteryException;

    List<Order> listAllOrders(LocalDate orderDate) throws FlooringMasteryException;

    Order addNewOrder(LocalDate orderDate, String customerName, String state, String productType, String area) throws FlooringMasteryException;

    Order editOrder(LocalDate orderDate, int orderNumber, String customerName, String state, String productType, String area) throws FlooringMasteryException;

    Order removeOrder(LocalDate orderDate, int orderNumber) throws FlooringMasteryException;
}

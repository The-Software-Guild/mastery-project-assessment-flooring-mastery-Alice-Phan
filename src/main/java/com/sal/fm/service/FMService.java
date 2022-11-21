package com.sal.fm.service;

import com.sal.fm.dao.FlooringMasteryException;
import com.sal.fm.model.Order;

import java.time.LocalDate;
import java.util.List;

public interface FMService {

    public Order getOrder(LocalDate orderDate, int orderNumber) throws FlooringMasteryException;

//    public Object listAllOrders(Object dateSelection);

    public List<Order> listAllOrders(LocalDate orderDate) throws FlooringMasteryException;
    public Order addNewOrder(LocalDate orderDate, String customerName, String state, String productType, String area) throws FlooringMasteryException;



    public Order editOrder(LocalDate orderDate, int orderNumber, String customerName, String state, String productType, String area) throws FlooringMasteryException;

    public Order removeOrder(LocalDate orderDate, int orderNumber) throws FlooringMasteryException;
}

package com.sal.fm.service;

import com.sal.fm.dao.FlooringMasteryDao;
import com.sal.fm.dao.FlooringMasteryDaoImpl;
import com.sal.fm.dao.FlooringMasteryException;
import com.sal.fm.model.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class FMServiceImpl implements FMService {
    private FlooringMasteryDao dao;

    // Instantiate a new object
    public FMServiceImpl() throws FlooringMasteryException {
        this.dao = (FlooringMasteryDao) new FlooringMasteryDaoImpl();
    }
    public FMServiceImpl(FlooringMasteryDao dao) {
        this.dao = dao;
    }

    @Override
    public Order getOrder(LocalDate orderDate, int orderNumber) throws FlooringMasteryException {
        return dao.getOrder(orderDate, orderNumber);
    }

    @Override
    public List<Order> listAllOrders(LocalDate orderDate) throws FlooringMasteryException {
        return dao.listAllOrders(orderDate)
                .stream()
                .filter(o -> o.getOrderNumber() > 0)
                .collect(Collectors.toList());
    }

    @Override
    public Order addNewOrder(LocalDate orderDate, String customerName, String state, String productType, String area) throws FlooringMasteryException {
        return dao.addNewOrder(orderDate, customerName, state, productType, area);
    }

    @Override
    public Order editOrder(LocalDate orderDate, int orderNumber, String customerName, String state, String productType, String area) throws FlooringMasteryException {
        return dao.editOrder(orderDate, orderNumber, customerName, state, productType, area);

    }

    @Override
    public Order removeOrder(LocalDate orderDate, int orderNumber) throws FlooringMasteryException {
        return dao.removeOrder(orderDate, orderNumber);
    }
}

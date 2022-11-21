package com.sal.fm.controller;

import com.sal.fm.dao.FlooringMasteryException;
import com.sal.fm.model.Order;
import com.sal.fm.service.FMService;
import com.sal.fm.ui.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;


public class Controller {
    private View view;
    private FMService serviceLayer;

//    @Autowired
//    public Controller() throws FlooringMasteryException {};
    public Controller(View view, FMService serviceLayer) {
        this.view = view;
        this.serviceLayer = serviceLayer;
    }

    // The run() method will control the whole application

    public void run() {
        boolean keepRunning = true;
        // display greeting banner before print menu?
        try {
            while (keepRunning) {
                view.printMenu();
                int operation = view.getMenuSelection();
                switch (operation) {
                    case 1: // display orders
                        displayOrders();
                        break;
                    case 2: // add an order
                        addNewOrder();
                        break;
                    case 3: // edit order
                        editOrder();
                        break;
                    case 4: // remove order
                        removeOrder();
                        break;
                    case 5: // export orders
                        view.displayNotImplemented();
                        break;
                    case 6: // quit
                        view.displayExitMessage();
                        keepRunning = false;
                        break;
                    default:
                        view.displayUnknownCommand();
                }
            }
        }
        catch (FlooringMasteryException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    public void displayOrders() throws FlooringMasteryException {
        view.printAllOrders(serviceLayer.listAllOrders(view.getDateSelection()));
    }

    public void addNewOrder() throws FlooringMasteryException {
        serviceLayer.addNewOrder(view.getDateSelection(), view.getCustomerName(), view.getState(), view.getProductType(), view.getArea());
    }

    /*
    * Edit will ask for the user for a date and order number.
    * If order exists, then update the original order with new info such as new state, new area, new product type
    * new customer name
     */
    public void editOrder() throws FlooringMasteryException {
        LocalDate orderDate = view.getDateSelection();
        int orderNumber = view.getOrderSelection();

        Order originalOrder = serviceLayer.getOrder(orderDate, orderNumber);

        serviceLayer.editOrder(orderDate, orderNumber,
                view.getCustomerName(originalOrder.getCustomerName()),
                view.getState(originalOrder.getState()),
                view.getProductType(originalOrder.getProductType()),
                view.getArea(originalOrder.getArea().toString()));
        view.pause("order number " + originalOrder.getOrderNumber() + " was modified successfully.");
    }

    /*
     * Remove an order based on the date and the order number.
     * If it exists, the system display a message
    */
    public void removeOrder() throws FlooringMasteryException {
        try {
            view.pause("Order from customer "
                        + serviceLayer.removeOrder(view.getDateSelection(), view.getOrderSelection()).getOrderNumber()
                        + " removed successfully.");
        }
        catch (FlooringMasteryException e) {
            view.displayErrorMessage("The order could not be removed. Please check information entered and try again.");
        }
    }
}

package com.sal.fm.ui;

import com.sal.fm.model.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class View {
    private UserIO io;

    public View(UserIO io) {
        this.io = io;
    }

    // Print menu
    public void printMenu() {
        io.print("=====================(*_*)=======================\n" +
                "* <<Flooring Program>>\n" +
                "* 1. Display Orders\n" +
                "* 2. Add an Order\n" +
                "* 3. Edit an Order\n" +
                "* 4. Remove an Order\n" +
                "* 5. Export All Data\n" +
                "* 6. Quit\n" +
                "====================================================");

    }

    // Display banner
//    public void greetingBanner() {
//        io.print("================(*_*)===============");
//        io.print("<<Flooring Program>>");
//        io.print("****** Main Menu ******");
//    }
//
//    // Display the main menu
//    public int displayMainMenu() {
//        greetingBanner();
//        io.print("1. Display Orders");
//        io.print("2. Add an Order");
//        io.print("3. Edit an Order");
//        io.print("4. Remove an Order");
//        io.print("5. Export All Data");
//        io.print("6. Quit");
//        return io.readInt("Please select your option from number 1 to number 6", 1, 6);
//    }


    // Method to get a date to display an order
//    public LocalDate getDateToDisplayOrder() {
////        displayOrderBanner();
//        return io.readDate("Please enter a date in MMDDYYYY format to display order");
//    }
//    // Display order banner
//    public void displayOrderBanner() {
//        io.print("=== Display Orders ===");
//    }

    public int getMenuSelection() {
        return io.readInt("Please select a menu option.", 1, 6);
    }

    public LocalDate getDateSelection() {
        return io.readDate("Please enter a date to access orders.");

    }
    public int getOrderSelection() {
        return io.readInt("Please enter an order number.");

    }

    public String getCustomerName() {
        return io.readString("Enter customer name: ");
    }
    public String getCustomerName(String currentName) {
        return io.readString("Enter customer name (" + currentName + "): ");
    }

    public String getState() {
        return io.readString("Enter state: ");
    }
    public String getState(String currentState) {
        return io.readString("Enter state (" + currentState + ") :");

    }

    public String getProductType() {
        return io.readString("Enter product type:");

    }

    public String getProductType(String currentProductType) {
        return io.readString("Enter product type (" + currentProductType + "): ");

    }
    public String getArea() {
        return io.readString("Enter area:");
    }

//    public BigDecimal getArea(BigDecimal currentArea) {
//        return io.readBigDecimal("Enter area.");
//    }
    public String getArea(String currentArea) {
        return io.readString("Enter area (" + currentArea + "):");
    }

    public ArrayList<String> printAllOrders(List<Order> orderList) {
        ArrayList<String> list = new ArrayList<>();

        list.add(0, "null");
        for (Order o : orderList) {
            printOrder(o);
        }
        return list;
    }

    public void printOrder(Order order) {
        io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n" +
                "* Order Number " + order.getOrderNumber() + ":\n" +
                "* " + order.getCustomerName() + "\n" +
                "*   " + order.getArea() + "sqft of " + order.getProductType() + "\n" +
                "*   Materials: " + order.getMaterialCost() + " (" + order.getCostPerSqf() + "/sqft)" + "\n" +
                "*       Labor: " + order.getLaborCost() + " (" + order.getLaborCostPerSqf() + "/sqft)" + "\n" +
                "*         Tax: " + order.getTax() + " (" + order.getState() + " @ " + order.getTaxRate() + "%)" + "\n"
                +
                "*       Total: " + order.getTotal() + "\n" +
                "* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");

    }
    public void pause(String message) {
        io.readString(message + "\nPress enter to continue.");

    }


    public void displayErrorMessage(String message) {
        io.print(message + "\n");
        io.readString("Please press enter to continue.");

    }

    public void displayUnknownCommand() {
        io.print("Invalid input. Please try again.");
    }

    public void displayExitMessage() {
        io.print("Program ending. Goodbye!");
    }
    public void displayNotImplemented() {
        pause("This feature is not implemented.");
    }


}

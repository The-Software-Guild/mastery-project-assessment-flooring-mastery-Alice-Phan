package com.sal.fm.model;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

@Component
public class Order {
    private int orderNumber;
    private String customerName;
    private String state;
    private BigDecimal taxRate;
    private String productType;
    private BigDecimal area;
    private BigDecimal costPerSqf;
    private BigDecimal laborCostPerSqf;
    private BigDecimal materialCost;
    private BigDecimal laborCost;
    private BigDecimal tax;
    private BigDecimal total;
    private LocalDate orderDate;

    // Default constructor
    public Order(int nextOrderNumber, String customerName, String stateAbbreviation, BigDecimal taxRate, String productType, BigDecimal area, BigDecimal costPerSqft, BigDecimal laborCostPerSqft, BigDecimal materialCost, BigDecimal laborCost, BigDecimal totalTax, BigDecimal bigDecimal) {
        this.orderNumber = nextOrderNumber;
        this.customerName = customerName;
        this.state = stateAbbreviation;
        this.taxRate = taxRate;
        this.productType = productType;
        this.area = area;
        this.costPerSqf = costPerSqft;
        this.laborCostPerSqf = laborCostPerSqft;
        this.materialCost = materialCost;
        this.laborCost = laborCost;
        this.tax = totalTax;
        this.total = bigDecimal;
    };

    // Constructor that takes in 12 parameters for add an order function

    public Order(int orderNumber, String customerName, String state, BigDecimal taxRate, String productType,
                 BigDecimal area, BigDecimal costPerSqf, BigDecimal laborCostPerSqf, BigDecimal materialCost,
                 BigDecimal laborCost, BigDecimal tax, BigDecimal total, LocalDate orderDate) {

        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.state = state;
        this.taxRate = taxRate;
        this.productType = productType;
        this.area = area;
        this.costPerSqf = costPerSqf;
        this.laborCostPerSqf = laborCostPerSqf;
        this.materialCost = materialCost;
        this.laborCost = laborCost;
        this.tax = tax;
        this.total = total;
        this.orderDate = orderDate;
    }

    // Initialized the variables using the following constructor
    public Order(String orderNumString, String customerNameString, String state, String taxRateString, String productType,
                 String areaString, String costPerSqfString, String laborCostPerSqfString, String materialCostString,
                 String laborCostString, String taxString, String totalString) {
        this.orderNumber = Integer.parseInt(orderNumString);
        this.customerName = customerNameString;
        this.state = state;
        this.taxRate = new BigDecimal(taxRateString).setScale(2, RoundingMode.HALF_UP);
        this.productType = productType;
        this.area = new BigDecimal(areaString).setScale(2, RoundingMode.HALF_UP);
        this.costPerSqf = new BigDecimal(costPerSqfString).setScale(2, RoundingMode.HALF_UP);
        this.laborCostPerSqf = new BigDecimal(laborCostPerSqfString).setScale(2, RoundingMode.HALF_UP);
        this.materialCost = new BigDecimal(materialCostString).setScale(2, RoundingMode.HALF_UP);
        this.laborCost = new BigDecimal(laborCostString).setScale(2, RoundingMode.HALF_UP);
        this.tax = new BigDecimal(taxString).setScale(2, RoundingMode.HALF_UP);
        this.total = new BigDecimal(totalString).setScale(2, RoundingMode.HALF_UP);
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getCostPerSqf() {
        return costPerSqf;
    }

    public void setCostPerSqf(BigDecimal costPerSqf) {
        this.costPerSqf = costPerSqf;
    }

    public BigDecimal getLaborCostPerSqf() {
        return laborCostPerSqf;
    }

    public void setLaborCostPerSqf(BigDecimal laborCostPerSqf) {
        this.laborCostPerSqf = laborCostPerSqf;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderNumber, customerName, state, taxRate, productType, area, costPerSqf, laborCostPerSqf, materialCost, laborCost, tax, total);
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Order other = (Order) obj;
        return orderNumber == other.orderNumber && Objects.equals(customerName, other.customerName) && Objects.equals(state, other.state) && Objects.equals(taxRate, other.taxRate)
                && Objects.equals(productType, other.productType) && Objects.equals(area, other.area) && Objects.equals(costPerSqf, other.costPerSqf) && Objects.equals(laborCostPerSqf, other.laborCostPerSqf)
                && Objects.equals(materialCost, other.materialCost) && Objects.equals(laborCost, other.laborCost) && Objects.equals(tax, other.tax) && Objects.equals(total, other.total);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNumber=" + orderNumber +
                ", customerName='" + customerName + '\'' +
                ", state='" + state + '\'' +
                ", taxRate=" + taxRate +
                ", productType='" + productType + '\'' +
                ", area=" + area +
                ", costPerSqf=" + costPerSqf +
                ", laborCostPerSqf=" + laborCostPerSqf +
                ", materialCost=" + materialCost +
                ", laborCost=" + laborCost +
                ", tax=" + tax +
                ", total=" + total +
                '}';
    }
}


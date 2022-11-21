package com.sal.fm.model;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Component
public class Product {
    // Created fields for Product class
    private String productType;
    private BigDecimal costPerSqf;
    private BigDecimal laborCostPerSqf;

    // default constructor
    public Product() {};

    // Constructor that takes in one parameter and creates a new Product object
    public Product(String productType) {

        this.productType = productType;
    }

    // Constructor takes in 3 parameters and creates a new Product object
    public Product(String productType, BigDecimal costPerSqf, BigDecimal laborCostPerSqf) {
        this.productType = productType;
        this.costPerSqf = costPerSqf;
        this.laborCostPerSqf = laborCostPerSqf;
    }

    // Initialize the variables using the constructor which takes in 3 string parameters
    public Product(String productType, String costPerSqf, String laborCostPerSqf) {
        this.productType = productType;
        this.costPerSqf = new BigDecimal(costPerSqf).setScale(2, RoundingMode.HALF_UP);
        this.laborCostPerSqf = new BigDecimal(laborCostPerSqf).setScale(2, RoundingMode.HALF_UP);
    }
    public String getProductType() {

        return productType;
    }

    public void setProductType(String productType) {

        this.productType = productType;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Product product = (Product) obj;
        return Objects.equals(productType, product.productType)
                && Objects.equals(costPerSqf, product.costPerSqf)
                && Objects.equals(laborCostPerSqf, product.laborCostPerSqf);
    }

    @Override
    public int hashCode() {

        return Objects.hash(productType, costPerSqf, laborCostPerSqf);
    }

    @Override
    public String toString() {
        return "Product{" +
                "productType='" + productType + '\'' +
                ", costPerSqf=" + costPerSqf +
                ", laborCostPerSqf=" + laborCostPerSqf +
                '}';
    }
}


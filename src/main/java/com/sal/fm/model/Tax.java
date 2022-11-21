package com.sal.fm.model;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Component
public class Tax {
    private String stateAbbreviation;
    private String stateName;
    private BigDecimal taxRate;

    public Tax() {};

    // Initialize the variables using the constructor which takes in 3 string parameters

    public Tax(String stateAbbreviation, String stateName, String taxRate) {
        this.stateAbbreviation = stateAbbreviation;
        this.stateName = stateName;
        this.taxRate = new BigDecimal(taxRate).setScale(2, RoundingMode.HALF_UP);
    }

    public String getStateAbbreviation() {
        return stateAbbreviation;
    }

    public void setStateAbbreviation(String stateAbbreviation) {
        this.stateAbbreviation = stateAbbreviation;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tax tax = (Tax) o;
        return Objects.equals(stateAbbreviation, tax.stateAbbreviation) && Objects.equals(stateName, tax.stateName) && Objects.equals(taxRate, tax.taxRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stateAbbreviation, stateName, taxRate);
    }

    @Override
    public String toString() {
        return "Tax{" +
                "stateAbbreviation='" + stateAbbreviation + '\'' +
                ", stateName='" + stateName + '\'' +
                ", taxRate=" + taxRate +
                '}';
    }
}

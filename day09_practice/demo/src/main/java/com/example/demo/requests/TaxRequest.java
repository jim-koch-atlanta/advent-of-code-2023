package com.example.demo.requests;

public class TaxRequest {
    private Integer taxableIncome;
    private float taxableRate;

    public Integer getTaxableIncome() {
        return taxableIncome;
    }

    public void setTaxableIncome(Integer taxableIncome) {
        this.taxableIncome = taxableIncome;
    }

    public float getTaxableRate() {
        return taxableRate;
    }

    public void setTaxableRate(float taxableRate) {
        this.taxableRate = taxableRate;
    }
}
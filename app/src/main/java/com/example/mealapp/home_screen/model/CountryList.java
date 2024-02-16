package com.example.mealapp.home_screen.model;


import java.util.ArrayList;

public class CountryList {
    private ArrayList<Country> countries;
    private int total, skip, limit;

    public ArrayList<Country> getProducts() {
        return countries;
    }

    public void setProducts(ArrayList<Country> products) {
        this.countries = products;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSkip() {
        return skip;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}

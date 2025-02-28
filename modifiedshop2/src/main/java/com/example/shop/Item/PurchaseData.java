package com.example.shop.Item;

public class PurchaseData {
    private int updatedStock;
    private double totalSum;

    public PurchaseData(int updatedStock, double totalSum){
        this.updatedStock = updatedStock;
        this.totalSum = totalSum;
    }

    public int getUpdatedStock() {
        return updatedStock;
    }

    public void setUpdatedStock(int updatedStock) {
        this.updatedStock = updatedStock;
    }

    public double getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(double totalSum) {
        this.totalSum = totalSum;
    }
}

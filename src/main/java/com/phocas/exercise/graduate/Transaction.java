package com.phocas.exercise.graduate;
import java.util.Objects;

/**
 * A record class representing a transaction, but containing only key elements
 * of the transaction for statistic computations.
 */
final class Transaction {
    
    // Name of seller in transaction.
    private final String salesPerson;

    // Value of transaction.
    private final int value;

    // Brand of item in transaction.
    private final String brand;

    // Category of item in transaction.
    private final String category;

    // Road of location of transaction.
    private final String road;

    public Transaction(String salesPerson, int value, String brand, String category, String road) {
        this.salesPerson = salesPerson;
        this.value = value;
        this.brand = brand;
        this.category = category;
        this.road = road;
    }

    public String getSalesPerson() {
        return salesPerson;
    }

    public int getValue() {
        return value;
    }

    public String getBrand() {
        return brand;
    }

    public String getCategory() {
        return category;
    }

    public String getRoadSold() {
        return road;
    }

    @Override
    public int hashCode() {
        int hashCode = Objects.hash(getValue(), getSalesPerson(), getBrand(), getCategory(), getRoadSold());
        return hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Transaction)) {
            return false;
        }

        Transaction that = (Transaction) obj;

        if ((getValue() != that.getValue()) 
            || (!getSalesPerson().equals(that.getSalesPerson()))
            || (!getBrand().equals(that.getBrand()))
            || (!getCategory().equals(that.getCategory()))) {
            return false;
        }
        return getRoadSold().equals(that.getRoadSold());
    }

    @Override
    public String toString() {
        return "Transaction {"
                + "salesPerson='" + salesPerson + '\''
                + ", value=" + value
                + ", brand='" + brand + '\''
                + ", category='" + category + '\''
                + ", road='" + road + '\''
                + '}';
    }

}

package com.phocas.exercise.graduate;

import java.util.HashMap;
import java.util.LinkedList;


public class SaleStats {

    public static String getNthMostValuedSalesperson(HashMap<String, Integer> salesPersonTotals, int n) {
        LinkedList<HashMap.Entry<String, Integer>> pairsList = new LinkedList<>(salesPersonTotals.entrySet());
        pairsList.sort(HashMap.Entry.comparingByValue());
        return pairsList.get(pairsList.size() - n).getKey();
    }
    
    public static HashMap<String, Integer> getBrandTotalsMap(LinkedList<Transaction> transactions) {
        final HashMap<String, Integer> brandTotals = new HashMap<>();
        for (Transaction transaction : transactions) {

            final String brand = transaction.getBrand();
            final int value = transaction.getValue();

            if (brandTotals.containsKey(brand)) {
                brandTotals.put(brand, brandTotals.get(brand) + value);
            } else {
                brandTotals.put(brand, value);
            }
        }
        return brandTotals;
    }

    public static HashMap<String, Integer> getCategoryTotalsMap(LinkedList<Transaction> transactions) {
        final HashMap<String, Integer> categoryValues = new HashMap<>();
        for (Transaction transaction : transactions) {

            final String category = transaction.getCategory();
            final int value = transaction.getValue();

            if (categoryValues.containsKey(category)) {
                categoryValues.put(category, categoryValues.get(category) + value);
            } else {
                categoryValues.put(category, value);
            }
        }
        return categoryValues;
    }

    public static int transactionsInBrandAndCategory(LinkedList<Transaction> transactions, String brand, String category) {
        int count = 0;
        for (Transaction transaction : transactions) {
            if (transaction.getBrand().equals(brand) && transaction.getCategory().equals(category)) {
                count++;
            }
        }
        return count;
    }

    public static HashMap<String, Integer> roadTransactions(LinkedList<Transaction> transactions) {
        final HashMap<String, Integer> roadTotals = new HashMap<>();
        for (Transaction transaction : transactions) {

            final String road = transaction.getRoadSold();
            final int value = transaction.getValue();

            if (roadTotals.containsKey(road)) {
                roadTotals.put(road, roadTotals.get(road) + value);
            } else {
                roadTotals.put(road, value);
            }
        }
        return roadTotals;
    }
}

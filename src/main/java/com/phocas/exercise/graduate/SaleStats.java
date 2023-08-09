package com.phocas.exercise.graduate;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * A utility class containing methods for computing statistics on transactions.
 * The methods can be used to answer the following:
 * 1. Who is the fourth best salesperson?
 * 2. (For the salesperson) What is the best selling brand?
 * 3. (For the salesperson) What is the best selling category?
 * 4. (For the salesperson) How many transactions were there in both the best selling brand and category?
 * 5. (For the salesperson) What road has the most transactions?
 */
public final class SaleStats {

    /**
     * Finds the key with the highest value in a hashmap.
     * @param map The hashmap to find the key with the highest value in.
     * @return The key with the highest value in a hashmap.
     */
	private static String hashMapMax(HashMap<String, Integer> map) {
		int max = 0;
		String maxKey = "";
		for (String key : map.keySet()) {
			if (map.get(key) > max) {
				max = map.get(key);
				maxKey = key;
			}
		}
		return maxKey;
	}

    /**
     * Finds the fourth most valued salesperson.
     * @param salesPersonTotals A hashmap of salesperson to total value of transactions.
     * @return The fourth most valued salesperson.
     */
    public static String findFourthMostValuedSalesperson(HashMap<String, Integer> salesPersonTotals) {
        // Sort the hashmap by value before indexing fourth highest valued salesperson.
        final LinkedList<HashMap.Entry<String, Integer>> pairsList = new LinkedList<>(salesPersonTotals.entrySet());
        pairsList.sort(HashMap.Entry.comparingByValue());
        return pairsList.get(pairsList.size() - 4).getKey();
    }
    
    /**
     * Finds the brand with the highest value of transactions.
     * @param transactions The transactions to check.
     * @return The brand with the highest value of transactions.
     */
    public static String findMaxBrand(LinkedList<Transaction> transactions) {
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
        return hashMapMax(brandTotals);
    }

    /**
     * Finds the category with the highest value of transactions.
     * @param transactions The transactions to check.
     * @return The category with the highest value of transactions.
     */
    public static String findMaxCategory(LinkedList<Transaction> transactions) {
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
        return hashMapMax(categoryValues);
    }

    /**
     * Counts the number of transactions that have both the given brand and category.
     * @param transactions The transactions to check.
     * @param brand The brand to check for.
     * @param category The category to check for.
     * @return The number of transactions that have both the given brand and category.
     */
    public static int transactionsInBrandAndCategory(LinkedList<Transaction> transactions, String brand, String category) {
        int count = 0;
        for (Transaction transaction : transactions) {
            if (transaction.getBrand().equals(brand) && transaction.getCategory().equals(category)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Finds the road with the most transactions.
     * @param transactions The transactions to check.
     * @return The road with the most transactions.
     */
    public static String maxRoadTransactions(LinkedList<Transaction> transactions) {
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
        return hashMapMax(roadTotals);
    }
}

package com.phocas.exercise.graduate;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedList;

public class Solution {

	public static String hashMapMax(HashMap<String, Integer> map) {
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

	public static void main(String[] args) {

		try {
			final JSONReader reader = new JSONReader();
			reader.readFile("dataset.ndjson");
			final HashMap<String, LinkedList<Transaction>> salesPersonTransactions = reader.getSalesPersonToTransactions();
			final HashMap<String, Integer> salesPersonTotals = reader.getSalesPersonToTotalValues();

			final String fourthBest = SaleStats.getNthMostValuedSalesperson(salesPersonTotals, 4); // Fourth Best Salesperson.
			final LinkedList<Transaction> fourthBestTransactions = salesPersonTransactions.get(fourthBest);

			final HashMap<String, Integer> brandTotals = SaleStats.getBrandTotalsMap(fourthBestTransactions);
			final HashMap<String, Integer> categoryTotals = SaleStats.getCategoryTotalsMap(fourthBestTransactions);
			final String brandMax = hashMapMax(brandTotals); // Best selling brand.
			final String categoryMax = hashMapMax(categoryTotals); // Best selling category.

			final int transactionsInBest = SaleStats.transactionsInBrandAndCategory(fourthBestTransactions, 
																					brandMax,
																					categoryMax);
			final HashMap<String, Integer> roadTotals = SaleStats.roadTransactions(fourthBestTransactions);
			final String roadMax = hashMapMax(roadTotals); // Road of most transactions.

			System.out.println("Fourth best salesperson: " + fourthBest + " with value: " + salesPersonTotals.get(fourthBest));
			System.out.println(fourthBest + "\'s Best selling brand: " + brandMax);
			System.out.println(fourthBest + "\'s Best selling category: " + categoryMax);
			System.out.println("Number of transactions in both top-selling brand and category: " + transactionsInBest);
			System.out.println("Road of most transactions: " + roadMax);
		} catch (ParseException e) {
			System.out.println("Date in incorrect format.");
		}

	}

}

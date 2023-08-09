package com.phocas.exercise.graduate;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedList;

import org.json.JSONException;

public class Solution {

	public static void main(String[] args) {

		try {
			
			final DatasetReader reader = new DatasetReader();
			reader.readFile("dataset.ndjson");

			final HashMap<String, LinkedList<Transaction>> salesPersonTransactions = reader.getSalesPersonTransactions();
			final HashMap<String, Integer> salesPersonTotals = reader.getSalesPersonTotalValues();

			final String fourthBest = SaleStats.findFourthMostValuedSalesperson(salesPersonTotals); // Fourth Best Salesperson.
			final LinkedList<Transaction> fourthBestTransactions = salesPersonTransactions.get(fourthBest);

			final String brandMax = SaleStats.findMaxBrand(fourthBestTransactions);; // Best selling brand.
			final String categoryMax = SaleStats.findMaxCategory(fourthBestTransactions); // Best selling category.
			final int transactionsInBest = SaleStats.transactionsInBrandAndCategory(fourthBestTransactions, brandMax, categoryMax); // Number of transactions in both top-selling brand and category.
			final String roadMax = SaleStats.maxRoadTransactions(fourthBestTransactions); // Road of most transactions.

			System.out.println("Fourth best salesperson: " + fourthBest + " with value: " + salesPersonTotals.get(fourthBest));
			System.out.println(fourthBest + "\'s Best selling brand: " + brandMax);
			System.out.println(fourthBest + "\'s Best selling category: " + categoryMax);
			System.out.println("Number of transactions in both top-selling brand and category: " + transactionsInBest);
			System.out.println("Road of most transactions: " + roadMax);

		} catch (ParseException e) {
			System.out.println("Date in incorrect format.");

		} catch (IOException e) {
			System.out.println("Error reading file.");

		} catch (JSONException e) {
			System.out.println("Invalid JSON.");

		} catch (IndexOutOfBoundsException e) {
			System.out.println("No fourth best salesperson.");

		}

	}

}

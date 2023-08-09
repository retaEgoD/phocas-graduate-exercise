package com.phocas.exercise.graduate;

import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TimeZone;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DatasetReader {
    
    /*
     * Sales person to list of transactions.
     */
    private HashMap<String, LinkedList<Transaction>> salesPersonTransactions;

    /*
     * Sales person to total sales value.
     */
    private HashMap<String, Integer> salesPersonTotals;

    public DatasetReader() {
        this.salesPersonTransactions = new HashMap<>();
        this.salesPersonTotals = new HashMap<>();
    }

    public void readFile(String path) throws ParseException, IOException, JSONException {
        
        try {

            final HashMap<String, LinkedList<Transaction>> salesPersonTransactions = this.salesPersonTransactions;
            final HashMap<String, Integer> salesPersonTotals = this.salesPersonTotals;

            final BufferedReader reader = new BufferedReader(new FileReader(path));
            final ObjectMapper mapper = new ObjectMapper();
            final JsonParser parser = mapper.getFactory().createParser(reader);

            // Dates for checking if transaction is in January 2022.
            final SimpleDateFormat UTCFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            UTCFormat.setTimeZone(TimeZone.getTimeZone("Pacific/Auckland"));
            final Date firstDayOfJanuary2022 = UTCFormat.parse("2021-12-31T12:00:00.000Z");
            final Date firstDayOfFebruary2022 = UTCFormat.parse("2022-01-31T12:00:00.000Z");

            try {
                while (parser.nextToken() != JsonToken.END_ARRAY) {
                    final JsonNode transactionJSON = mapper.readTree(parser);
                    System.out.println(transactionJSON);
                    final String dateString = transactionJSON.get("date").textValue();
                    
                    if (isDateValid(dateString, UTCFormat)) {
                        final Date transactionDate = UTCFormat.parse(dateString);

                        if (transactionDate.after(firstDayOfJanuary2022) 
                            && transactionDate.before(firstDayOfFebruary2022)
                            && transactionJSON.get("city").equals("Christchurch")) {

                            final Transaction transaction = JSONToTransaction(transactionJSON);
                            final String salesPerson = transaction.getSalesPerson();
                            final int value = transaction.getValue();

                            if (salesPersonTransactions.containsKey(salesPerson)) {
                                // Salesperson has already been encountered.
                                salesPersonTransactions.get(salesPerson).add(transaction);
                                salesPersonTotals.put(salesPerson, salesPersonTotals.get(salesPerson) + value);
                            } else {
                                // New salesperson.
                                final LinkedList<Transaction> transactions = new LinkedList<>();
                                transactions.add(transaction);
                                salesPersonTransactions.put(salesPerson, transactions);
                                salesPersonTotals.put(salesPerson, value);
                            }
                        }
                    }
                    
                    
                }
            parser.close();

            } catch (JSONException e) {
                System.out.println("Not in correct format.");
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } 
    }

    private boolean isDateValid(String date, SimpleDateFormat format) {
        try {
            format.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private static Transaction JSONToTransaction(JsonNode transactionJSON) {
        final Transaction transaction = new Transaction(transactionJSON.get("salesPerson").textValue(), 
                                                        transactionJSON.get("value").intValue(), 
                                                        transactionJSON.get("brand").textValue(), 
                                                        transactionJSON.get("category").textValue(), 
                                                        transactionJSON.get("road").textValue());
        return transaction;
    }

    public HashMap<String, LinkedList<Transaction>> getSalesPersonToTransactions() {
        return salesPersonTransactions;
    }

    public HashMap<String, Integer> getSalesPersonToTotalValues() {
        return salesPersonTotals;
    }

}

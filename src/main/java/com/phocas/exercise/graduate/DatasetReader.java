package com.phocas.exercise.graduate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.json.JSONException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * A class for reading a dataset and storing the transactions in a hashmap of salesperson 
 * to list of the salesperson's transactions, as well as salesperson to total value of transactions.
 */
public class DatasetReader {
    

    // Hashmap of sales person to (linked) list of transactions.
    private HashMap<String, LinkedList<Transaction>> salesPersonTransactions;


    // Hashmap of sales person to total value of transactions.
    private HashMap<String, Integer> salesPersonTotals;


    public DatasetReader() {
        this.salesPersonTransactions = new HashMap<>();
        this.salesPersonTotals = new HashMap<>();
    }


    /**
     * Reads a file and stores the transactions in a hashmap of salesperson to list of transactions.
     * @param path The path of the file to read.
     * @throws FileNotFoundException
     * @throws ParseException
     * @throws IOException
     * @throws JSONException
     */
    public void readFile(String path) throws FileNotFoundException, ParseException, IOException, JSONException {
        
        HashMap<String, LinkedList<Transaction>> salesPersonTransactions = new HashMap<>();
        HashMap<String, Integer> salesPersonTotals = new HashMap<>();

        // Setting up parser and time formatter.
        final BufferedReader reader = new BufferedReader(new FileReader(path));
        final ObjectMapper mapper = new ObjectMapper();
        final JsonParser parser = mapper.getFactory().createParser(reader);
        final SimpleDateFormat UTCFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        // Continue while there are still transactions to read.
        while (parser.nextToken() != JsonToken.END_ARRAY && parser.getCurrentToken() != null) {
            final JsonNode transactionJSON = mapper.readTree(parser);
            final String dateString = transactionJSON.get("date").textValue();
            
            if (isDateValid(dateString, UTCFormat)) {
                final Date transactionDate = UTCFormat.parse(dateString);

                // Check if date is in January 2022 and if the transaction is in Christchurch. 12 hour offset for NZ time.
                if (transactionDate.after(UTCFormat.parse("2021-12-31T12:00:00.000Z")) 
                    && transactionDate.before(UTCFormat.parse("2022-01-31T12:00:00.000Z"))
                    && transactionJSON.get("city").textValue().equals("Christchurch")) {

                    final Transaction transaction = JSONToTransaction(transactionJSON);
                    final String salesPerson = transaction.getSalesPerson();
                    final int value = transaction.getValue();

                    if (salesPersonTransactions.containsKey(salesPerson)) {

                        salesPersonTransactions.get(salesPerson).add(transaction);
                        salesPersonTotals.put(salesPerson, salesPersonTotals.get(salesPerson) + value);
                    } else {
                        
                        final LinkedList<Transaction> transactions = new LinkedList<>();
                        transactions.add(transaction);
                        salesPersonTransactions.put(salesPerson, transactions);
                        salesPersonTotals.put(salesPerson, value);
                    }
                }
            }
        }

        parser.close();
        reader.close();
        setSalesPersonTransactions(salesPersonTransactions);
        setSalesPersonTotals(salesPersonTotals);
        
    }


    /**
     * Checks if a date string is a valid date given a format by attempting to parse it
     * @param date The date to check.
     * @param format The format to check the date against.
     * @return True if the date is valid, false otherwise.
     */
    private boolean isDateValid(String date, SimpleDateFormat format) {
        try {
            format.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }


    /**
     * Converts a JSON object to a Transaction object.
     * @param transactionJSON The JSON object to convert.
     * @return The converted Transaction object.
     */
    private static Transaction JSONToTransaction(JsonNode transactionJSON) {
        final Transaction transaction = new Transaction(transactionJSON.get("salesPerson").textValue(), 
                                                        transactionJSON.get("value").intValue(), 
                                                        transactionJSON.get("brand").textValue(), 
                                                        transactionJSON.get("category").textValue(), 
                                                        transactionJSON.get("road").textValue());
        return transaction;
    }


    public HashMap<String, LinkedList<Transaction>> getSalesPersonTransactions() {
        return salesPersonTransactions;
    }


    public HashMap<String, Integer> getSalesPersonTotalValues() {
        return salesPersonTotals;
    }


    private void setSalesPersonTransactions(HashMap<String, LinkedList<Transaction>> salesPersonTransactions) {
        this.salesPersonTransactions = salesPersonTransactions;
    }


    private void setSalesPersonTotals(HashMap<String, Integer> salesPersonTotals) {
        this.salesPersonTotals = salesPersonTotals;
    }
}

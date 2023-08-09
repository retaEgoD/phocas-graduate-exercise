package com.phocas.exercise.graduate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SalesStatsTest {

    private LinkedList<Transaction> transactions;

    @BeforeEach
    void setUp() {

        transactions = new LinkedList<>();
        transactions.add(new Transaction("Bartholemew", 20, "Brand2", "Category", "Road"));
        transactions.add(new Transaction("Bartholemew", 30, "Brand1", "Category", "Road2"));
        transactions.add(new Transaction("Bartholemew", 40, "Brand1", "Category1", "Road"));
        transactions.add(new Transaction("Bartholemew", 50, "Brand3", "Category1", "Road2"));
        transactions.add(new Transaction("Bartholemew", 60, "Brand2", "Category1", "Road2"));
        transactions.add(new Transaction("BartholemewIV", 700, "Brand1", "Category", "Road"));
        transactions.add(new Transaction("BartholemewIII", 800, "Brand4", "Category", "Road"));
        transactions.add(new Transaction("BartholemewII", 900, "Brand3", "Category", "Road"));
        
    }

    @AfterEach
    void teardown() {
        transactions = null;
    }

    @Test
    void findFourthMostValuedSalesperson() {
        HashMap<String, Integer> salesPersonTotals = new HashMap<>();
        salesPersonTotals.put("Richard Lobbster", 100);
        salesPersonTotals.put("BartholemewII", 200);
        salesPersonTotals.put("BartholemewIII", 300);
        salesPersonTotals.put("BartholemewIV", 400);
        salesPersonTotals.put("BartholemewV", 500);
        salesPersonTotals.put("BartholemewVI", 600);
        salesPersonTotals.put("BartholemewVII", 700);

        assertEquals("BartholemewIV", SaleStats.findFourthMostValuedSalesperson(salesPersonTotals));

        // Check for ties.
        salesPersonTotals.put("CartholemewIV", 400);
        assertEquals("CartholemewIV", SaleStats.findFourthMostValuedSalesperson(salesPersonTotals));
        assertNotEquals("BartholemewIV", SaleStats.findFourthMostValuedSalesperson(salesPersonTotals));

    }

    @Test
    void findMaxBrand() {
        assertEquals("Brand3", SaleStats.findMaxBrand(transactions));
    }

    @Test
    void findMaxCategory() {
        assertEquals("Category", SaleStats.findMaxCategory(transactions));
    }

    @Test
    void transactionsInBrandAndCategory() {
        assertEquals(2, SaleStats.transactionsInBrandAndCategory(transactions, "Brand1", "Category"));
    }

    @Test
    void maxRoadTransactions() {
        assertEquals("Road", SaleStats.maxRoadTransactions(transactions));
    }
    
}

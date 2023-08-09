package com.phocas.exercise.graduate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import org.json.JSONException;

public class DatasetReaderTest {
    
    private DatasetReader reader;

    @BeforeEach
    void setUp() {
        reader = new DatasetReader();
    }

    @AfterEach
    void teardown() {
        reader = null;
    }

    @Test
    void readFile() throws FileNotFoundException, JSONException, ParseException, IOException {
        reader.readFile("src/test/resources/smallDataset.ndjson");
        assertEquals(34, reader.getSalesPersonTransactions().size());
        Transaction transaction = reader.getSalesPersonTransactions().get("Emma").get(0);
        assertEquals("Emma", transaction.getSalesPerson());
        assertEquals("Davis Place", transaction.getRoadSold());
        assertEquals("Bashee", transaction.getCategory());
        assertEquals("the", transaction.getBrand());
        assertEquals(3469, transaction.getValue());
    }
}

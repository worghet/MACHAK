package com.example.machak;

import android.annotation.SuppressLint;

import java.text.DecimalFormat;

public class Transaction {

    private static final DecimalFormat decimalFormat = new DecimalFormat("#.##");


    private String location;
    private double amount;
    private Timestamp timestamp;



    // -- CONSTRUCTOR



    public Transaction(String location_input, double amount_input, Timestamp timestamp_input) {
        location = location_input;
        amount = amount_input;
        timestamp = timestamp_input;
    }



    // -- GETTER METHODS



    public String getLocation() {
        return location;
    }

    public double getAmount() {
        return amount;
    }

    @SuppressLint("DefaultLocale")
    public String getSummary() {

            /*
            "[2025-11-24 14:30] $12.50 @ New York"
            "[NYC] [$12.50] [2025-11-24 14:30]"
            "NYC / $12.50 / 14:30"
            "NYC: $12.50 (2025-11-24 14:30)"
             */


//        return "hiya";
        return String.format("$%s @ %s [%s]", decimalFormat.format(amount), location, timestamp.getFormattedDate());
    }

    // -- FUNCTION METHODS



    public static void appendTransactionToLog(String location_input, double amount_input, Timestamp timestamp_input) {

        // read file, get list of transactions

        Transaction new_transaction = new Transaction(location_input, amount_input, timestamp_input);

        // transaction list.append(transaction)

        // update file, close reader/writer
    }



}

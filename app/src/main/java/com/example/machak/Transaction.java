package com.example.machak;

public class Transaction {



    private String location;
    private double amount;
    private long unix_timestamp;



    // -- CONSTRUCTOR



    public Transaction(String location_input, double amount_input, long timestamp_input) {
        location = location_input;
        amount = amount_input;
        unix_timestamp = timestamp_input;
    }



    // -- GETTER METHODS



    public String getLocation() {
        return location;
    }

    public double getAmount() {
        return amount;
    }

    public long getUnix_timestamp() {
        return unix_timestamp;
    }



    // -- FUNCTION METHODS



    public static void appendTransactionToLog(String location_input, double amount_input, long timestamp_input) {

        // read file, get list of transactions

        Transaction new_transaction = new Transaction(location_input, amount_input, timestamp_input);

        // transaction list.append(transaction)

        // update file, close reader/writer
    }



}

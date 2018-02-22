package com.example;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Li Xueqing on 20/2/2018.
 */

public class TransactionData {

    public static class Transaction{
        private int transactionID;
        private String bankAccount;
        private Date date;

        Transaction(int transactionID, String bankAccount, Date date){
            this.transactionID = transactionID;
            this.bankAccount = bankAccount;
            this.date = date;
        }

        public int getTransactionID(){
            return transactionID;
        }
        
        public String getBankAccount(){
            return bankAccount;
        }

        public Date getDate(){
            return date;
        }
        
    }

    public static int newTransaction(String bankAccount, Date date) {
        int transactionID = getTransactionID();
        Transaction newTransaction = new Transaction(transactionID, bankAccount, date);
        return addNewTransaction(newTransaction);
    }

    static int getTransactionID(){
        String query = "SELECT MAX(TransID) AS TransID FROM Transactions";
        List<HashMap<String, Object>> results = getData.ExecuteQuery(query);
        return (int) results.get(0).get("TransID") + 1;
    }
    
    static int addNewTransaction(Transaction transaction){
        String query = "INSERT INTO Transactions (TransactionID, BankAccount, Date)" +
                "VALUE (" +
                transaction.getTransactionID() + "," +
                transaction.getBankAccount() + "," +
                transaction.getDate() + ")";
        return updateData.ExecuteNonQuery(query);
    }

    public static Transaction getTransaction(int transactionID){
        String query = "SELECT * FROM Transactions WHERE TransactionID=" + transactionID;
        List<HashMap<String, Object>> results = getData.ExecuteQuery(query);
        HashMap<String, Object> contents = results.get(0);
        return parseTransactionData(contents);
    }

    public static Transaction parseTransactionData(HashMap<String, Object> contents){
        int transactionID = ((int) contents.get("TransactionID"));
        String bankAccount = ((String) contents.get("BankAccount"));
        Date date = ((Date) contents.get("Date"));
        return new Transaction(transactionID, bankAccount, date);
    }

    static int updateTransaction(Transaction transaction){
        String query = "UPDATE Transactions SET" +
                "BankAccount=" + transaction.getBankAccount() +
                ",Date=" + transaction.getDate() +
                "WHERE TransactionID=" + transaction.getTransactionID();
        return updateData.ExecuteNonQuery(query);
    }



}

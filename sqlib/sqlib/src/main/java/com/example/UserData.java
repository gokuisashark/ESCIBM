package com.example;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Li Xueqing on 20/2/2018.
 */

public class UserData {

    public static class User {
        private int userID;
        private String userName;
        private String userPassword;
        private String bankAccount;
        private String userAddress;
        private int postalCode;
        private int phoneNumber;
        private String emailAddress;

        User(int userID, String userName, String userPassword, String bankAccount,
             String userAddress, int postalCode, int phoneNumber, String emailAddress){
            this.userID = userID;
            this.userName = userName;
            this.userPassword = userPassword;
            this.bankAccount = bankAccount;
            this.userAddress = userAddress;
            this.postalCode = postalCode;
            this.phoneNumber = phoneNumber;
            this.emailAddress = emailAddress;
        }

        public int getUserID() {
            return userID;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserPassword() {
            return userPassword;
        }

        public void setUserPassword(String userPassword) {
            this.userPassword = userPassword;
        }

        public String getBankAccount() {
            return bankAccount;
        }

        public void setBankAccount(String bankID) {
            this.bankAccount = bankID;
        }

        public String getUserAddress() {
            return userAddress;
        }

        public void setUserAddress(String userAddress) {
            this.userAddress = userAddress;
        }

        public int getPostalCode() {
            return postalCode;
        }

        public void setPostalCode(int postalCode) {
            this.postalCode = postalCode;
        }

        public int getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(int phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getEmailAddress() {
            return emailAddress;
        }

        public void setEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
        }

    }

    public static int newUser(String userName, String userPassword, String bankAccount,
                       String userAddress, int PostalCode, int PhoneNumber, String emailAddress) {
        int userID = getUserID();
        User newuser = new User(userID, userName, userPassword, bankAccount, userAddress, PostalCode, PhoneNumber, emailAddress);
        return addNewUser(newuser);
    }

    static int addNewUser(User user) {
        String query = "INSERT INTO Users (UserID, UserName, UserPassword, bankAccount, UserAddress, PostalCode, PhoneNumber, emailAddress)\n" +
                "VALUES (" +
                user.getUserID() + "," +
                "\'" + user.getUserName() + "\'," +
                "\'" + user.getUserPassword() + "\'," +
                user.getBankAccount() + "," +
                "\'" + user.getUserAddress() + "\'," +
                user.getPostalCode() + "," +
                user.getPhoneNumber() + "," +
                user.getEmailAddress() + ")";
        return updateData.ExecuteNonQuery(query);
    }

    public static int getUserID(){
        String query = "SELECT MAX(UserID) AS UserID FROM Users";
        List<HashMap<String, Object>> results = getData.ExecuteQuery(query);
        return (int) results.get(0).get("UserID") + 1;
    }

    public static User getUser(User user){
        String query = "SELECT * FROM Users, Items WHERE Users.UserID = " + user.getUserID();
        List<HashMap<String, Object>> results = getData.ExecuteQuery(query);
        HashMap<String, Object> contents = results.get(0);
        return parseUserData(contents);
    }

    private static User parseUserData(HashMap<String, Object> contents){
        int userID = ((int) contents.get("UserID"));
        String userName = ((String) contents.get("UserName"));
        String userPassword = ((String) contents.get("UserPassword"));
        String bankAccount = ((String) contents.get("bankAccount"));
        String userAddress = ((String) contents.get("UserAddress"));
        int postalCode = ((int) contents.get("PostalCode"));
        int phoneNumber = ((int) contents.get("Phone"));
        String emailAddress = ((String) contents.get("emailAddress"));
        return new User(userID, userName, userPassword, bankAccount, userAddress, postalCode, phoneNumber, emailAddress);
    }

    public static int updateUser(User user){
        String query = "UPDATE Users SET " +
                "UserName=" + "\'" + user.getUserName() + "\'" +
                ",UserPassword=" + "\'" + user.getUserPassword() + "\'" +
                ",bankAccount=" + user.getBankAccount() +
                ",UserAddress=" + "\'" + user.getUserAddress() + "\'" +
                ",PostalCode=" + "\'" + user.getPostalCode() + "\'" +
                ",PhoneNumber=" + user.getPhoneNumber() +
                ",emailAddress=" + user.getEmailAddress() +
                " WHERE UserID=" + user.getUserID();
        return updateData.ExecuteNonQuery(query);
    }

    public static void main(String[] args) {
//        int userID, String userName, String userPassword, String bankAccount,
//                String userAddress, int postalCode, int phoneNumber, String emailAddress
        User test = new User(getUserID(), "test", "password", "9292", "testAddress", 485999, 000004, "testEmail");
        addNewUser(test);
        test.setUserAddress("test1Address");
        updateUser(test);
    }

    }

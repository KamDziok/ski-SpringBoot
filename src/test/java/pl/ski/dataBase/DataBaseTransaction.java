package pl.ski.dataBase;

import pl.ski.offer_ski.OfferSki;
import pl.ski.transaction.Transaction;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataBaseTransaction {

    private static Date createDate(int year, int month, int day){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try{
            date = format.parse((year + "-" + month + "-" + day));
        }catch (Exception e){ }
        return date;
    }

    public static Date nowDate(){
        return DataBaseTransaction.createDate(2020, 12, 15);
    }

    public static Transaction getTransaction1(){
        Transaction transaction = new Transaction(DataBaseTransaction.nowDate(), DataBaseTransaction.createDate(2021, 01, 01),
                DataBaseTransaction.createDate(2021, 01, 03), DataBaseUser.getUser1(), DataBaseTransaction.getOfferSkisTransaction1());
        transaction.setId((long) 1);
        return transaction;
    }

    public static Transaction getTransaction2(){
        Transaction transaction = new Transaction(DataBaseTransaction.nowDate(), DataBaseTransaction.createDate(2021, 01, 03),
                DataBaseTransaction.createDate(2021, 01, 06), DataBaseUser.getUser1(), DataBaseTransaction.getOfferSkisTransaction2());
        transaction.setId((long) 2);
        return transaction;
    }

    public static Transaction getTransaction3(){
        Transaction transaction = new Transaction(DataBaseTransaction.nowDate(), DataBaseTransaction.createDate(2021, 01, 01),
                DataBaseTransaction.createDate(2021, 01, 07), DataBaseUser.getUser2(), DataBaseTransaction.getOfferSkisTransaction3());
        transaction.setId((long) 3);
        return transaction;
    }

    private static List<OfferSki> getOfferSkisTransaction1(){
        List<OfferSki> result = new ArrayList<OfferSki>();

        result.add(DataBaseOfferSki.getOfferSki2());
        result.add(DataBaseOfferSki.getOfferSki2());
        result.add(DataBaseOfferSki.getOfferSki4());

        return result;
    }

    private static List<OfferSki> getOfferSkisTransaction2(){
        List<OfferSki> result = new ArrayList<OfferSki>();

        result.add(DataBaseOfferSki.getOfferSki2());

        return result;
    }

    private static List<OfferSki> getOfferSkisTransaction3(){
        List<OfferSki> result = new ArrayList<OfferSki>();

        result.add(DataBaseOfferSki.getOfferSki3());

        return result;
    }

    public static List<Transaction> getAllTransactionBetweenDate(){
        List<Transaction> transactionList = new ArrayList<>();

        transactionList.add(DataBaseTransaction.getTransaction1());
        transactionList.add(DataBaseTransaction.getTransaction2());
        transactionList.add(DataBaseTransaction.getTransaction3());

        return transactionList;
    }
}

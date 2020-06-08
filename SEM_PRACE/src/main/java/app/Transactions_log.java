/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.EOFException;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author TheMy
 */
public class Transactions_log {

    private File log;
    private int balance;
    private ArrayList<Transaction> trList = new ArrayList<Transaction>();

    public Transactions_log(File user) throws FileNotFoundException {
        log = user;
        
    }

    private void loadTransactoins() throws FileNotFoundException {
        Scanner read = new Scanner(log);
        //System.out.println("toto se neprovede "+ read.nextLine());
        loadMore(read);
        int temp = trList.size();
        int balance = 0;
        for (int i = 0; i < temp; i++) {
            balance = balance + trList.get(i).getMoneyShift();
        }
        this.balance = balance;

    }

    private void loadMore(Scanner a) throws FileNotFoundException {
        String temp = a.nextLine();
        //System.out.println(temp);
        trList.add(new Transaction(temp));
        if (a.hasNext()) {
            loadMore(a);
        }
    }
    
    /**
     *
     * @return - integer - actual account balance
     * @throws FileNotFoundException
     */
    public int getBalance() throws FileNotFoundException{
        loadTransactoins();
        return this.balance;
    }
    
    /**
     *
     * @param sortType - integer - typ třízení
     * @return - ArrayList<transaction> - setřízený list
     * @throws FileNotFoundException
     */
    public ArrayList<Transaction> getlog(int sortType) throws FileNotFoundException{
        
        switch (sortType){
            case 1:
                loadTransactoins();
                return trList;
            case 2:
                loadTransactoins();
                ValueAgeComparator valueCampare = new ValueAgeComparator();
                Collections.sort(trList,valueCampare);
                return trList;
             
    }
        return null;
    }
    public ArrayList<Transaction> getlog(int start, int end) throws FileNotFoundException{
        
        
                loadTransactoins();
                ValueAgeComparator valueCampare = new ValueAgeComparator();
                Collections.sort(trList,valueCampare);
                return trList;
             
  
        
    }
    
 //Comparator   
 public class ValueAgeComparator implements Comparator<Transaction> {
    @Override
    public int compare(Transaction c1, Transaction c2)
     {
         return (c2.getMoneyShift()) - c1.getMoneyShift();
     }
 }
    
    
 

    

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

/**
 *
 * @author TheMy
 */
public class Transaction {
    private int day;
    private int month;
    private int year;
    private int moneyShift;

   
    
    
    public Transaction(String log){
        String[] log_split=log.split(" ");
        String[] date =log_split[0].split("-");
        year=Integer.valueOf(date[0]);
        month=Integer.valueOf(date[1]);
        day=Integer.valueOf(date[2]);
        if (log_split[1].equals("+")){
            moneyShift=Integer.valueOf(log_split[2]);
        }
        if (log_split[1].equals("-")){
            moneyShift=Integer.valueOf(log_split[2])*-1;
        }
        
        
        
        
        
        
    }

    
    public int getMoneyShift(){
        return moneyShift;
    }
    
   public int getDay(){
            return day;
        }
   public int getMonth(){
            return month;
        }
   public int getYear(){
            return year;
        }
   
   
    
    
}

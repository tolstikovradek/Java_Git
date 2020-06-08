/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package radek_hurricane;

import java.util.ArrayList;

/**
 *
 * @author Themy
 */
public class Hurricane implements Comparable<Hurricane>{
    private static final String[] MONTHS = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    private String name;
    private int year;
    private int month;
    private double pressure,speedInKnots;
    
    
    public Hurricane(){
        
    }
    
    public static Hurricane initialize(int year, String month, double pressure, double speedInKnots, String name){
        Hurricane newHurr = new Hurricane();
        newHurr.setName(name);
        newHurr.setYear(year);
        newHurr.setMonth(month);
        newHurr.setPressure(pressure);
        newHurr.setSpeedInKnots(speedInKnots);
        
        return newHurr;
    }
    
    private int getIndex(String a){
        for (int i = 0; i < MONTHS.length; i++) {
            if(MONTHS[i].equals(a)){
                return i;
            }
            
        }
        return -1;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(String month) {
        this.month = getIndex(month);
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public void setSpeedInKnots(double speedInKnots) {
        this.speedInKnots = speedInKnots;
    }

    public static String[] getMONTHS() {
        return MONTHS;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public String getMonth() {
        return MONTHS[month];
    }

    public double getPressure() {
        return pressure;
    }

    public double getSpeedInKnots() {
        return speedInKnots;
    }
    
    public double getSpeedInKm(){
        return this.speedInKnots*1.852;
    }
    
    public String getSeverity(){
        double speedInKm = this.getSpeedInKm();
        if(speedInKm >= 119 && speedInKm <= 153){
            return "category 1";
        }
        if(speedInKm >= 154 && speedInKm <= 177){
            return "category 2";
        }
        if(speedInKm >= 178 && speedInKm <= 208){
            return "category 3";
        }
        if(speedInKm >= 209 && speedInKm <= 251){
            return "category 4";
        }
        if(speedInKm >= 252){
            return "category 5";
        }
        return "harmless";
    }
    

    @Override
    public String toString() {
        return year + "\t" + month + "\t" + pressure + "\t" + speedInKnots + "\t" + name;
    }

    @Override
    public int compareTo(Hurricane o) {
        if(this.getSpeedInKm() < o.getSpeedInKm()){
            return -1;
        }else{
            if(this.getSpeedInKm() > o.getSpeedInKm()){
                return 1;
            }
        }
        return 0;
    }
    
    
    
}

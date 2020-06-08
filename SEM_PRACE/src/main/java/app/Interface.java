/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.util.ArrayList;

/**
 *
 * @author TheMy
 */
public interface Interface {
   
    /**
     *
     * @return
     */
    public int login(String login, String pw);
    public int newAccount(String login, String pw1, String pw2, String name, String surname);
    public String[] accInfo();
    public boolean vyber(int amount);
    public boolean vklad(int amount);
    public ArrayList<Transaction> vypis(int typeOfSort);
    public ArrayList<Transaction> vypis(int typeOfSort,int start, int end);
    public int zustatek();
    public void uctenka(boolean succesfull, int amount,String name, String surname);
}

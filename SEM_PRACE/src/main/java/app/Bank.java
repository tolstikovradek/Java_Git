/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import utils.WrongPw;

/**
 *
 * @author TheMy
 */
public class Bank implements Interface {

    private Account uzivatel;

    public Bank() {
        uzivatel = new Account();
    }

    @Override
    public int login(String login, String pw) {

        int ans = -1;
        try {
            ans = uzivatel.login(login, pw);
        } catch (IOException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        } catch (WrongPw ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
            return 3;
        }
        switch (ans) {
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;

            case 4:
                return 4;
            default:
                return -1;
        }

    }

    /**
     *
     * @param login - String - user login name
     * @param pw1- String - user password
     * @param pw2- String - user password
     * @param name- String - user real name
     * @param surname- String - user real surnamename
     * @return - int -
     */
    @Override
    public int newAccount(String login, String pw1, String pw2, String name, String surname) {

        int ans = -1;
        try {
            ans = uzivatel.createFiles(login, pw1, pw2, name, surname);
            return ans;
        } catch (IOException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ans;
    }

    /**
     *
     * @param amount - integer - monžství vybraných peněz
     * @return - boolean - validita výběru
     */
    @Override
    public boolean vyber(int amount) {
        if (amount <= 0) {
            return false;
        }
        try {
            return uzivatel.removeMoney(amount);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     *
     * @param amount - integer - částka vkladu
     * @return -boolean- validita vstupu
     */
    @Override
    public boolean vklad(int amount) {
        if (amount > 0) {
            uzivatel.addMoney(amount);
            return true;
        }
        return false;
    }

    /**
     *
     * @return - String[] - 1. jméno, 2. přijmení
     */
    @Override
    public String[] accInfo() {
        return uzivatel.getinfo();
    }

    /**
     *
     * @param typeOfSort - integer způsob třízení seznamu
     * @return - ArrayList<transaction> - zetřízený seznam
     */
    @Override
    public ArrayList<Transaction> vypis(int typeOfSort) {
        try {
            return uzivatel.getTransactionLog(typeOfSort);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @return - Integer - zůstatek na účtu
     */
    @Override
    public int zustatek() {
        try {
            return uzivatel.getMoney();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    /**
     *
     * @param succesfull - boolean - true-> výběr, false ->vklad
     * @param amount - integer - částka
     * @param name - String - jméno
     * @param surname - String - přijmení
     */
    @Override
    public void uctenka(boolean succesfull, int amount, String name, String surname) {

        LocalTime now = LocalTime.now();
        LocalDate localDate = LocalDate.now();

        try ( PDDocument doc = new PDDocument()) {

            PDPage myPage = new PDPage();
            doc.addPage(myPage);
            try ( PDPageContentStream cont = new PDPageContentStream(doc, myPage)) {

                cont.beginText();
                cont.setFont(PDType1Font.TIMES_ROMAN, 20);
                cont.setLeading(14.5f);
                cont.newLineAtOffset(25, 700);
                cont.showText("Uctenka");
                cont.setFont(PDType1Font.TIMES_ROMAN, 12);
                cont.newLine();

                cont.showText("Typ transakce: ");
                if (succesfull == true) {
                    cont.showText("vyber v hodnote ");
                } else {
                    cont.showText("vklad v hodnote ");
                }
                cont.showText(Integer.toString(amount));
                cont.showText("Kc");
                cont.newLine();
                cont.showText("Transakce probehla " + localDate.toString() + " v " + now.getHour() + ":" + now.getMinute());
                cont.newLine();
                cont.showText("Transakci provedl: " + name + " " + surname);
                cont.newLine();
                cont.newLine();
                cont.showText("Preji hezky zbytek dne");
                cont.endText();

            }catch(IOException e){
                System.out.println("nastala chyba");
            }

            doc.save("data/uctenka.pdf");

        } catch (IOException ex) {
            System.out.println("nastala chyba");
        }

    }

    @Override
    public ArrayList<Transaction> vypis(int typeOfSort, int start, int end) {
        try {
            return uzivatel.getTransactionLog(typeOfSort);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}

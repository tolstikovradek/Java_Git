/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import app.Interface;
import app.Account;
import app.Bank;
import app.Transaction;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import org.apache.fontbox.*;
import org.apache.pdfbox.pdmodel.PDDocument;

//own exceptions
//rekurze
/**
 *
 * @author TheMy
 */
public class main {

    public static Scanner sc = new Scanner(System.in);
    public static Bank a;

    public static void main(String[] args) throws IOException {
        a = new Bank();
        String name="", surname="";
        boolean login = false;
        int ans = -1;
        boolean end = true;

        while (end) {
            System.out.println("");
            if (login) {
                System.out.println("Přihlášený jako: ");
                System.out.print(a.accInfo()[0] + " " + a.accInfo()[1]);
                System.out.println("");
                System.out.println("");
                menuLogged();
                ans = vstupInt();

                switch (ans) {
                    case 1:

                        int temp = 0;
                        System.out.println("Prosím zadejte částku, kterou chcete vybrat");
                        temp = vstupInt();
                        if (a.vyber(temp)) {
                            
                           saveToPdf(true, temp,a.accInfo()[0],a.accInfo()[1]) ;

                            System.out.println("Výběr "+temp+"Kč byl úspěšný");
                            
                        } else {
                            System.out.println("Na provedení výběru nemáte dostatečný zůstatek");
                        }
                        break;
                    case 2:
                        System.out.println("Prosím zadejte částku, kterou chcete vložit");
                        temp = vstupInt();
                        a.vklad(temp);
                        saveToPdf(false, temp,a.accInfo()[0],a.accInfo()[1]) ;
                        System.out.println("Částka "+temp+"Kč byla vložena na účet");
                        break;
                    case 3:
                        System.out.println("Prosím zadejte způsob výpisu");
                        System.out.println("Pro výpis všech transakcí a zůstatku zadejte 1");
                        System.out.println("Pro výpis podle vložené částky zadejte 2");
                        System.out.println("Pro výpis podle vybrané částky zadejte 3");
                        temp=vstupInt();
                        ArrayList<Transaction> list;
                        switch (temp){
                            case 1 :
                                //System.out.println("Load1");
                                list=a.vypis(temp);
                            System.out.println("Zadejte rok a měsíc od kdy chcete transakce vypsat");
                                int yearStart = vstupInt();
                                int dayStart = vstupInt();
                                System.out.println("Zadejte rok a měsíc do kdy chcete transakce vypsat");
                                int yearEnd = vstupInt();
                                int dayEnd = vstupInt();
                                System.out.println("");
                                   for (int i=(list.size()-1);i>=0;i--){
                                    //System.out.println("case2for");
                                    //System.out.println("LaodFor");
                                    
                                    if (list.get(i).getYear()>=yearStart &&list.get(i).getMonth()>=dayStart && list.get(i).getYear()<=yearEnd &&list.get(i).getMonth()<=dayEnd){
                                        System.out.println("");
                                    System.out.print(list.get(i).getDay()+"-"+list.get(i).getMonth()+"-"+list.get(i).getYear());
                                    if(list.get(i).getMoneyShift()>0){
                                        System.out.print(" vklad ");
                                        System.out.print(list.get(i).getMoneyShift());
                                        System.out.print("Kč");
                                        System.out.println("");
                                    }else{
                                        System.out.print(" výběr ");
                                        System.out.print(Math.abs(list.get(i).getMoneyShift()));
                                        System.out.print("Kč");
                                        System.out.println("");
                                    }
                                    }
                                }
                                System.out.println("");
                                System.out.println("Zůstatek: "+a.zustatek()+"Kč");
                                
                            break;
                            case 2:
                                //System.out.println("case2");
                             list=a.vypis(temp);
                             
                                for (int i=0;i<list.size();i++){
                                    //System.out.println("case2for");
                                    //System.out.println("LaodFor");
                                    System.out.println("");
                                    System.out.print(list.get(i).getDay()+"-"+list.get(i).getMonth()+"-"+list.get(i).getYear());
                                    if(list.get(i).getMoneyShift()>0){
                                        System.out.print(" vklad ");
                                        System.out.print(list.get(i).getMoneyShift());
                                        System.out.print("Kč");
                                        System.out.println("");
                                    }else{
                                        System.out.print(" výběr ");
                                        System.out.print(Math.abs(list.get(i).getMoneyShift()));
                                        System.out.print("Kč");
                                        System.out.println("");
                                    }
                                }
                                break;
                            case 3:
                                list=a.vypis(2);
                                   for (int i=(list.size()-1);i>=0;i--){
                                    //System.out.println("case2for");
                                    //System.out.println("LaodFor");
                                    System.out.println("");
                                    System.out.print(list.get(i).getDay()+"-"+list.get(i).getMonth()+"-"+list.get(i).getYear());
                                    if(list.get(i).getMoneyShift()>0){
                                        System.out.print(" vklad ");
                                        System.out.print(list.get(i).getMoneyShift());
                                        System.out.print("Kč");
                                        System.out.println("");
                                    }else{
                                        System.out.print(" výběr ");
                                        System.out.print(Math.abs(list.get(i).getMoneyShift()));
                                        System.out.print("Kč");
                                        System.out.println("");
                                    }
                                }
                            break;   
                        }
                        
                        
                        
                        
                        
                        break;
                        
                        
                        
                        
                    case 4:
                        login = false;
                        break;
                    default:
                        System.out.println("Nevalidní vstup");
                        break;
                }

            } else {
                menu();

                ans = vstupInt();

                switch (ans) {
                    case 1:
                        //login 
                        login = menuLogin();
                        break;
                    case 2:

                        newUser();
                        break;
                    case 3:
                        end = false;
                        break;

                }
            }
        }
    }
/**
     *
     * Vypíše menu
     */
    private static void menu() {
        System.out.println("Vítejte v programu banka");
        System.out.println("Pro přihlášení zadejte 1");
        System.out.println("Pro vytvoření nového účtu zadejte 2");
        System.out.println("Pro ukončení zadejte 3");
    }
/**
     *
     * Vypíše menu pro nového uživatele
     */
    private static boolean newUser() {
        int temp;
        String login, pw1, pw2, name, surname;
        System.out.println("Prosím zadejte smé nové přihlašovací jméno");
        login = sc.next();
        System.out.println("Prosím zadejte své heslo");
        pw1 = sc.next();
        System.out.println("Prosím zopakujte své heslo");
        pw2 = sc.next();
        System.out.println("Prosím zopakujte své jméno");
        name = sc.next();
        System.out.println("Prosím zopakujte své prijmení");
        surname = sc.next();
        temp = a.newAccount(login, pw1, pw2, name, surname);
        System.out.println("");
        //1 hesla se neshodují
        //2 nepodařilo se vytvořit soubory
        //3 vše se podařilo
        //4 soubor existuje
        switch (temp) {
            case 1:
                System.out.println("Zadaná hesla se neshodují nebo obsahují nepovolené znaky");
                return false;
            case 2:
                System.out.println("Vyskytnul se problém při vytváření souborů");
                return false;
            case 3:
                System.out.println("Účet byl úspěšně vytvořen");
                return true;
            case 4:
                System.out.println("Zadané jméno již existuje");
                return false;
            default:
                System.out.println("Nevalidní vstup");
                return false;
        }
    }
/**
     *
     * Ověřuje integer
     * *@return - integer
     */
    private static int vstupInt() {
        int temp = -1;
        try {
            temp = sc.nextInt();
        } catch (InputMismatchException a) {
            //maže
            sc.nextLine();
        }
        return temp;
    }
/**
     *
     * Vypíše menu pro přihlašujícího se uživatele
     */
    private static boolean menuLogin() {
        //1 uživatel neexistuje
        //2 poškozený soubor
        //3 špatné heslo
        //4 načtení se podařilo
        //public int login(String login, String pw) throws FileNotFoundException, IOException{
        String login, pw;
        System.out.println("Prosím zadejte své prihlašovací jméno");
        login = sc.next();
        System.out.println("Prosím zadejte své heslo");
        pw = sc.next();
        int temp = a.login(login, pw);
        switch (temp) {
            case 1:
                System.out.println("zadané jméno neexistuje");
                return false;
            case 2:
                System.out.println("Vyskytnul se problém při vytváření souborů");
                return false;
            case 3:
                System.out.println("Zadané heslo není správné");
                return false;
            case 4:
                System.out.println("Jste úspěšně přihlášený");
                return true;
            default:
                System.out.println("Nevalidní vstup");
                return false;
        }

    }
/**
     *
     * Vypíše menu přihlášeného uřivatele
     */
    private static void menuLogged() {
        System.out.println("");
        System.out.println("Pro výběr zadejte 1 ");
        System.out.println("Pro vklad zadejte 2 ");
        System.out.println("Pro výpis transakcí zadejte 3");
        System.out.println("Pro odhlášení zadejte 4");
    }
    /**
     *
     * 
     */
    private static void saveToPdf(boolean vyber, int amount,String name, String surname){
        
        boolean exit= true;
        String ans;
        while (exit){
            System.out.println("Přejete si tuto platbu uložit jako PDF účtenku? (ano/ne)");
            ans=sc.next();
            if (ans.equals("ano")){
                a.uctenka(vyber,amount, name, surname);
                exit=false;
            }
            if(ans.equals("ne")){
                exit=false;
            }
         
        }
        
        
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import utils.WrongPw;

/**
 *
 * @author TheMy
 */
public class Account {
    private File transakce;
    private String name;
    private  String surname;
    private int money;

    /**
     *
     * @param login - String - uživatelské jmeno
     * @param pw - String - heslo
     * @return Integer - 1 uživatel neexistuje, 2 poškozený soubor, 3 špatné heslo, 4 načtení se podařilo
     * @throws FileNotFoundException
     * @throws IOException
     */
    public int login(String login, String pw) throws FileNotFoundException, IOException, WrongPw{
        //1 uživatel neexistuje
        //2 poškozený soubor
        //3 špatné heslo
        //4 načtení se podařilo
        
        String name;
        String surname;
        String pw1;
        
         File user = new File("data/" + login + ".dat");
         File userdata = new File("data/" + login + "_Data.txt");
         
         if(!user.exists()){
             return 1;
         }
         
                try (DataInputStream ucet = new DataInputStream(new FileInputStream("data/" + login + ".dat"))) {
                   
                try {
                   name=ucet.readUTF();
                   surname=ucet.readUTF();
                   pw1=ucet.readUTF();
                    
                } catch (EOFException e) {
                    return 2;
                }
            if (pw.equals(pw1)){
            this.name=name;
            this.transakce=new File("data/" + login + "_Data.txt");
                System.out.println(name);
                System.out.println(surname);
            this.surname=surname;
            }else{
                if(!pw.equals(pw1)){
                throw new WrongPw();
                }
                return 3;
            }
         
    }
    return 4;
    }
    
    /**
     *
     * @param login - String - přihlašovací jmeno
     * @param pw1 - String - heslo
     * @param pw2 - String - heslo pro ověření
     * @param name - String - jméno uživatele
     * @param surname  - String - přijmení uživatele
     * @return - integer - 1 hesla se neshodují, 2 nepodařilo se vytvořit soubory, 3 vše se podařilo, 4 soubor existuje
     * @throws FileNotFoundException
     * @throws IOException
     */
    public int createFiles(String login, String pw1, String pw2, String name, String surname) throws FileNotFoundException, IOException {
        //1 hesla se neshodují
        //2 nepodařilo se vytvořit soubory
        //3 vše se podařilo
        //4 soubor existuje
        Pattern p = Pattern.compile("[A-Z0-9]");
        Matcher m = p.matcher(pw1); 
        System.out.println(pw1);
        System.out.println(m.find());
      Matcher matches = Pattern.compile( "//w" ).matcher(pw1);
        System.out.println(matches.find(0));
        if (!pw1.equals(pw2) || !matches.find() ) {
            return 1;
        }
        File user = new File("data/" + login + ".dat");
        
        if (user.exists()){
            return 4;
        }

        if (!user.exists()) {
            try ( DataOutputStream write = new DataOutputStream(new FileOutputStream(user))) {
                write.writeUTF(name);
                write.writeUTF(surname);
                write.writeUTF(pw1);
                write.close();
                
            } catch (IOException e) {
                return 2;
            }
        }
        String time = LocalDate.now().toString();
        try {
            FileWriter myWriter = new FileWriter("data/" + login + "_Data.txt");
            myWriter.write(time + " Založení účtu");
            myWriter.close();
            
        } catch (IOException e) {
            return 2;
        }
        return 3;

    }
    
    public File getLog(){
        return this.transakce;
    }
    
    /**
     *
     * @param amount - integer - množství peněz, co se mají přidat
     */
    public void addMoney(int amount){
      File log = transakce; 
      try {
            String time = LocalDate.now().toString();
            BufferedWriter myWriter = new BufferedWriter(new FileWriter(transakce,true)); //změnit na transakce
            myWriter.newLine();
            myWriter.write(time+" ");
            myWriter.write("+ ");
            myWriter.write(Integer.toString(amount));
            myWriter.close();
            System.out.println(amount);
        } catch (IOException e) {
            
        } 
    }
    
     private void checkBalance() throws FileNotFoundException{
        Transactions_log a = new Transactions_log(transakce);
        money= a.getBalance();
      }
     
    /**
     *
     * @param amount - integer - monžství meněz, které se má vybrat
     * @return - boolean - true - částka byla vybrána, false - částku se napodařilo vybrat
     * @throws FileNotFoundException
     */
    public boolean removeMoney(int amount) throws FileNotFoundException{
        File log = transakce;
        checkBalance();
        if(money>=amount){
      try {
            String time = LocalDate.now().toString();
            BufferedWriter myWriter = new BufferedWriter(new FileWriter(transakce,true)); //změnit na transakce
            myWriter.newLine();
            myWriter.write(time+" ");
            myWriter.write("- ");
            myWriter.write(Integer.toString(amount));
            myWriter.close();
            System.out.println(amount);
            return true;
        } catch (IOException e) {
            
        }  
     }
        return false;
     }
     
     public String[] getinfo(){
      String[] out = {name,surname}; 
      return out;
     }
     
     public ArrayList<Transaction> getTransactionLog(int sort) throws FileNotFoundException{
         Transactions_log a = new Transactions_log(transakce);
         return a.getlog(sort);
          
     }
     public int getMoney() throws FileNotFoundException{
         checkBalance();
         return money;
     }
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
}

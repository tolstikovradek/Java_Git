/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banka;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author jakub
 */
public class Banka {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        createFile("123456781", "10.04.2019");
        vyber("123456781",600,"12.05.2019");
        System.out.println(zustatek("123456781"));
    }

    private static void createFile(String number, String accountCreateTime) throws FileNotFoundException, IOException {

        File resultFile = new File(number + ".dat");
        if (!resultFile.exists()) { //Přepsat !
            try (DataOutputStream inResult = new DataOutputStream(new FileOutputStream(resultFile))) {
               // inResult.writeUTF(number); 
                inResult.writeInt(0);
            }
            resultFile = new File(number + "_transakce.dat");

            try (DataOutputStream inResult = new DataOutputStream(new FileOutputStream(resultFile))) {
                inResult.writeUTF(accountCreateTime);
                inResult.writeUTF("Založení účtu");                
                inResult.writeInt(0);

            }
        }
    }

    private static void vyber(String number, int transakce, String time) throws FileNotFoundException, IOException {
        File resultFile = new File(number);
        int zustatek = 0;
        boolean end = false;
     
            try (DataInputStream ucet = new DataInputStream(new FileInputStream(number + ".dat"))) {
                   while (!end) {
                try {
                   // number = ucet.readUTF();
                    zustatek = ucet.readInt();
                } catch (EOFException e) {
                    end = true;
                }
            }
        }
        zustatek = zustatek - transakce;
       
        try (DataOutputStream inResult = new DataOutputStream(new FileOutputStream(resultFile + ".dat"))) {
           //inResult.writeUTF(number);//číslo
            inResult.writeInt(zustatek);
        }
        try (DataOutputStream inResult = new DataOutputStream(new FileOutputStream(resultFile + "_transakce.dat", true))) {
            inResult.writeUTF(time);
            inResult.writeUTF("Výběr");
            transakce = 0 - transakce;
            inResult.write(transakce);
        }

    }

    private static void vklad(String number, int transakce, String time) throws FileNotFoundException, IOException {
        File resultFile = new File(number);
        int zustatek = 0;
        boolean end = false;
        
            try (DataInputStream ucet = new DataInputStream(new FileInputStream(number + ".dat"))) {
                while (!end) {
                try {
                  // number = ucet.readUTF();//číslo
                    zustatek = ucet.readInt();
                } catch (EOFException e) {
                    end = true;
                }
            }
        }
        zustatek = zustatek + transakce;
        
        try (DataOutputStream inResult = new DataOutputStream(new FileOutputStream(resultFile + ".dat"))) {
            //inResult.writeUTF(number);//číslo
            inResult.writeInt(zustatek);
        }
        try (DataOutputStream inResult = new DataOutputStream(new FileOutputStream(resultFile + "_transakce.dat", true))) {
            inResult.writeUTF(time);
            inResult.writeUTF("Vklad");
            inResult.write(transakce);
        }

    }

    private static int zustatek(String number) throws FileNotFoundException, IOException {
               
        boolean end = false;
        
            int zustatek = 0;
            try (DataInputStream ucet = new DataInputStream(new FileInputStream(number + ".dat"))) {
                while (!end) {
                try {
                    //ucet.readUTF();
                    zustatek =  ucet.readInt();
                } catch (EOFException e) {
                    end = true;
                }
                return zustatek;
            }
        }
        return -1;
    }

}

package callendar;

import static callendar.CallendarMethods.printCallendar;
import java.util.Scanner;

/**
 *
 * @author Themy
 */
public class CallendarApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int ans;
        do {
            System.out.println("1 - Vytvořit kalendář");
            System.out.println("0 - Ukončit");
            ans = sc.nextInt();
            switch (ans) {
                case 0:
                    break;
                case 1:
                    int month;
                    int year;
                    System.out.println("Zadejte mesic");
                    month = sc.nextInt();
                    System.out.println("Zadejte rok");
                    year = sc.nextInt();
                    printCallendar(month, year);
                    break;
                default:
                    System.out.println("Zadejte platný výběr!");
                    System.out.println();
                    System.out.println();
                    ans = 2;
            }

        } while (ans > 0);
    }

}

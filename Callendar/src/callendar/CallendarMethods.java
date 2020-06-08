package callendar;

/**
 *
 * @author Themy
 */
public class CallendarMethods {

    public static int dayOfWeek(int day, int month, int year) {
        int h;
        int m;
        int K = year % 100;
        int J = year / 100;
        if (month == 1 || month == 2) {
            m = month + 12;
        } else {
            m = month;
        }
        h = (day + ((13 * (m + 1)) / 5) + K + K / 4 + J / 4 - 2 * J) % 7;
        return (h + 5) % 7;
    }

    public static int numberOfDays(int month, int year) {
        switch (month) {
            case 1:
                return 31;
            case 2:
                if (isIrregular(year)) {
                    return 29;
                } else {
                    return 28;
                }
            case 3:
                return 31;
            case 4:
                return 30;
            case 5:
                return 31;
            case 6:
                return 30;
            case 7:
                return 31;
            case 8:
                return 31;
            case 9:
                return 30;
            case 10:
                return 31;
            case 11:
                return 30;
            case 12:
                return 31;
            default:
                return -1;
        }
    }

    public static String month(int month) {
        switch (month) {
            case 1:
                return "LEDEN";
            case 2:
                return "UNOR";
            case 3:
                return "BREZEN";
            case 4:
                return "DUBEN";
            case 5:
                return "KVETEN";
            case 6:
                return "CERVEN";
            case 7:
                return "CERVENEC";
            case 8:
                return "SRPEN";
            case 9:
                return "ZARI";
            case 10:
                return "RIJEN";
            case 11:
                return "LISTOPAD";
            case 12:
                return "PROSINEC";
            default:
                return "ERROR";
        }
    }

    public static boolean isIrregular(int year) {
        return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
    }

    public static void printCallendar(int month, int year) {
        int day = 1;
        int firstDay = dayOfWeek(1, month, year);
        int lastDay = numberOfDays(month, year);
        System.out.println(month(month) + " " + year);
        System.out.println("PO UT ST ČT PÁ SO NE");
        // prázdná pole na začátku měsíce
        for (int i = 0; i < firstDay; i++) {
            System.out.print("   ");
        }
        //Výpis prvního řádku a odsazení ostatiích
        for (int i = 0; i < 7 - firstDay; i++) {
            if (day <= lastDay) {
                System.out.format("%2d ", day);
                day++;
            }

        }
        System.out.println();
        //zbytek řádků
       for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 7; j++) {
                if (day <= lastDay) {
                    System.out.format("%2d ", day);
                    day++;
                }
            }
            System.out.println("");
        }

    }

}

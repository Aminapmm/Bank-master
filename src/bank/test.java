package bank;
import bank.java.com.github.mfathi91.time.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.io.*;

public class test {

    static PersianDate d = PersianDate.now();

    static LocalTime t = LocalTime.now();
    static DateTimeFormatter p = DateTimeFormatter.ofPattern("HH:mm:ss");
    static String t1 = t.format(p);

    public static void main(String[] args) {
        //System.out.printf("%s %s",d,t1);
        try {
            FileWriter w = new FileWriter("C:\\Users\\datacomputer\\Desktop\\test123.txt");
            PrintWriter p = new PrintWriter(w);
            p.printf("Firstname: %s\n Lastname: %s\n Phonenumber: %s\n Customerid: %s\n","AMIN","AHMADPOUR",13254,789798);
            p.close();
        }
        catch (Exception e){
            System.out.println(e);
        }

    }
}

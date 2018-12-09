package collections;

import org.eclipse.collections.api.block.function.primitive.LongToByteFunction;
import org.eclipse.collections.api.block.function.primitive.LongToCharFunction;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.multimap.bag.HashBagMultimap;
import org.eclipse.collections.impl.multimap.list.FastListMultimap;
import quartz.scheduler.SimpleScheduler;

import javax.swing.*;
import java.awt.*;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Scanner;

public class MultiMapProject {
    public static HashBagMultimap<String, String> PeopleList = HashBagMultimap.newMultimap();


    public static boolean checkSum(int[] PESEL) {
        int sum = 1 * PESEL[0] +
                3 * PESEL[1] +
                7 * PESEL[2] +
                9 * PESEL[3] +
                1 * PESEL[4] +
                3 * PESEL[5] +
                7 * PESEL[6] +
                9 * PESEL[7] +
                1 * PESEL[8] +
                3 * PESEL[9];
        sum %= 10;
        sum = 10 - sum;
        sum %= 10;

        if (sum == PESEL[10]) {
            return true;
        }
        else {
            return false;
        }
    }

    public static void main(String[] args) throws InterruptedException{
        String miasto;
        String imie;
        String nazwisko;
        Boolean end = true;
        long PESEL;
        int licznik = 0;
        //Scanner S = new Scanner(System.in);
        while (licznik != 3) {
            Scanner S = new Scanner(System.in);
            miasto = S.next();
            imie = S.next();
            nazwisko = S.next();
            PESEL = S.nextLong();
            int PES[] = Long.toString(PESEL).chars().map(c -> c-'0').toArray();
            if (Long.toString(PESEL).length() == 11) {
                System.out.println("Podales 11 cyfr");
                System.out.println(Arrays.toString(PES));
            }
            if (checkSum(PES)) {
                System.out.println("Numer PESEL zgodny.");
                PeopleList.put(miasto, imie + " " + nazwisko + " " + PESEL);
                licznik++;
                //end = false;
            }
            else
            {
              System.out.println("Podales niepoprawny PESEL. Wprowadz dane jeszcze raz.");
            }
        }

        PeopleList.get("Poznan")
                .forEach(name -> System.out.println(name));

        //System.out.println("Poznan  citizens number:" + citiesToPeople.get("Poznan").size());
        //System.out.println("Berlin  citizens number:" + citiesToPeople.get("Berlin").size());

        HashBagMultimap<String, String> citiesToPeopleNoOrder = HashBagMultimap.newMultimap();

        citiesToPeopleNoOrder.put("Poznan", "Nowak");
        citiesToPeopleNoOrder.put("Poznan", "Kowalski");
        citiesToPeopleNoOrder.put("Poznan", "Abraham");

        citiesToPeopleNoOrder.put("Berlin", "Muller");
        citiesToPeopleNoOrder.put("Berlin", "Nowak");
        citiesToPeopleNoOrder.put("Berlin", "Bond");
        citiesToPeopleNoOrder.put("Berlin", "Tyson");
        MutableList<String> list = citiesToPeopleNoOrder.get("Poznan").toList();

        list.forEach(name -> System.out.println(name));

        System.out.println("Poznan  citizens number:" + citiesToPeopleNoOrder.get("Poznan").size());
        System.out.println("Berlin  citizens number:" + citiesToPeopleNoOrder.get("Berlin").size());
        SimpleScheduler.main(null);
    }


}

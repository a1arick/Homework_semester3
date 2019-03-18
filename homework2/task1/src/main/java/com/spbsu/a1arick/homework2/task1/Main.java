package com.spbsu.a1arick.homework2.task1;

import java.util.*;

public class Main {
    public static void comands() {
        System.out.println("Available commands:");
        System.out.println("'add(id, os):'  1) id computer 2) operation system(Windows, IOS, Android, Linux, MAC_OS");
        System.out.println("'connect(id, id):'  1) id computer 2)id computer");
        System.out.println("'infect(id, Virus, power):'  1) id computer 2)name of virus 3) power virus");

    }

    public static void main(String[] args) {
        Network network = new Network();
        Scanner in = new Scanner(System.in).useLocale(Locale.US);
        comands();
        String choice;
        do {
            choice = in.next();
            switch (choice) {
                case "add":
                    int id = in.nextInt();
                    String os = in.next();
                    network.add(id, os);
                    System.out.println("ok");
                    break;
                case "connect":
                    int id1 = in.nextInt();
                    int id2 = in.nextInt();
                    network.connect(id1, id2);
                    System.out.println("ok");
                    break;
                case "infect":
                    int idInfection = in.nextInt();
                    String name = in.next();
                    double power = in.nextDouble();
                    network.infect(idInfection, new Virus(name, power));
                    break;
            }
        } while (!choice.equals("exit"));

    }
}

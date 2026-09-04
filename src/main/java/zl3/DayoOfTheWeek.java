package zl3;

import java.util.Scanner;

public class DayoOfTheWeek {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj liczbę całkowitą od 1 do 7.");
        int number = scanner.nextInt();
        String day = switch (number) {
            case 1 -> "Poniedziałek";
            case 2 -> "Wtorek";
            case 3 -> "Środa";
            case 4 -> "Czwartek";
            case 5 -> "Piątek";
            case 6 -> "Sobota";
            case 7 -> "Niedziela";
            default -> throw new IllegalArgumentException("Niepoprawna wartość: " + number);
        };
        System.out.println(day);
        if (number >= 1 && number <= 5) {
            System.out.println("Dzień roboczy");
            System.out.println("Godzina odjazdu pierwszego autobusu: Pn-Pt: 05:30");
        } else {
            System.out.println("Weekend");
            if (number == 6) {
                System.out.println("Godzina odjazdu pierwszego autobusu: So: 07:00");
            } else {
                System.out.println("Godzina odjazdu pierwszego autobusu: Nd: 09:00");
            }
        }
    }
}

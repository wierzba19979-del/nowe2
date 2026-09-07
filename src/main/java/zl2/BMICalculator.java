package zl2;

import java.util.Scanner;

public class BMICalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj wagę [kg]:");
        double waga = scanner.nextDouble();
        System.out.println("Podaj wzrost [cm]");
        double wzrostCm = scanner.nextDouble();
        if (wzrostCm < 30 || wzrostCm > 250)
            throw new IllegalArgumentException("Podany wzrost jest nieprawidłowy");
        double wzrostm = wzrostCm / 100.0;
        double bmi = waga / (wzrostm * wzrostm);
        System.out.println(String.format("%.2f", bmi));

        if (bmi < 18.5)
            System.out.println("Niedowaga");
        else if (bmi >= 18.5 && bmi <= 25) {
            System.out.println("Norma");
        } else if (bmi > 25 && bmi <= 30) {
            System.out.println("Nadwaga");
        } else if (bmi > 30) {
            System.out.println("Otyłość");
        }

    }


}

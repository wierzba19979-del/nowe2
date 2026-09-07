package pd1;

import java.util.Scanner;

public class MatrixOperations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] matrix = new int[3][3];

        System.out.println("Podaj dziewić liczb, które chcesz wprowadzić do macierzy.");
        int counter = 1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.println("Wprowadź liczbę nr:" + counter + ":");
                matrix[i][j] = scanner.nextInt();
                counter++;
            }
        }

        System.out.println("Macierz z wprowadzonymi liczbami");
        for (int[] ints : matrix) {
            for (int number : ints) {
                System.out.print(number + " ");
            }
            System.out.println();
        }

        int sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                sum = sum + matrix[i][j];
            }
            System.out.println("Suma wartości w wierszu nr:" + (i + 1) + " wynosi:" + sum);
            sum = 0;
        }

        int sum2 = 0;
        for (int j = 0; j < matrix[0].length; j++) {
            for (int i = 0; i < matrix.length; i++) {
                sum2 = sum2 + matrix[i][j];
            }
            System.out.println("Suma wartości w kolumnie nr:" + (j + 1) + " wynosi:" + sum2);
            sum2 = 0;
        }

        boolean isSimetric = true;
        outer:
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != matrix[j][i]) {
                    isSimetric = false;
                    break outer;
                }
            }
        }
        if (isSimetric) {
            System.out.println("Macierz jest symeryczna");
        } else {
            System.out.println("Macierz nie jest symetryczna");
        }

        int[][] matrixTransposition = new int[matrix[0].length][matrix.length];

        for (int i = 0; i < matrixTransposition.length; i++) {
            for (int j = 0; j < matrixTransposition[i].length; j++) {
                matrixTransposition[j][i] = matrix[i][j];
            }
        }

        System.out.println("Transpozycja macierzy");
        for (int[] ints : matrixTransposition) {
            for (int number : ints) {
                System.out.print(number + " ");
            }
            System.out.println();
        }

        int[][] matrixRotation = new int[matrix.length][matrix[0].length];

        for (int i = 0; i < matrixRotation.length; i++) {
            for (int j = 0; j < matrixRotation[i].length; j++) {
                matrixRotation[j][matrixRotation.length - 1 - i] = matrix[i][j];
            }
        }

        System.out.println("Macierz obrócona o 90 stopni w prawo:");
        for (int[] ints : matrixRotation) {
            for (int number : ints) {
                System.out.print(number + " ");
            }
            System.out.println();
        }
    }
}

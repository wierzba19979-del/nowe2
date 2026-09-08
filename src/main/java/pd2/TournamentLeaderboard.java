package pd2;

import java.util.Scanner;

public class TournamentLeaderboard {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj liczbę graczy (od 2 do 10):");
        int numberOfPlayers = scanner.nextInt();
        Player[] players = new Player[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.println("Podaj imię gracza " + (i + 1) + ":");
            String name = scanner.next();
            int[] scores = new int[3];
            for (int j = 0; j < scores.length; j++) {
                System.out.println("Podaj wynik " + (j + 1) + ":");
                scores[j] = scanner.nextInt();
            }
            players[i] = new Player(name, scores);
        }

        PlayerSorter.sortBySum(players);

        int higestSingleScore = players[0].getMax();
        for (int i = 0; i < players.length; i++) {
            if (players[i].getMax() > higestSingleScore) {
                higestSingleScore = players[i].getMax();
            }

        }

        for (int i = 0; i < players.length; i++) {
            Player player = players[i];
            String star = "";
            if (player.getMax() == higestSingleScore) {
                star = "*";
            }
            System.out.println("Miejsce " + (i + 1) + " - " +
                    player.getName() + ", suma punktów:" +
                    player.getSum() + ", średnia:" +
                    player.getAverage() + ", minimalny wynik:" +
                    player.getMin() + ", maksymalny wynik" +
                    player.getMax() + " - " +
                    star);
        }
    }
}

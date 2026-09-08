package pd2;

public class PlayerSorter {
    /**
     * Sortuje tablicę graczy malejąco według sumy punktów. *
     *
     * @param players tablica graczy do posortowania
     */
    public static void sortBySum(Player[] players) {
        for (int i = 0; i < players.length - 1; i++) {
            for (int j = 0; j < players.length - 1 - i; j++) {
                if (players[j].getSum() < players[j + 1].getSum()) {
                    Player temporary = players[j];
                    players[j] = players[j + 1];
                    players[j + 1] = temporary;
                }
            }
        }
    }
}

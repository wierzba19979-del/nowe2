package pd2;

public class Player {
    private String name;
    private int[] scores;
    /**
     * Tworzy nowego gracza.
     * @param name imię gracza
     * @param scores tablica trzech wyników gracza
     */
    public Player(String name, int[] scores) {
        this.name = name;
        this.scores = scores;
    }
    /**
     * Zwraca imię gracza
     *
     * @return imię gracza
     */
    public String getName() {
        return name;
    }

    /**
     * Oblicza sumę punktów gracza.
     *
     * @return suma punktów
     */
    public int getSum() {
        int sum = 0;
        for (int score : scores) {
            sum += score;
        }
        return sum;
    }

    /**
     * Oblicza średni wynik gracza
     *
     * @return średni wynik
     */
    public double getAverage() {
        double average = (double)getSum() / scores.length;
        return average;
    }

    /**
     * Znajduje najmniejszy wynik gracza
     *
     * @return najmniejszy wynik
     */
    public int getMin() {
        int min = scores[0];
        for (int score : scores) {
            if (score < min) {
                min = score;
            }
        }
        return min;
    }

    /**
     * Znajduje największy wynik gracza
     *
     * @return największy wynik
     */
    public int getMax() {
        int max = scores[0];
        for (int score : scores) {
            if (score > max) {
                max = score;
            }
        }
        return max;
    }
}

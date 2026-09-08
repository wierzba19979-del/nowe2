package pd2;

public class Player {
    private String name;
    private int[] scores;

    public Player(String name, int[] score) {
        this.name = name;
        this.scores = score;
    }

    public String getName() {
        return name;
    }

    public int getSum() {
        int sum = 0;
        for (int score : scores) {
            sum += score;
        }
        return sum;
    }

    public double getAverage() {
        double average = (double)getSum() / scores.length;
        return average;
    }

    public int getMin() {
        int min = scores[0];
        for (int score : scores) {
            if (score < min) {
                min = score;
            }
        }
        return min;
    }

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

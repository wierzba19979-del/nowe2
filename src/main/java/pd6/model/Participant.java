package pd6.model;

import lombok.Getter;

@Getter
public abstract class Participant {
    private final String name;
    private int points;

    protected Participant(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Nazwa uczestnika nie może być pusta.");
        }
        this.name = name;
        this.points = 0;
    }

    void addPoints(int points) {
        if (points < 0) {
            throw new IllegalArgumentException("Nie można dodać ujemnej liczby punktów");
        }
        this.points += points;
    }
}

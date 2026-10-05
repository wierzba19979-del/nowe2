package pd6.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PointsCalculator {
    private static final int WIN_POINTS = 3;
    private static final int DRAW_POINTS = 1;

    public static void calculate(Match match) {
        switch (match.getResult()) {
            case HOME_WIN -> match.getHome().addPoints(WIN_POINTS);
            case DRAW -> {
                match.getHome().addPoints(DRAW_POINTS);
                match.getAway().addPoints(DRAW_POINTS);
            }
            case AWAY_WIN -> match.getAway().addPoints(WIN_POINTS);
        }
    }
}

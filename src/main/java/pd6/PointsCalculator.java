package pd6;

public class PointsCalculator {
    private final static int WIN_POINTS = 3;
    private final static int DRAW_POINTS = 1;

    public void calculate(Match match){
        switch (match.getResult()){
            case HOME_WIN -> match.getHome().addPoints(WIN_POINTS);
            case DRAW -> {
                match.getHome().addPoints(DRAW_POINTS);
                match.getAway().addPoints(DRAW_POINTS);
            }
            case AWAY_WIN -> match.getAway().addPoints(WIN_POINTS);
        }
    }
}

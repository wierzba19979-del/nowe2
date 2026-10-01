package pd6;

import lombok.Getter;

@Getter
public class Match {
    private final Participant home;
    private final Participant away;
    private final MatchResult result;

    public Match(Participant home, Participant away, MatchResult result) {
        if(home == null||away==null){
            throw new IllegalArgumentException("Uczestnicy nie mogą być nullem");
        }
        if(home == away){
            throw new IllegalArgumentException("Uczestnik nie moze rozegrać meczu sam ze sobą");
        }
        if(result == null){
            throw new IllegalArgumentException("Wynik meczu nie może być nullem");
        }
        this.home = home;
        this.away = away;
        this.result = result;
    }
}

package pd6;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class Tournament {
    private final List<Participant> participants = new ArrayList<>();
    private final List<Match> matches = new ArrayList<>();
    private final PointsCalculator pointsCalculator;

    public Tournament(PointsCalculator pointsCalculator) {
        this.pointsCalculator = pointsCalculator;
    }

    public void addParticipant(Participant participant) {
        if (participant == null) {
            throw new IllegalArgumentException("Uczestnik nie może być nullem");
        }
        if (participants.contains(participant)){
            throw new IllegalArgumentException("Uczestnik jest już zapisany");
        }
        participants.add(participant);
    }

    public Match playMatch(Participant home, Participant away, MatchResult result) {
        if (!participants.contains(home) || !participants.contains(away)) {
            throw new IllegalArgumentException("Obaj uczestnicy muszą być zapisani na turniej");
        }
        Match match = new Match(home, away, result);
        pointsCalculator.calculate(match);
        matches.add(match);
        return match;
    }

    public List<Match> getMatches() {
        return List.copyOf(matches);
    }

    public List<Participant> getParticipants() {
        return List.copyOf(participants);
    }

    public List<Participant> getTable() {
        return participants.stream()
                .sorted(Comparator.
                        comparingInt(Participant::getPoints)
                        .reversed()
                        .thenComparing(Participant::getName))
                .toList();
    }

    public <T extends Participant> List<T> getParticipantsOfType(Class<T> type) {
        return participants.stream()
                .filter(type::isInstance)
                .map(type::cast)
                .toList();
    }
}

package pd6.service;


import lombok.NoArgsConstructor;
import pd6.model.Match;
import pd6.model.MatchResult;
import pd6.model.Participant;
import pd6.model.PointsCalculator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@NoArgsConstructor

public class Tournament {
    private List<Participant> participants;
    private List<Match> matches;

    public void initialize (){
        participants = new ArrayList<>();
        matches = new ArrayList<>();
    }

    public void addParticipant(Participant participant) {
        if (participant == null) {
            throw new IllegalArgumentException("Uczestnik nie może być nullem");
        }
        if (participants.contains(participant)) {
            throw new IllegalArgumentException("Uczestnik jest już zapisany");
        }
        participants.add(participant);
    }

    public Match recordMatch(Participant home, Participant away, MatchResult result) {
        if (home == null || away == null) {
            throw new IllegalArgumentException("Uczestnicy nie mogą być nullem");
        }
        if (home == away) {
            throw new IllegalArgumentException("Uczestnik nie moze rozegrać meczu sam ze sobą");
        }
        if (result == null) {
            throw new IllegalArgumentException("Wynik meczu nie może być nullem");
        }
        if (!participants.contains(home) || !participants.contains(away)) {
            throw new IllegalArgumentException("Obaj uczestnicy muszą być zapisani na turniej");
        }
        Match match = new Match(home, away, result);
        PointsCalculator.calculate(match);
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

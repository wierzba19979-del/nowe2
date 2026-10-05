package pd6;

import pd6.model.*;
import pd6.service.Tournament;

public class Main {
    public static void main(String[] args) {
        Tournament tournament = new Tournament();
        tournament.initialize();
        Player player1 = new Player("Marek");
        Player player2 = new Player("Jarek");
        Player player3 = new Player("Darek");

        Team team1 = new Team("Kasztany");
        Team team2 = new Team("Dzbany");
        Team team3 = new Team("Tulipany");

        tournament.addParticipant(player1);
        tournament.addParticipant(player2);
        tournament.addParticipant(player3);

        tournament.addParticipant(team1);
        tournament.addParticipant(team2);
        tournament.addParticipant(team3);

        tournament.recordMatch(player1, player2, MatchResult.HOME_WIN);
        tournament.recordMatch(player1, player3, MatchResult.HOME_WIN);
        tournament.recordMatch(player2, player3, MatchResult.HOME_WIN);
        tournament.recordMatch(team1, player2, MatchResult.HOME_WIN);
        tournament.recordMatch(player1, team2, MatchResult.HOME_WIN);
        tournament.recordMatch(team3, player2, MatchResult.HOME_WIN);
        tournament.recordMatch(team3, team1, MatchResult.DRAW);

        for (Participant participant : tournament.getTable()) {
            System.out.println(participant.getName() + participant.getPoints());
        }

        for (Match match : tournament.getMatches()) {
            System.out.println(match.getHome().getName() + " vs " + match.getAway().getName()
                    + " wynik: " + match.getResult());
        }

        for (Player player : tournament.getParticipantsOfType(Player.class)) {
            System.out.println(player.getName() + " - " + player.getPoints() + " pkt");
        }

        for (Team team : tournament.getParticipantsOfType(Team.class)) {
            System.out.println(team.getName() + " - " + team.getPoints() + " pkt");
        }
    }
}

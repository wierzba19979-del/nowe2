package pd6.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Match {
    private final Participant home;
    private final Participant away;
    private final MatchResult result;
}

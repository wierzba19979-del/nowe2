package pd9;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Event {
    private String name;
    private String type;
    private int priority;
}

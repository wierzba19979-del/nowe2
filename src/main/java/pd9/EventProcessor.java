package pd9;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class EventProcessor {
    public void process(
            List<Event> events,
            List<Predicate<Event>> filters,
            Function<Event, String> formatter,
            Consumer<String> output) {

        events.forEach(event -> {
            boolean matches = true;
            for (Predicate<Event> filter : filters) {
                if (!filter.test(event)) {
                    matches = false;
                    break;
                }
            }
            if (matches) {
                String result = formatter.apply(event);
                output.accept(result);
            }
        });
    }
}

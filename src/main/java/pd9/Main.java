package pd9;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) {
        List<Event> events = Arrays.asList(
                new Event("Login", "SECURITY", 1),
                new Event("Logout", "SECURITY", 5),
                new Event("Payment", "BUSINESS", 3),
                new Event("Error", "SYSTEM", 4)
        );

        EventProcessor processor = new EventProcessor();

        Predicate<Event> highPriority = event -> event.getPriority() >= 3;

        Predicate<Event> security = event -> event.getType().equals("SECURITY");

        Predicate<Event> system = event -> event.getType().equals("SYSTEM");

        Function<Event, String> simpleFormatter = event -> event.getName();

        Function<Event, String> detailedFormatter =
                event -> "["
                        + event.getType()
                        + "] "
                        + event.getName()
                        + " | Priority: "
                        + event.getPriority();

        Function<Event, String> warningFormatter =
                event -> "WARNING: "
                        + event.getName()
                        + " (priority "
                        + event.getPriority()
                        + ")";

        Consumer<String> consoleOutput = message -> System.out.println(message);

        processor.process(events, Arrays.asList(highPriority), simpleFormatter, consoleOutput);

        System.out.println("=== Security events ===");
        processor.process(events, Arrays.asList(security, highPriority), detailedFormatter, consoleOutput);

        System.out.println("=== System events ===");
        processor.process(events, Arrays.asList(system), warningFormatter, consoleOutput);

        System.out.println("=== All events ===");
        processor.process(events, Arrays.asList(), detailedFormatter, consoleOutput);
    }
}

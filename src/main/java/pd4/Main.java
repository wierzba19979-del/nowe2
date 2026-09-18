package pd4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Resource> resources = new ArrayList<>(List.of(
                new Book(1, "Niezwyciężony", 44.99, ResourceType.BOOK, "Stanisław Lem"),
                new Book(2, "Potop", 49.99, ResourceType.BOOK, "Henryk Sienkiewicz"),
                new Movie(3, "Chłopaki nie płaczą", 39.99, ResourceType.MOVIE, 96),
                new Movie(4, "Kiler", 34.99, ResourceType.MOVIE, 104)));
        RentalSystem system = RentalSystem.initialize();
        system.addRental(new Rental(resources.get(0), RentalStatus.ACTIVE, 3));
        system.addRental(new Rental(resources.get(1), RentalStatus.OVERDUE, 4));
        system.addRental(new Rental(resources.get(2), RentalStatus.RETURNED, 5));
        system.addRental(new Rental(resources.get(3), RentalStatus.ACTIVE, 2));
        System.out.println("Sortowanie po cenie bazowej:");
        resources.sort(null);
        for (Resource resource : resources) {
            System.out.println(resource.getName() + " - " + resource.getBasePrice());
        }
        System.out.println("Sortowanie po nazwie:");
        resources.sort(Comparator.comparing(Resource::getName));
        for (Resource resource : resources) {
            System.out.println(resource.getName() + " - " + resource.getBasePrice());
        }


        System.out.println("Całkowity koszt wypożyczenia:" + system.calculateTotalCost());
        System.out.println("Ilość pozycji ze statusem ACTIVE:" + system.countByStatus(RentalStatus.ACTIVE));
        System.out.println("Ilość pozycji ze statusem RETURNED:" + system.countByStatus(RentalStatus.RETURNED));
        System.out.println("Ilość pozycji ze statusem OVERDUE:" + system.countByStatus(RentalStatus.OVERDUE));

        for (RentalSummary summary : system.getSummaries()) {
            System.out.println(summary);
        }
    }
}
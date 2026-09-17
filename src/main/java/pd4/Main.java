package pd4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Resource> resources = new ArrayList<>();
        Book book1 = new Book(1, "Niezwyciężony", 44.99, ResourceType.BOOK, "Stanisław Lem");
        Book book2 = new Book(2, "Potop", 49.99, ResourceType.BOOK, "Henryk Sienkiewicz");
        Movie movie1 = new Movie(3, "Chłopaki nie płaczą", 39.99, ResourceType.MOVIE, 96);
        Movie movie2 = new Movie(4, "Kiler", 34.99, ResourceType.MOVIE, 104);
        resources.add(book1);
        resources.add(book2);
        resources.add(movie1);
        resources.add(movie2);
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
        Rental rental1 = new Rental(book1, RentalStatus.ACTIVE, 3);
        Rental rental2 = new Rental(book2, RentalStatus.OVERDUE, 4);
        Rental rental3 = new Rental(movie1, RentalStatus.RETURNED, 5);
        Rental rental4 = new Rental(movie2, RentalStatus.ACTIVE, 2);

        RentalSystem system = new RentalSystem();
        system.addRental(rental1);
        system.addRental(rental2);
        system.addRental(rental3);
        system.addRental(rental4);
        System.out.println("Całkowity koszt wypożyczenia:" + system.calculateTotalCost());
        System.out.println("Ilość pozycji ze statusem ACTIVE:" + system.countByStatus(RentalStatus.ACTIVE));
        System.out.println("Ilość pozycji ze statusem RETURNED:" + system.countByStatus(RentalStatus.RETURNED));
        System.out.println("Ilość pozycji ze statusem OVERDUE:" + system.countByStatus(RentalStatus.OVERDUE));

        RentalSummary summary1 = rental1.getSummary();
        RentalSummary summary2 = rental2.getSummary();
        RentalSummary summary3 = rental3.getSummary();
        RentalSummary summary4 = rental4.getSummary();

        System.out.println(summary1);
        System.out.println(summary2);
        System.out.println(summary3);
        System.out.println(summary4);
    }
}
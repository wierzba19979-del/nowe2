package pd4;

import java.util.ArrayList;
import java.util.List;

public class RentalSystem {
    private final List<Rental> rentals;

    public RentalSystem() {
        this.rentals = new ArrayList<>();
    }
    public void addRental(Rental rental){
        rentals.add(rental);
    }
    public double calculateTotalCost(){
        double total = 0;
        for (Rental rental : rentals){
            total += rental.calculateCost();
        }
        return total;
    }
    public int countByStatus(RentalStatus status){
        int counter = 0;
        for (Rental rental : rentals) {
            if(rental.getStatus() == status){
                counter++;
            }
        }
        return counter;
    }
}

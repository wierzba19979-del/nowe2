package pd4;

public class Rental {
    private final Resource resource;
    private RentalStatus status;
    private final int days;

    public Rental(Resource resource, RentalStatus status, int days) {
        this.resource = resource;
        this.status = status;
        this.days = days;
    }

    public Resource getResource() {
        return resource;
    }

    public RentalStatus getStatus() {
        return status;
    }

    public int getDays() {
        return days;
    }

    public void setStatus(RentalStatus status) {
        this.status = status;
    }

    public double calculateCost(){
        return getResource().calculateRentalCost(days);
    }

    public RentalSummary getSummary(){
        return new RentalSummary(resource.getName(), status,calculateCost());
    }
}

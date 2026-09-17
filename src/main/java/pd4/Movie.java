package pd4;

public final class Movie extends Resource{
    private int duration;

    public Movie(int id, String name, double basePrice, ResourceType type, int duration) {
        super(id, name, basePrice, type);
        this.duration = duration;
    }

    @Override
    public double calculateRentalCost(int days) {
        return getBasePrice() + (2 * days);
    }

    public int getDuration() {
        return duration;
    }
}

package pd4;

public final class Movie extends Resource{
    private final int duration;
    private static final double EXTRA_FEE_FOR_MOVIE = 10.0;
    public Movie(int id, String name, double basePrice, ResourceType type, int duration) {
        super(id, name, basePrice, type);
        this.duration = duration;
    }

    @Override
    public double calculateRentalCost(int days) {
        return getBasePrice() * days + EXTRA_FEE_FOR_MOVIE;
    }

    public int getDuration() {
        return duration;
    }
}

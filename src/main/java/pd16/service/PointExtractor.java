package pd16.service;

import pd16.model.Point;

public class PointExtractor {

    public static Point extract(Point point) {
        if (point == null) {
            throw new IllegalArgumentException("User location is missing");
        }
        double latitude = point.latitude();
        double longitude = point.longitude();
        if (!Double.isFinite(latitude) || !Double.isFinite(longitude)
                || latitude < -90 || latitude > 90
                || longitude < -180 || longitude > 180) {
            throw new IllegalArgumentException("Invalid geographic coordinates");
        }
        return point;
    }
}
package pd16.service;

import pd16.model.Geo;
import pd16.model.Point;

public class PointExtractor {
    public static Point extract(Geo geo) {
        if (geo == null) {
            throw new IllegalArgumentException("Geographical coordinates are missing");
        }
        try {
            double latitude = Double.parseDouble(geo.latitude());
            double longitude = Double.parseDouble(geo.longitude());
            if (!Double.isFinite(latitude)
                    || !Double.isFinite(longitude)
                    || latitude < -90 || latitude > 90
                    || longitude < -180 || longitude > 180) {
                throw new IllegalArgumentException("Geographical coordinates are out of range");
            }
            return new Point(latitude, longitude);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid geographical coordinates: "
                    + geo.latitude() + ", " + geo.longitude(), e);
        }
    }
}


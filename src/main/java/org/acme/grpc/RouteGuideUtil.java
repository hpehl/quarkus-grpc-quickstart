package org.acme.grpc;

/** Common utilities for the RouteGuide demo. */
class RouteGuideUtil {

    private static final double COORD_FACTOR = 1e7;

    /** Gets the latitude for the given point. */
    static double getLatitude(Point location) {
        return location.getLatitude() / COORD_FACTOR;
    }

    /** Gets the longitude for the given point. */
    static double getLongitude(Point location) {
        return location.getLongitude() / COORD_FACTOR;
    }

    /** Indicates whether the given feature exists (i.e. has a valid name). */
    static boolean exists(Feature feature) {
        return feature != null && !feature.getName().isEmpty();
    }
}

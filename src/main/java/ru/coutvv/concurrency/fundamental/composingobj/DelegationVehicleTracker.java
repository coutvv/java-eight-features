package ru.coutvv.concurrency.fundamental.composingobj;

import net.jcip.annotations.Immutable;
import net.jcip.annotations.ThreadSafe;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by lomovtsevrs on 01.03.2017.
 */
@ThreadSafe
public class DelegationVehicleTracker {

    private final ConcurrentMap<String, Point> locations;
    private final Map<String, Point> unmodifiableMap;

    public DelegationVehicleTracker(Map<String, Point> points) {
        locations = new ConcurrentHashMap<>(points);
        unmodifiableMap = Collections.unmodifiableMap(locations);
    }

    public Map<String, Point> getLocations() {
        return unmodifiableMap;
    }

    public Point getLocation(String id) {
        return locations.get(id);
    }

    public void setLocation(String id, int x, int y) {
        if(locations.replace(id, new Point(x, y)) == null) {
            throw new IllegalArgumentException("invalid vehicle name: " + id);
        }
    }

    @Immutable
    public static class Point {
        public final int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

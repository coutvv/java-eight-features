package ru.coutvv.concurrency.fundamental.composingobj;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.NotThreadSafe;
import net.jcip.annotations.ThreadSafe;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lomovtsevrs on 01.03.2017.
 */
@ThreadSafe
public class MonitorVehicleTracker {
    @GuardedBy("this")
    private final Map<String, MutablePoint> locations;

    public MonitorVehicleTracker(Map<String, MutablePoint> locations) {
        this.locations = locations;
    }

    public synchronized Map<String, MutablePoint> getLocations() {
        return deepCopy(locations);
    }

    public synchronized MutablePoint getLocation(String id) {
        MutablePoint loc = locations.get(id);
        return loc == null? null : new MutablePoint(loc);
    }

    public synchronized void setLocation(String id, int x, int y) {
        MutablePoint loc = locations.get(id);
        if(loc == null)
            throw new IllegalArgumentException("No such ID: " + id);
        loc.x = x;
        loc.y = y;
    }


    private static Map<String, MutablePoint> deepCopy(Map<String, MutablePoint> m) {
        Map<String, MutablePoint> result = new HashMap<>();
        for(String id : m.keySet()) {
            result.put(id, new MutablePoint(m.get(id)));
        }
        return Collections.unmodifiableMap(result);
    }

    @NotThreadSafe
    public static class MutablePoint {
        public int x,y;
        public MutablePoint() {x = 0; y = 0;}

        public MutablePoint(MutablePoint p) {
            this.x = p.x;
            this.y = p.y;
        }
    }
}

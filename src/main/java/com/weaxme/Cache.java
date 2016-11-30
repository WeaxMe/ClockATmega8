package com.weaxme;

import com.weaxme.clock.Clock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @author Vitaliy Gonchar
 */
public class Cache {
    private static final Logger LOG = LoggerFactory.getLogger(Cache.class);

    private static final Map<String, Clock> clockCache = new HashMap<>();

    public static boolean addNewClockConfig(Clock clock) {
        if (!clockCache.containsKey(clock.getName())) {
            clockCache.put(clock.getName(), clock);
            return true;
        } else {
            LOG.warn("This clock is already exists");
            return false;
        }
    }

    public static boolean editClockConfig(String key, Clock clock) {
        if (key.equals(clock.getName()) && !clockCache.get(key).equals(clock)) {
            clockCache.put(key, clock);
            return true;
        } else if (!clockCache.containsKey(clock.getName())){
            clockCache.remove(key);
            clockCache.put(clock.getName(), clock);
            return true;
        } else {
            LOG.warn("Cannot edit this clock: " + clockCache.get(key));
            return false;
        }
    }

    public static void deleteClock(Clock clock) {
        if (clockCache.containsKey(clock.getName())) clockCache.remove(clock.getName());
    }

    public static Clock getClockConfig(String name) {
        return clockCache.containsKey(name) ? clockCache.get(name) : null;
    }

    public static List<Clock> getAllClocks() {

        return Collections.list(Collections.enumeration(clockCache.values()));
    }

    public static int getSize() {
        return clockCache.size();
    }
}

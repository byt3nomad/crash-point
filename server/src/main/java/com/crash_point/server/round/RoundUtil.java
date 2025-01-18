package com.crash_point.server.round;

public class RoundUtil {
    public static double round(double number) {
        return Math.round(number * 100.0) / 100.0;
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

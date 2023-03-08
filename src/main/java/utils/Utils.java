package utils;

public class Utils {

    public static String randomMail() {
        return System.currentTimeMillis() + "@gmail.com";
    }

    public static void waitForMillis(double millis) {
        try {
            Thread.sleep((long) (millis));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}

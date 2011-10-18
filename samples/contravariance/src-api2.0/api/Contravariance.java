package api;

public class Contravariance {
    private Contravariance() {
    }

    // BEGIN: variance.contravariance.v2
    public static boolean isPositive(Number n1) {
        return n1.doubleValue() > 0.0d;
    }
    // END: variance.contravariance.v2
}

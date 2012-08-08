package api;

public class Contravariance {
    private Contravariance() {
    }

    // BEGIN: variance.contravariance.v2
    public static boolean isPositive(Number aNumber) {
        return aNumber.doubleValue() > 0.0d;
    }
    // END: variance.contravariance.v2
}

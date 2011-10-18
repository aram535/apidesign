package api;

import java.util.Set;

public class Erasure {
    private Erasure() {
    }

    // BEGIN: variance.erasure.v2
    public static boolean arePositive(Set<? extends Number> numbers) {
        for (Number n : numbers) {
            if (n.doubleValue() <= 0.0d) {
                return false;
            }
        }
        return true;
    }
    // END: variance.erasure.v2
}

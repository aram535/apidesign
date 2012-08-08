package api;

import java.util.Collection;

public class Erasure {
    private Erasure() {
    }

    // BEGIN: variance.erasure.v1
    public static boolean arePositive(Collection<? extends Integer> numbers) {
        for (Integer n : numbers) {
            if (n <= 0) {
                return false;
            }
        }
        return true;
    }
    // END: variance.erasure.v1
}

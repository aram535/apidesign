package api;

import java.util.Set;

public class Erasure {
    private Erasure() {
    }

    // BEGIN: variance.erasure.v1
    public static boolean arePositive(Set<? extends Integer> numbers) {
        for (Integer n : numbers) {
            if (n <= 0) {
                return false;
            }
        }
        return true;
    }
    // END: variance.erasure.v1
}

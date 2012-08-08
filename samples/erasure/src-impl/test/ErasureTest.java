package test;

import static api.Erasure.arePositive;
import java.util.Arrays;
import java.util.List;

public class ErasureTest {
    public static void main(String[] args) {
        // BEGIN: variance.erasure.test
        List<Integer> oneToTen = Arrays.asList(2, 4, 6, 8, 10);
        boolean positive = arePositive(oneToTen);
        System.err.println("positive = " + positive);
        assert positive : "All the numbers are positive: " + oneToTen;
        // END: variance.erasure.test
    }
}

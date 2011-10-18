package test;

import api.Erasure;
import java.util.Set;
import java.util.TreeSet;

public class ErasureTest {
    // BEGIN: variance.erasure.test
    public static void main(String[] args) {
        Set<Integer> oneToTen = new TreeSet<Integer>();
        for (int i = 1; i <= 10; i++) {
            oneToTen.add(i);
        }
        
        boolean positive = Erasure.arePositive(oneToTen);
        System.err.println("positive = " + positive);
        assert positive : "All the numbers are positive: " + oneToTen;
    }
    // END: variance.erasure.test
}

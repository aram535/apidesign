package test;

import api.Contravariance;

public class ContravarianceTest {
    // BEGIN: variance.contravariance.test
    public static void main(String[] args) {
        boolean positive = Contravariance.isPositive(10);
        boolean negative = Contravariance.isPositive(-5);
        assert positive : "10 is positive";
        assert !negative : "-5 is negative";
    }
    // END: variance.contravariance.test
}

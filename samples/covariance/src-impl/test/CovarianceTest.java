package test;

import api.Covariance;

public class CovarianceTest {
    // BEGIN: variance.covariance.test
    public static void main(String[] args) {
        Number n = Covariance.max(10, 20);
        System.err.println("value: " + n + " type: " + n.getClass());
        assert n.intValue() == 20 : "The max should be 20, but was: " + n;
    }
    // END: variance.covariance.test
}

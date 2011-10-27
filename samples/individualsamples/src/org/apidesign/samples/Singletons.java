package org.apidesign.samples;

/** Showing some horrible usages of singletons.
 */
public class Singletons {
    static {
        // BEGIN: singleton.no.no
        /** initialize singletons */
        Database.init();
        OfflineQueue.init();
        CreditCardProcessor.init();
        /** use singletons */
        CreditCard c =  new CreditCard(
          "1234 5678 9012 3456", 5, 2008
        );
        c.charge(100);
        // END: singleton.no.no
    }
    
    static final class Database {
        public static void init() {
        }
    }

    static final class OfflineQueue {
        public static void init() {
        }
    }
    
    static final class CreditCardProcessor {
        public static void init() {
        }
    }

    private static class CreditCard {

        public CreditCard(String card, int month, int year) {
        }

        private void charge(int amount) {
        }
    }
}

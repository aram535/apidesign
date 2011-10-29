package org.apidesign.samples;

/** Sample code for http://wiki.apidesign.org/wiki/SuperVsInner essay */
public class SuperInner {
    // BEGIN: superinner.java
    public abstract class JavaLikeExample {
      public final int callable() {
        return 1 + theSlot();
      }

      protected abstract int theSlot();
    }
    // END: superinner.java

    /*
    // BEGIN: superinner.beta
    public abstract class BetaLikeExample {
      pubment int callable() {
        int res = inner.callable();
        return res + 1;
      }
    }
    // END: superinner.beta
     */
    
}

package org.apidesign.stateful.api;

import junit.framework.TestCase;

public class ProgressStatefulTest extends TestCase {
    
    public ProgressStatefulTest(String testName) {
        super(testName);
    }

    public void testProgressWithoutStart() {
        try {
            // BEGIN: progress.wrong.order
            ProgressStateful p = ProgressStateful.create("WrongOrder");
            p.progress(10);
            p.finish();
            // END: progress.wrong.order
            
            fail("Calling progress without start yields an exception!?");
        } catch (IllegalStateException ex) {
            // OK
        }
    }

}

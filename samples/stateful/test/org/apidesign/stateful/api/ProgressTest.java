package org.apidesign.stateful.api;

import junit.framework.TestCase;

public class ProgressTest extends TestCase {
    
    public ProgressTest(String testName) {
        super(testName);
    }

    public void testProgressWithoutStart() {
        try {
            // BEGIN: progress.wrong.order
            Progress p = Progress.create("WrongOrder");
            p.progress(10);
            p.finish();
            // END: progress.wrong.order
            
            fail("Calling progress without start yields an exception!?");
        } catch (IllegalStateException ex) {
            // OK
        }
    }

}

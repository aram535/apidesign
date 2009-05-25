package api;

import org.apidesign.privileged.api.Mutex;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class MutexTest {
    // BEGIN: mutex.init
    private static final Mutex.Privileged PRIVILEGED = new Mutex.Privileged();
    public static final Mutex MUTEX = new Mutex(PRIVILEGED);
    // END: mutex.init

    public MutexTest() {
    }

    /**
     * Test of readAccess method, of class Mutex.
     */
    @Test
    public void readAccess() {
        // BEGIN: mutex.use
        class R implements Runnable {
            int cnt;
            
            public void run() {
                cnt++;
            }
        }
        R r = new R();
        MUTEX.readAccess(r);
        assertEquals("Counter increased", 1, r.cnt);
        // END: mutex.use
    }
    
    @Test
    public void usePrivileged() {
        int cnt = 0;
        // BEGIN: mutex.privileged
        PRIVILEGED.enterReadAccess();
        try {
          // do the operation
            cnt++;
        } finally {
           PRIVILEGED.exitReadAccess();
        }
        assertEquals("Counter increased", 1, cnt);
        // END: mutex.privileged
        
    }

}

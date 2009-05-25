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

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of readAccess method, of class Mutex.
     */
    @Test
    public void readAccess() {
        Mutex instance = new Mutex();

        // BEGIN: mutex.use
        class R implements Runnable {
            int cnt;
            
            public void run() {
                cnt++;
            }
        }
        R r = new R();
        instance.readAccess(r);
        assertEquals("One call to runnable", 1, r.cnt);
        // END: mutex.use
    }
    
    @Test
    public void usePrivileged() {
        Mutex.Privileged lock = new Mutex.Privileged();
        Mutex mutex = new Mutex(lock);

        // BEGIN: mutex.privileged
        lock.enterReadAccess();
        try {
          // do the operation
        } finally {
           lock.exitReadAccess();
        }
        // END: mutex.privileged
        
    }

}
package org.apidesign.deadlock.logs;

import java.util.logging.Level;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.netbeans.junit.NbTestCase;
import static org.junit.Assert.*;

// BEGIN: test.parallel.test
public class ParallelTest extends NbTestCase {
    public ParallelTest(String testName) {
        super(testName);
    }

    @Override
    protected Level logLevel() {
        return Level.WARNING;
    }

    public void testMain() throws Exception {
        Parael.main(null);
        fail("Ok, just print logged messages");
    }
}
// END: test.parallel.test

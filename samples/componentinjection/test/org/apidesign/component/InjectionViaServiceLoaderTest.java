package org.apidesign.component;

import java.util.Iterator;
import org.junit.BeforeClass;
import org.junit.Test;
import org.netbeans.junit.MockServices;
import static org.junit.Assert.*;

// BEGIN: lookup.mockservices.serviceloader
public class InjectionViaServiceLoaderTest {
    public InjectionViaServiceLoaderTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        // this is a support for "dynamic" registration of any class
        // into the global pool of Lookup.getDefault() and 
        // java.util.ServiceLoader
        // either in @BeforeClass or @Before register 
        // classes of the mock instances that you want your code 
        // to find
        MockServices.setServices(ImplOne.class, ImplTwo.class);
    }

    @Test
    public void singleSlot() {
        InjectionSlot result = InjectionViaServiceLoader.singleSlot();
        assertNotNull("Some result found", result);
        assertEquals(
            "The first is ImplOne", ImplOne.class, result.getClass()
        );
    }

    @Test
    public void multiSlot() {
        Iterator<? extends InjectionSlot> it = 
            InjectionViaServiceLoader.multiSlot().iterator();
        assertTrue("Has at least one", it.hasNext());
        assertEquals(
            "The first is ImplOne", ImplOne.class, it.next().getClass()
        );
        assertTrue("Has two", it.hasNext());
        assertEquals(
            "The second is ImplTwo", ImplTwo.class, it.next().getClass()
        );
        assertFalse("No other instance registered", it.hasNext());
    }

    public static final class ImplOne extends InjectionSlot {
    }
    public static final class ImplTwo extends InjectionSlot {
    }
}
// END: lookup.mockservices.serviceloader

package org.apidesign.component;

import java.util.Iterator;
import org.junit.BeforeClass;
import org.junit.Test;
import org.netbeans.junit.MockServices;
import org.openide.util.Lookup;
import static org.junit.Assert.*;
import org.openide.util.Lookup.Result;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;

public class InjectionSlotChangesTest {
    public InjectionSlotChangesTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        // this is a support for "dynamic" registration of any class
        // into the global pool of Lookup.getDefault() and java.util.ServiceLoader
        // either in @BeforeClass or @Before register 
        // classes of the mock instances that you want your code 
        // to find
        MockServices.setServices(ImplOne.class, ImplTwo.class);
    }

    @Test
    public void multiSlot() {
        Iterator<? extends InjectionSlot> it = InjectionSlot.multiSlot().iterator();
        assertTrue("Has at least one", it.hasNext());
        assertEquals("The first is ImplOne", ImplOne.class, it.next().getClass());
        assertTrue("Has two", it.hasNext());
        assertEquals("The second is ImplTwo", ImplTwo.class, it.next().getClass());
        assertFalse("No other instance registered", it.hasNext());

        // BEGIN: lookup.listener
        class Listener implements LookupListener {
            int cnt;
            public void resultChanged(LookupEvent arg0) {
                cnt++;
            }
        }
        Listener listener = new Listener();
        Lookup.Result<InjectionSlot> res = 
            Lookup.getDefault().lookupResult(InjectionSlot.class);
        res.addLookupListener(listener);
        assertEquals("Two services now", 2, res.allInstances().size());
        
        MockServices.setServices(ImplTwo.class);
        
        assertEquals("One service only", 1, res.allInstances().size());
        assertEquals("One change in listener", 1, listener.cnt);
        assertEquals(
            "The second is ImplTwo", 
            ImplTwo.class, 
            res.allInstances().iterator().next().getClass()
        );
        // END: lookup.listener
    }

    public static final class ImplOne extends InjectionSlot {
    }
    public static final class ImplTwo extends InjectionSlot {
    }
}

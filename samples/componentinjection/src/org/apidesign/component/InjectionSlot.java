package org.apidesign.component;

import java.util.Collection;
import org.openide.util.Lookup;

// BEGIN: lookup.define.slot
public class InjectionSlot {
    public static InjectionSlot singleSlot() {
        // get one implementation
        return Lookup.getDefault().lookup(InjectionSlot.class);
    }
    
    public static Collection<? extends InjectionSlot> multiSlot() {
        // get all registered implementations
        return Lookup.getDefault().lookupAll(InjectionSlot.class);
    }
}
// END: lookup.define.slot

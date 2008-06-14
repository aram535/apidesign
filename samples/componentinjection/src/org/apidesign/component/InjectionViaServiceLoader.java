package org.apidesign.component;

import java.util.Collection;
import java.util.ServiceLoader;
import org.openide.util.Lookup;

public class InjectionViaServiceLoader {
    private InjectionViaServiceLoader() {
    }
    
    // BEGIN: lookup.query.serviceloader
    public static InjectionSlot singleSlot() {
        // get one implementation
        return ServiceLoader.load(InjectionSlot.class).iterator().next();
    }
    
    public static Iterable<? extends InjectionSlot> multiSlot() {
        // get all registered implementations
        return ServiceLoader.load(InjectionSlot.class);
    }
    // END: lookup.query.serviceloader
}

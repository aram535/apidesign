package org.apidesign.component;

import java.util.Collection;
import org.openide.util.Lookup;

public class DefineInjectionSlot {
    private DefineInjectionSlot() {
    }
    
    // BEGIN: lookup.define.slot
    public static <T> T singleSlot(Class<T> clazz) {
        // get one implementation
        T instance = Lookup.getDefault().lookup(clazz);
        return instance;
    }
    
    public static <T> Collection<? extends T> multiSlot(Class<T> clazz) {
        // get all registered implementations
        Collection<? extends T> all = Lookup.getDefault().lookupAll(clazz);
        return all;
    }
    // END: lookup.define.slot
}

package api;

import java.util.Collection;
import java.util.Set;

/** Simplified version of NetBeans 
 * <a href="http://bits.netbeans.org/6.0/javadoc/org-openide-util/org/openide/util/Lookup.html">Lookup</a>
 * reimplemented to separate the API for clients
 * from the API for implementators while guaranteeing
 * consistency among all there methods.
 *
 * @author Jaroslav Tulach <jtulach@netbeans.org>
 * @version 2.0
 */
// BEGIN: design.consistency.2.0
public final class Lookup {
    Lookup() {
    }
    
    // BEGIN: design.consistency.lookup.2.0
    public <T> T lookup(Class<T> clazz) {
        return null;
    }
    // END: design.consistency.lookup.2.0

    // BEGIN: design.consistency.lookupAll.2.0
    public <T> Collection<? extends T> lookupAll(Class<T> clazz) {
        return null;
    }
    // END: design.consistency.lookupAll.2.0

    // BEGIN: design.consistency.lookupAllClasses.2.0
    public <T> Set<Class<? extends T>> lookupAllClasses(Class<T> clazz) {
        return null;
    }
    // END: design.consistency.lookupAllClasses.2.0
}
// END: design.consistency.2.0
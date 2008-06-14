package api;

import java.util.Collection;
import java.util.Set;

/** Factory to create various types of lookup instances.
 *
 * @author Jaroslav Tulach <jtulach@netbeans.org>
 * @version 2.0
 */
public final class Lookups {
    private Lookups() {
    }
    
    public static Lookup fixed(Object... instances) {
        return null;
    }
    
    public static Lookup dynamic(Dynamic provider) {
        return null;
    }
    
    public interface Dynamic {
        public <T> void computeInstances(Class<T> requestedType, Collection<? super T> addInstancesTo);
    }
}

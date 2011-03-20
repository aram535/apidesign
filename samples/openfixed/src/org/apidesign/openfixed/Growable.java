package org.apidesign.openfixed;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/** Sample bean using the {@link GrowingListener}.
 *
 * @author Jaroslav Tulach <jtulach@netbeans.org>
 */
public final class Growable {
    private List<GrowingListener> listeners = new CopyOnWriteArrayList<GrowingListener>();
    
    public void addGrowingListener(GrowingListener l) {
        listeners.add(l);
    }
    public void removeGrowingListener(GrowingListener l) {
        listeners.remove(l);
    }
}

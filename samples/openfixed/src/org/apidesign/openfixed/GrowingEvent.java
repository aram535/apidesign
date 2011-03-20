package org.apidesign.openfixed;

import java.util.EventObject;

// BEGIN: openfixed.event
public final class GrowingEvent extends EventObject {
    public GrowingEvent(Object source) {
        super(source);
    }
// FINISH: openfixed.event
    
// BEGIN: openfixed.addgetter    
    private int index;
    /** @since 2.0 */
    public GrowingEvent(Object source, int index) {
        super(source);
        this.index = index;
    }

    /** @since 2.0 */
    public int getIndex() {
        return index;
    }
// END: openfixed.addgetter    
}

package org.apidesign.openfixed;

import java.util.EventListener;

// BEGIN: openfixed.listener
public interface GrowingListener extends EventListener {
    public void response(GrowingEvent ev);
}
// FINISH: openfixed.listener

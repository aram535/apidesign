package org.apidesign.javamonitorflaws;

import org.apidesign.javamonitorflaws.Cache;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 *
 * @author Jaroslav Tulach <jtulach@netbeans.org>
 */
// BEGIN: monitor.pitfalls.subclass
public class MultiplyCache extends Cache<String,Integer> {
    private PropertyChangeSupport pcs;
    private int multiply;
    public static final String PROP_MULTIPLY = "multiply";

    public synchronized int getMultiply() {
        return multiply;
    }
    public synchronized void setMultiply(int multiply) {
        int oldMultiply = this.multiply;
        this.multiply = multiply;
        pcs.firePropertyChange(PROP_MULTIPLY, oldMultiply, multiply);
    }

    public synchronized void addPropertyChangeListener(
        PropertyChangeListener listener
    ) {
        if (pcs == null) {
            pcs = new PropertyChangeSupport(this);
        }
        pcs.addPropertyChangeListener(listener);
    }
    public synchronized void removePropertyChangeListener(
        PropertyChangeListener listener
    ) {
        if (pcs != null) {
            pcs.removePropertyChangeListener(listener);
        }
    }

    @Override
    protected Integer createItem(String f) {
        return f.length() * getMultiply();
    }
}
// END: monitor.pitfalls.subclass



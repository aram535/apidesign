/*
 *                 Sun Public License Notice
 * 
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 * 
 * The Original Code is NetBeans. The Initial Developer of the Original
 * Code is Sun Microsystems, Inc. Portions Copyright 1999-2006 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

package apipkg;

import implpkg.Accessor;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/** Class in API that everyone can use.
 *
 * @author Jaroslav Tulach
 */
public final class Item {
    private int value;
    private ChangeListener listener;
    
    static {
        Accessor.DEFAULT = new AccessorImpl();
    }
    
    /** Contructor for friends */
    Item() {
    }
    
    /** Anyone can value of the item. At least if it can get a reference to it.
     */
    public void setValue(int x) {
        value = x;
        ChangeListener l = listener;
        if (l != null) {
            l.stateChanged(new ChangeEvent(this));
        }
    }
    
    /** Anyone can get the value of the item. 
     */
    public int getValue() {
        return value;
    }
    
    /** Only the impl package can listen.
     */
    void addChangeListener(ChangeListener l) {
        assert listener == null;
        listener = l;
    }
    
}

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

package implpkg;

import apipkg.Item;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Jaroslav Tulach
 */
// BEGIN: design.less.friend.Accessor
public abstract class Accessor {
    public static Accessor DEFAULT;
    static {
        try {
            Class.forName(Item.class.getName(), true, Item.class.getClassLoader());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public Accessor() {
    }

    protected abstract Item newItem();
    protected abstract void addChangeListener(Item item, ChangeListener l);
}
// END: design.less.friend.Accessor

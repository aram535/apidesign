/*
 *                 Sun Public License Notice
 * 
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 * 
 * The Original Code is NetBeans. The Initial Developer of the Original
 * Code is Jaroslav Tulach. Portions Copyright 2007 Jaroslav Tulach. 
 * All Rights Reserved.
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
    private static volatile Accessor DEFAULT;
    public static Accessor getDefault() {
        Accessor a = DEFAULT;
        if (a == null) {
            throw new IllegalStateException("Something is wrong: " + a);
        }
        return a;
    }

    public static void setDefault(Accessor accessor) {
        if (DEFAULT != null) {
            throw new IllegalStateException();
        }
        DEFAULT = accessor;
    }
    
    public Accessor() {
    }

    protected abstract Item newItem();
    protected abstract void addChangeListener(Item item, ChangeListener l);
// FINISH: design.less.friend.Accessor

    // BEGIN: design.less.friend.InitAPI
    private static final Class<?> INIT_API_CLASS = loadClass(Item.class.getName());
    private static Class<?> loadClass(String name) {
        try {
            return Class.forName(
                name, true, Accessor.class.getClassLoader()
            );
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    // END: design.less.friend.InitAPI
}

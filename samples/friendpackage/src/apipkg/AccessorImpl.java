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

/** The bridge between api and impl package.
 *
 * @author Jaroslav Tulach
 */
// BEGIN: design.less.friend.AccessorImpl
final class AccessorImpl extends Accessor {
    protected Item newItem() {
        return new Item();
    }

    protected void addChangeListener(Item item, ChangeListener l) {
        item.addChangeListener(l);
    }
}
// END: design.less.friend.AccessorImpl

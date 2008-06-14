package apipkg;

import implpkg.Accessor;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/** Class in API that everyone can use.
 *
 * @author Jaroslav Tulach
 */
// BEGIN: design.less.friend.Item
public final class Item {
    private int value;
    private ChangeListener listener;

    // BEGIN: design.less.friend.Item.static
    static {
        Accessor.setDefault(new AccessorImpl());
    }
    // END: design.less.friend.Item.static
    
    /** Only friends can create instances. */
    Item() {
    }
    
    /** Anyone can change value of the item. 
     */
    public void setValue(int newValue) {
        value = newValue;
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
    
    /** Only friends can listen to changes.
     */
    void addChangeListener(ChangeListener l) {
        assert listener == null;
        listener = l;
    }
    
}
// END: design.less.friend.Item

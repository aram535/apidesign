package apipkg;

import implpkg.Accessor;
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

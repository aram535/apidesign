package org.apidesign.exceptions.trycatchredo;

import java.net.URL;
import javax.swing.Action;

/**
 *
 * @author Jaroslav Tulach <jtulach@netbeans.org>
 */
public final class IOManager {
    IOManager() {
    }

    public static Action createSaveAction(URL where, CharSequence what) {
        return new SaveActionWithQuery(where, what);
    }
}

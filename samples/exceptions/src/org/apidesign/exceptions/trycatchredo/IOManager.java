package org.apidesign.exceptions.trycatchredo;

import java.net.URL;
import javax.swing.Action;

/**
 *
 * @author Jaroslav Tulach <jtulach@netbeans.org>
 */
public final class IOManager {
    static boolean old;

    IOManager() {
    }

    /** Action that can store a text to given URL.
     *
     * @param where the url to upload the text to
     * @param what the text to upload
     * @return action that can be invoked anytime to save the content
     */
    public static Action createSaveAction(URL where, CharSequence what) {
        if (old) {
            return new SaveAction(where, what);
        } else {
            return new SaveActionWithQuery(where, what);
        }
    }
}

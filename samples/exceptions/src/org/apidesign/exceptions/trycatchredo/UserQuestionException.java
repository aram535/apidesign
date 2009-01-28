package org.apidesign.exceptions.trycatchredo;

import java.io.IOException;
import javax.swing.JComponent;

/**
 *
 * @author Jaroslav Tulach
 */
public abstract class UserQuestionException extends IOException {
    public abstract JComponent getQuestionPane();
    public abstract void confirm();
}

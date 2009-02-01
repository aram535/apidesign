package org.apidesign.exceptions.trycatchredo;

import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author Jaroslav Tulach
 */
public abstract class UserQuestionException extends IOException {
    public abstract JOptionPane getQuestionPane();
    public abstract void confirm(Object option);
}

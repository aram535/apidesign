package org.apidesign.exceptions.trycatchredo;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import javax.swing.Action;
import javax.swing.JOptionPane;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jaroslav Tulach <jtulach@netbeans.org>
 */
public class IOManagerTest {

    public IOManagerTest() {
    }

    @Before
    public void setUp() {
        MemoryURL.initialize();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void simpleWrite() throws Exception {
        URL u = new URL("memory://simpleWrite.txt");
        MemoryURL.registerURL(u.toExternalForm(), "", null);
        final Action a = IOManager.createSaveAction(u, "Hello World!");
        EventQueue.invokeAndWait(new Runnable() {
            public void run() {
                a.actionPerformed(new ActionEvent(this, 0, ""));
            }
        });
        String out = MemoryURL.getOutputForURL(u.toExternalForm());
        assertEquals("Hello World!", out);
    }

    @Test
    public void writeWithAQuestion() throws Exception {
        URL u = new URL("memory://queryEncoding.txt");

        MemoryURL.registerURL(u.toExternalForm(), "", new QueryStream());
        final Action a = IOManager.createSaveAction(u, "Ask a Question");
        EventQueue.invokeAndWait(new Runnable() {
            public void run() {
                a.actionPerformed(new ActionEvent(this, 0, ""));
            }
        });
        String out = MemoryURL.getOutputForURL(u.toExternalForm());
        assertEquals("Text is reversed", "noitseuQ a ksA", out);
    }

    private static class QueryStream extends OutputStream {
        Boolean reverse;
        ByteArrayOutputStream arr = new ByteArrayOutputStream();

        @Override
        public synchronized void write(byte[] b, int off, int len) throws IOException {
            if (reverse == null) {
                throw new QueryException();
            }
            arr.write(b, off, len);
        }

        @Override
        public synchronized void write(int b) throws IOException {
            if (reverse == null) {
                throw new QueryException();
            }
            arr.write(b);
        }

        @Override
        public String toString() {
            if (reverse == null) {
                return "Reverse question was not answered yet!";
            }
            if (reverse) {
                StringBuilder sb = new StringBuilder();
                sb.append(arr.toString());
                sb.reverse();
                return sb.toString();
            }
            return arr.toString();
        }

        private class QueryException extends UserQuestionException {
            @Override
            public JOptionPane getQuestionPane() {
                JOptionPane p = new JOptionPane("Store in reverse way?");
                p.setOptionType(JOptionPane.YES_NO_CANCEL_OPTION);
                return p;
            }

            @Override
            public void confirm(Object option) {
                if (option.equals(JOptionPane.YES_OPTION)) {
                    reverse = Boolean.TRUE;
                    return;
                }
                if (option.equals(JOptionPane.NO_OPTION)) {
                    reverse = Boolean.FALSE;
                    return;
                }
            }
        } // end of QueryException
    } // end of QueryStream


}
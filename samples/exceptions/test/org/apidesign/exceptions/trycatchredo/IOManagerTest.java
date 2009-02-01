package org.apidesign.exceptions.trycatchredo;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.net.URL;
import javax.swing.Action;
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
        final Action a = IOManager.createSaveAction(u, "Ahoj");
        EventQueue.invokeAndWait(new Runnable() {
            public void run() {
                a.actionPerformed(new ActionEvent(this, 0, ""));
            }
        });
        byte[] out = MemoryURL.getOutputForURL(u.toExternalForm());
        assertEquals("Four bytes", 4, out.length);
    }
}
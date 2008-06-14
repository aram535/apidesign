
package org.apidesign.delegatingwriter;

import java.io.IOException;
import java.io.StringWriter;
import java.io.StringWriter;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/** Emulates what goes wrong when delegating directly
 *
 * @author Jaroslav Tulach <jaroslav.tulach@apidesign.org>
 */
public class BufferedWriterCryptoTest {
    private StringWriter writer;
    
    
    public BufferedWriterCryptoTest() {
    }
    
    @Before
    public void setUp() {
        writer = new StringWriter();
    }

    @Test
    public void testBehaviourOfRealBufferInJDKWorksFine() throws IOException {
        // BEGIN: writer.crypto.ok
        CryptoWriter bufferedWriter = new CryptoWriter(writer);
        bufferedWriter.append("VMS");
        bufferedWriter.flush();
        assertEquals("Converted", "WNT", writer.toString());
        // END: writer.crypto.ok
    }

    @Test
    public void testBehaviourOfBufferThatDelegatesToAppendFails() throws IOException {
        CryptoWriter bufferedWriter = new CryptoWriter(writer, CryptoWriter.Behaviour.DELEGATE_TO_OUT);
        bufferedWriter.append("VMS");
        bufferedWriter.flush();
        assertEquals("This will fail, as the direct delegation from append to " +
            "the underlaying writer will skip all the crypto methods", 
            "WNT", writer.toString()
        );
    }

    @Test
    public void testBehaviourWhenDelegatingConditionallyIsOK() throws IOException {
        CryptoWriter bufferedWriter = new CryptoWriter(writer, AltBufferedWriter.Behaviour.DELEGATE_CONDITIONALLY);
        bufferedWriter.append("VMS");
        bufferedWriter.flush();
        assertEquals("Converted", "WNT", writer.toString());
    }
    
}
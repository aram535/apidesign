/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package api;

import api.Digest;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Random;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/** Compares that the MessageDigest and Digest yield the same results for
 * default provider.
 *
 * @author Jaroslav Tulach <jaroslav.tulach@apidesign.org>
 */
public class DigestTest {
    private static byte[] arr;
    private static long time;
    private static byte[] resOld;
    private static byte[] resNew;

    public DigestTest() {
    }

    @BeforeClass
    public static void setUp() {
        time = System.currentTimeMillis();
        Random r = new Random(time);
        arr = new byte[r.nextInt(1024 * 1024)];
        r.nextBytes(arr);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void generateHashUsingMessageDigest() throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] res = md.digest(arr);
        resOld = res;
    }

    @Test
    public void generateHashUsingNewDigest() throws Exception {
        Digest d = Digest.getInstance("MD5");
        ByteBuffer bb = ByteBuffer.wrap(arr);
        byte[] res = d.digest(bb);
        resNew = res;
    }
    
    @Test
    public void compareTheHashes() throws Exception {
        if (!Arrays.equals(resOld, resNew)) {
            fail("Arrays are different:\n" + Arrays.toString(resOld) + "\n" + Arrays.toString(resNew));
        }
    }
}
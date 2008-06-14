
package api;

import java.nio.ByteBuffer;
import spi.DigestImplementation;

/** MessageDigest extends MessageDigestSpi, that means the javadoc
 *
 * @author Jaroslav Tulach
 */
public final class Digest {
    private final DigestImplementation impl;

    /** Factory method is better than constructor */
    private Digest(DigestImplementation impl) {
        this.impl = impl;
    }
    
    /** Factory method to create digest for an algorithm.
     */
    public static Digest getInstance(String algorithm) {
        return null;
    }
      
    //
    // these methods are kept the same as in original MessageDigest,
    // but for simplicity choose just some from the original API
    //
    
    public byte[] digest(ByteBuffer bb) {
        return null;
    }
            
}

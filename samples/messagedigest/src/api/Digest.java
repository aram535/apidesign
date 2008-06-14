
package api;

import java.nio.ByteBuffer;
import impl.friendapi.DigestImplementation;
import impl.friendapi.DigestProvider;
import java.util.ServiceLoader;

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
        for (DigestProvider dp : ServiceLoader.load(DigestProvider.class)) {
            DigestImplementation impl = dp.create(algorithm);
            if (impl != null) {
                return new Digest(impl);
            }
        }
        throw new IllegalArgumentException(algorithm);
    }
      
    //
    // these methods are kept the same as in original MessageDigest,
    // but for simplicity choose just some from the original API
    //
    
    public byte[] digest(ByteBuffer bb) {
        impl.update(bb);
        return impl.digest();
    }
}

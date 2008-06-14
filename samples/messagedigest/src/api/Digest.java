
package api;

/** MessageDigest extends MessageDigestSpi, that means the javadoc
 *
 * @author Jaroslav Tulach
 */
public final class Digest {
    /** Factory method is better than constructor */
    private Digest() {
    }
    
    /** Factory method to create digest for an algorithm.
     */
    public static Digest getInstance(String algorithm) {
        return null;
    }
      
    
            
}

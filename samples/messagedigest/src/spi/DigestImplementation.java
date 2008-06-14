/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package spi;

import java.nio.ByteBuffer;

/**
 *
 * @author Jaroslav Tulach
 */
public abstract class DigestImplementation {
    final String name;
    
    protected DigestImplementation(String algorithm) {
        this.name = algorithm;
    }
    
    protected abstract void update(ByteBuffer bb);
    protected abstract byte[] digest();
}

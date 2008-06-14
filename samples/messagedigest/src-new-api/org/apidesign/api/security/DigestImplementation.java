/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.apidesign.api.security;

import java.nio.ByteBuffer;
import org.apidesign.spi.security.Digestor;

/**
 *
 * @author Jaroslav Tulach <jaroslav.tulach@apidesign.org>
 */
final class DigestImplementation<Data> {
    private static final DigestorAccessorImpl ACCESSOR = new DigestorAccessorImpl();
    
    private final Digestor<Data> digestor;
    private final String algorithm;
    private Data data;
    
    private DigestImplementation(Digestor<Data> digestor, String algorithm, Data d) {
        this.digestor = digestor;
        this.algorithm = algorithm;
        this.data = d;
    }
    
    static <Data> DigestImplementation create(Digestor<Data> digestor, String algorithm) {
        Data d = ACCESSOR.create(digestor, algorithm);
        if (d == null) {
            return null;
        } else {
            return new DigestImplementation(digestor, algorithm, d);
        }
    }

    byte[] digest(ByteBuffer bb) {
        ACCESSOR.update(digestor, data, bb);
        return ACCESSOR.digest(digestor, data);
    }
}

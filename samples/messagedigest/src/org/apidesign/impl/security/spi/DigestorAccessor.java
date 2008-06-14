/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.apidesign.impl.security.spi;

import java.nio.ByteBuffer;
import org.apidesign.spi.security.Digestor;

/**
 *
 * @author jarda
 */
public abstract class DigestorAccessor {
    private static DigestorAccessor INSTANCE;
    
    protected DigestorAccessor() {
        assert INSTANCE == null;
        INSTANCE = this;
    }
    
    public static DigestorAccessor getDefault() {
        try {
            Class.forName(Digestor.class.getName(), true, DigestorAccessor.class.getClassLoader());
            return INSTANCE;
        } catch (ClassNotFoundException ex) {
            throw new IllegalStateException(ex);
        }
    }
    
    protected abstract <Data> byte[] digest(Digestor<Data> dig, Data data);
    protected abstract <Data> Data create(Digestor<Data> dig, String algorithm); 
    protected abstract <Data> void update(Digestor<Data> dig, Data data, ByteBuffer input);
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.apidesign.spi.security;

import org.apidesign.impl.security.spi.DigestorAccessor;
import java.nio.ByteBuffer;

/**
 *
 * @author  Jaroslav Tulach <jaroslav.tulach@apidesign.org>
 */
// BEGIN: day.end.bridges.Digestor
public abstract class Digestor<Data> {
   protected abstract byte[] digest(Data data);
   protected abstract Data create(String algorithm); 
   protected abstract void update(Data data, ByteBuffer input);
   
// END: day.end.bridges.Digestor   
   static {
       new DigestorAccessor() {
            @Override
            protected <Data> byte[] digest(Digestor<Data> dig, Data data) {
                return dig.digest(data);
            }

            @Override
            protected <Data> Data create(Digestor<Data> dig, String algorithm) {
                return dig.create(algorithm);
            }

            @Override
            protected <Data> void update(Digestor<Data> dig, Data data, ByteBuffer input) {
                dig.update(data, input);
            }
        };
   }
}

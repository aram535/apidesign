/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package spi;

import impl.spi.DigestorAccessor;
import java.nio.ByteBuffer;

/**
 *
 * @author  Jaroslav Tulach <jaroslav.tulach@apidesign.org>
 */
public abstract class Digestor<Data> {
   protected abstract byte[] digest(Data data);
   protected abstract Data create(String algorithm); 
   protected abstract void update(Data data, ByteBuffer input);
   
   
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

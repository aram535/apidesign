/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package impl.spi;

import impl.friendapi.DigestImplementation;
import impl.friendapi.DigestProvider;
import java.nio.ByteBuffer;
import java.util.ServiceLoader;
import spi.Digestor;

/**
 *
 * @author Jaroslav Tulach
 */
public class DigestorProvider implements DigestProvider {

    public DigestImplementation create(String algorithm) {
        for (Digestor<?> d : ServiceLoader.load(Digestor.class)) {
            Handler<?> h = create(d, algorithm);
            if (h != null) {
                return h;
            }
        }
        return null;
    }
    
    private static <Data> Handler<Data> create(Digestor<Data> dig, String algorithm) {
        Data d = DigestorAccessor.getDefault().create(dig, algorithm);
        if (d == null) {
            return null;
        } else {
            return new Handler<Data>(algorithm, dig, d);
        }
    }

    private static final class Handler<Data> extends DigestImplementation {
        private final Digestor dig;
        private final Data data;

        public Handler(String algorithm, Digestor dig, Data data) {
            super(algorithm);
            this.dig = dig;
            this.data = data;
        }
        
        
        @Override
        public void update(ByteBuffer bb) {
            DigestorAccessor.getDefault().update(dig, data, bb);
        }

        @Override
        public byte[] digest() {
            return DigestorAccessor.getDefault().digest(dig, data);
        }
        
    }
}

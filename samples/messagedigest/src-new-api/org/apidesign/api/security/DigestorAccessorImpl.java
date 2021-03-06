package org.apidesign.api.security;

import org.apidesign.spi.security.*;
import java.nio.ByteBuffer;
import org.apidesign.impl.DigestorAccessor;

/**
 *
 * @author  Jaroslav Tulach <jaroslav.tulach@apidesign.org>
 */
final class DigestorAccessorImpl extends DigestorAccessor {
    @Override
    public <Data> byte[] digest(Digestor<Data> dig, Data data) {
        return defaultDigest(dig, data);
    }

    @Override
    public <Data> Data create(Digestor<Data> dig, String algorithm) {
        return defaultCreate(dig, algorithm);
    }

    @Override
    public <Data> void update(Digestor<Data> dig, Data data, ByteBuffer input) {
        defaultUpdate(dig, data, input);
    }
}

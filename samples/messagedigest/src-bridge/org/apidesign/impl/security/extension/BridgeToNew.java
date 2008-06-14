/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.apidesign.impl.security.extension;

import org.apidesign.impl.security.friendapi.DigestImplementation;
import org.apidesign.impl.security.friendapi.DigestProvider;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jaroslav Tulach <jaroslav.tulach@apidesign.org>
 */
// BEGIN: day.end.bridges.BridgeToNew
public class BridgeToNew implements DigestProvider {

    public DigestImplementation create(String algorithm) {
        try {
            final MessageDigest md = MessageDigest.getInstance(algorithm);
            return new DigestImplementation(algorithm) {

                @Override
                public void update(ByteBuffer bb) {
                    md.update(bb);
                }

                @Override
                public byte[] digest() {
                    return md.digest();
                }
            };
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(BridgeToNew.class.getName()).log(Level.INFO, null, ex);
            return null;
        }
    }
}
// END: day.end.bridges.BridgeToNew

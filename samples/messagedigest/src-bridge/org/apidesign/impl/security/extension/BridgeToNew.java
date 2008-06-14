/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.apidesign.impl.security.extension;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apidesign.spi.security.Digestor;

/**
 *
 * @author Jaroslav Tulach <jaroslav.tulach@apidesign.org>
 */
// BEGIN: day.end.bridges.BridgeToNew
public class BridgeToNew extends Digestor<MessageDigest> {
    @Override
    protected MessageDigest create(String algorithm) {
        try {
            return MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(BridgeToNew.class.getName()).log(Level.FINE, "Cannot find " + algorithm, ex);
            return null;
        }
    }

    @Override
    protected byte[] digest(MessageDigest data) {
        return data.digest();
    }

    @Override
    protected void update(MessageDigest data, ByteBuffer input) {
        data.update(input);
    }
}
// END: day.end.bridges.BridgeToNew

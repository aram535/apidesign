/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package api;

import java.nio.ByteBuffer;
import spi.Digestor;

/**
 *
 * @author jarda
 */
public final class CountingDigestor extends Digestor<int[]> {

    @Override
    protected byte[] digest(int[] data) {
        int i = data[0];
        byte[] arr = { (byte) (i & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 24) & 255) };
        return arr;
    }

    @Override
    protected int[] create(String algorithm) {
        return "cnt".equals(algorithm) ? new int[1] : null; // NOI18N
    }

    @Override
    protected void update(int[] data, ByteBuffer input) {
        data[0] += input.remaining();
        input.position(input.position() + input.remaining());
    }

}

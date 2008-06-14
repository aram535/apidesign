package org.apidesign.cycles.array;

import java.io.IOException;
import java.io.OutputStream;
import org.openide.util.Lookup;
// BEGIN: nocycles.ma2
public class MutableArray {
    private byte[] arr;

    public MutableArray(byte[] arr) {
        this.arr = arr;
    }

    public void xor(byte b) {
        for (int i = 0; i < arr.length; i++) { arr[i] ^= b; }
    }

    public void and(byte b) {
        for (int i = 0; i < arr.length; i++) { arr[i] &= b; }
    }

    public void or(byte b) {
        for (int i = 0; i < arr.length; i++) { arr[i] |= b; }
    }

    public void encrypt(OutputStream os) throws IOException {
        DoEncode en = Lookup.getDefault().lookup(DoEncode.class);
        assert en != null : "We need org.netbeans.example.crypt to be enabled!";
        byte[] clone = (byte[]) arr.clone();
        en.encode(clone);
        os.write(clone);
    }
    
    public interface DoEncode {
        public void encode(byte[] arr);
    }
}
// END: nocycles.ma2

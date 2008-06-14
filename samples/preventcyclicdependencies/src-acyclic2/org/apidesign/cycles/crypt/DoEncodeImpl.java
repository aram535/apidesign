// BEGIN: nocycles.encrypt2
package org.apidesign.cycles.crypt;

import org.apidesign.cycles.array.MutableArray;

public class DoEncodeImpl implements MutableArray.DoEncode {
    public void encode(byte[] arr) {
        Encryptor en = new Encryptor();
        en.encode(arr);
    }
}
// END: nocycles.encrypt2

package api;

import java.io.IOException;

/** Fixing the problem caused by mixing subclassing and delegation in 
 * the <code>java.io.BufferedWriter</code>
 *
 * @author Jaroslav Tulach <jaroslav.tulach@apidesign.org>
 */
public final class Writer {
    private final Impl impl;
    
    private Writer(Impl impl) {
        this.impl = impl;
    }
    public final void write(int c) throws IOException {
        impl.write(new CharSeq(c));
    }
    
    public final void write(char cbuf[]) throws IOException {
	impl.write(new CharSeq(cbuf, 0, cbuf.length));
    }
    public final void write(char cbuf[], int off, int len) throws IOException {
        impl.write(new CharSeq(cbuf, off, len));
    }
    public final void write(String str) throws IOException {
	impl.write(str);
    }
    public final void write(String str, int off, int len) throws IOException {
        impl.write(str.subSequence(off, off + len));
    }

    
    
    public static interface Impl {
        public void write(CharSequence seq) throws IOException;
    }
    
    
    
}

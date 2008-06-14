package api;

import java.io.BufferedWriter;
import java.io.IOException;

/** Fixing the problem caused by mixing subclassing and delegation in 
 * the <code>java.io.BufferedWriter</code>
 *
 * @author Jaroslav Tulach <jaroslav.tulach@apidesign.org>
 */
public final class Writer {
    private final Impl impl;
    private final ImplSeq seq;
    
    private Writer(Impl impl, ImplSeq seq) {
        this.impl = impl;
        this.seq = seq;
    }
    public final void write(int c) throws IOException {
        if (impl != null) {
            char[] arr = {(char) c};
            impl.write(arr, 0, 1);
        } else {
            seq.write(new CharSeq(c));
        }
    }
    
    public final void write(char cbuf[]) throws IOException {
        if (impl != null) {
            impl.write(cbuf, 0, cbuf.length);
        } else {
            seq.write(new CharSeq(cbuf, 0, cbuf.length));
        }
    }
    public final void write(char cbuf[], int off, int len) throws IOException {
        if (impl != null) {
            impl.write(cbuf, off, len);
        } else {
            seq.write(new CharSeq(cbuf, off, len));
        }
    }
    public final void write(String str) throws IOException {
        if (impl != null) {
            impl.write(str, 0, str.length());
        } else {
            seq.write(str);
        }
    }
    public final void write(String str, int off, int len) throws IOException {
        if (impl != null) {
            impl.write(str, off, len);
        } else {
            seq.write(str.subSequence(off, off + len));
        }
    }

    public final void flush() throws IOException {
        if (impl != null) {
            impl.flush();
        } else {
            seq.flush();
        }
    }
    public final void close() throws IOException {
        if (impl != null) {
            impl.close();
        } else {
            seq.flush();
        }
    }

    public static Writer create(Impl impl) {
        return new Writer(impl, null);
    }

    public static Writer create(ImplSeq impl) {
        return new Writer(null, impl);
    }
    
    public static Writer create(final java.io.Writer w) {
        return new Writer(new Impl() {
            public void write(String str, int off, int len) throws IOException {
                w.write(str, off, len);
            }

            public void write(char[] arr, int off, int len) throws IOException {
                w.write(arr, off, len);
            }

            public void close() throws IOException {
                w.close();
            }

            public void flush() throws IOException {
                w.flush();
            }
        }, null);
    }
    
    public static Writer createBuffered(final Writer out) {
        class Delegate extends java.io.Writer {
            @Override
            public void write(char[] cbuf, int off, int len) throws IOException {
                out.write(cbuf, off, len);
            }

            @Override
            public void flush() throws IOException {
                out.flush();
            }

            @Override
            public void close() throws IOException {
                out.close();
            }
            
        }
        return create(new BufferedWriter(new Delegate()));
    }
    
    
    public static interface Impl {
        public void close() throws IOException;
        public void flush() throws IOException;
        public void write(String str, int off, int len) throws IOException;
        public void write(char[] arr, int off, int len) throws IOException;
    }
    public static interface ImplSeq {
        public void close() throws IOException;
        public void flush() throws IOException;
        public void write(CharSequence seq) throws IOException;
    }
    
    
    
}

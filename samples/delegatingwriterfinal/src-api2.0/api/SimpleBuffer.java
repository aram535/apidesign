package api;

import java.io.IOException;

/**
 *
 * @author Jaroslav Tulach
 */
final class SimpleBuffer implements Writer.ImplSeq {
    private final Writer out;
    private final StringBuffer sb = new StringBuffer();
    
    public SimpleBuffer(Writer out) {
        this.out = out;
    }

    public void close() throws IOException {
        flush();
        out.close();
    }

    public void flush() throws IOException {
        if (sb.length() > 0) {
            out.write(sb.toString());
            sb.setLength(0);
            out.flush();
        }
    }

    public void write(CharSequence seq) throws IOException {
        if (seq.length() < 1024) {
            sb.append(seq);
        } else {
            flush();
            out.append(seq);
        }
    }

}

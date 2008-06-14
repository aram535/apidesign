/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.apidesign.delegatingwriter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * This is a regular {@link BufferedWriter}, just it optimizes for
 * huge {@link CharSequence} objects set to its {@link #append} method.
 * Instead of converting them to string, which might required too much
 * temporary memory, it delegates it directly.
 * <p>
 * This class is <q>simulating</q> would would happen if JDK's BufferedWriter 
 * in revision 1.5
 * was designed to delegate its newly added <code>append</code> methods
 * instead of calling its already existing <code>write</code> ones.
 * 
 * @author Jaroslav Tulach <jaroslav.tulach@apidesign.org>
 */
public class ExBufferedWriter extends BufferedWriter {
    private final Writer out;
    
    public ExBufferedWriter(Writer out, int sz, boolean optimized) {
        super(out, sz);
        this.out = optimized ? out : null;
    }
    
    @Override
    public Writer append(CharSequence csq) throws IOException {
        if (out != null) {
            // efficient, yet dangerous delegation skipping methods known to 
            // subclasses that used version 1.4
            out.append(csq);
            return this;
        } else {
            // non-efficient variant of delegating via converting to String first 
            // and using one of methods that existed in 1.4
            if (csq == null) {
                write("null");
            } else {
                write(csq.toString());
            }
            return this;
        }
    }
}

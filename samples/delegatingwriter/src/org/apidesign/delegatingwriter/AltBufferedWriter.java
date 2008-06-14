/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.apidesign.delegatingwriter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * This is a regular {@link BufferedWriter}, just its implementation
 * of the append method can choose from three options. This allows us to
 * simulate the potential pros and cons of various possible implementations.
 * 
 * @author Jaroslav Tulach <jaroslav.tulach@apidesign.org>
 */
public class AltBufferedWriter extends BufferedWriter {
    private final Writer out;
    private final Behaviour behaviour;
    
    public AltBufferedWriter(Writer out) {
        // behave exactly like BufferedWriter in 1.5 behaves
        this(out, Behaviour.DELEGATE_TO_SUPER);
    }
    public AltBufferedWriter(Writer out, Behaviour behaviour) {
        super(out);
        this.out = out;
        this.behaviour = behaviour;
    }
    
    @Override
    public Writer append(CharSequence csq) throws IOException {
        switch (behaviour) {
            case THROW_EXCEPTION: return appendThrowException(csq); 
            case DELEGATE_TO_SUPER: return appendDelegateToSuper(csq);
            case DELEGATE_TO_OUT: return appendDelegateToUnderlaying(csq);
            case DELEGATE_CONDITIONALLY: return appendDelegateConditionally(csq);
            default: throw new IllegalStateException("Unknown" + behaviour);
        }
    }
    
    public Writer appendThrowException(CharSequence csq) throws IOException {
        /* in case of real code, this would be part of the regular append method BEGIN: writer.throw
    public Writer append(CharSequence csq) throws IOException {
        /* thrown an exception as this method is new and subclasses need to override it */
        throw new UnsupportedOperationException();
    }
    // END: writer.throw
    
    public Writer appendDelegateToSuper(CharSequence csq) throws IOException {
        // non-efficient variant of delegating via converting to String first 
        // and using one of methods that existed in 1.4
        // BEGIN: writer.throw.client
        if (csq == null) {
            write("null");
        } else {
            write(csq.toString());
        }
        return this;
        // END: writer.throw.client
    }
    
    public Writer appendDelegateToUnderlaying(CharSequence csq) throws IOException {
        // BEGIN: writer.delegateout
        // efficient, yet dangerous delegation skipping methods unknown to 
        // subclasses that used version 1.4
        out.append(csq);
        return this;
        // END: writer.delegateout
    }

    private Writer appendDelegateConditionally(CharSequence csq) throws IOException {
        // BEGIN: writer.conditionally
        boolean isOverriden = false;
        try {
            isOverriden = 
                (getClass().getMethod("write", String.class).getDeclaringClass() != Writer.class) ||
                (getClass().getMethod("write", Integer.TYPE).getDeclaringClass() != BufferedWriter.class) ||
                (getClass().getMethod("write", String.class, Integer.TYPE, Integer.TYPE).getDeclaringClass() != BufferedWriter.class);
        } catch (Exception ex) {
            throw new IOException(ex);
        }
        
        if (isOverriden) {
            if (csq == null) {
                write("null");
            } else {
                write(csq.toString());
            }
        } else {
            out.append(csq);
        }
        return this;
        // END: writer.conditionally
    }
    
    public enum Behaviour {
        THROW_EXCEPTION, DELEGATE_TO_SUPER, DELEGATE_TO_OUT, DELEGATE_CONDITIONALLY
    }
}

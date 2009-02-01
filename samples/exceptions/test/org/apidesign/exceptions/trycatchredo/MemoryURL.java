
package org.apidesign.exceptions.trycatchredo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;
import java.util.HashMap;
import java.util.Map;
import junit.framework.Assert;

/**
 *
 * @author Jaroslav Tulach
 */
public final class MemoryURL extends URLStreamHandler {
    private MemoryURL() {
    }

    static void initialize() {
    }
    static {
        class F implements URLStreamHandlerFactory {
            public URLStreamHandler createURLStreamHandler(String protocol) {
                if (protocol.startsWith("memory")) {
                    return new MemoryURL();
                }
                return null;
            }
        }
        F f = new F();
        URL.setURLStreamHandlerFactory(f);
    }
    
    private static Map<String,InputStream> contents = new HashMap<String,InputStream>();
    private static Map<String,MC> outputs = new HashMap<String,MC>();
    public static void registerURL(String u, String content, ByteArrayOutputStream out) throws MalformedURLException {
        contents.put(u, new ByteArrayInputStream(content.getBytes()));
        if (out != null) {
            new MC(new URL(u)).out = out;
        }
    }
    
    public static byte[] getOutputForURL(String u) {
        MC out = outputs.get(u);
        Assert.assertNotNull("No output for " + u, out);
        return out.out.toByteArray();
    }
    
    protected URLConnection openConnection(URL u) throws IOException {
        return new MC(u);
    }
    
    private static final class MC extends URLConnection {
        private InputStream values;
        private ByteArrayOutputStream out;
        
        public MC(URL u) {
            super(u);
            outputs.put(u.toExternalForm(), this);
        }

        public void connect() throws IOException {
            if (values != null) {
                return;
            }
            values = contents.remove(url.toExternalForm());
            if (values == null) {
                throw new IOException("No such content: " + url);
            }
        }

        @Override
        public InputStream getInputStream() throws IOException {
            connect();
            return values;
        }

        @Override
        public OutputStream getOutputStream() throws IOException {
            if (out == null) {
                out = new ByteArrayOutputStream();
            }
            return out;
        }
    }
}

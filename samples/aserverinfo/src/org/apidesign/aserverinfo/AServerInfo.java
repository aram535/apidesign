package org.apidesign.aserverinfo;

import java.net.URL;
import java.util.concurrent.Callable;
import org.openide.util.Lookup;

public final class AServerInfo {
    private final Callable<String> name;
    private final Callable<URL> url;
    private final Callable<Void> reset;
    private final Callable<Void> shutdown;

    private AServerInfo(Callable<String> name, Callable<URL> url, Callable<Void> reset, Callable<Void> s) {
        this.name = name;
        this.url = url;
        this.reset = reset;
        this.shutdown = s;
    }
    
    //
    // API for clients
    //
    
    public String getName() {
        return call(name, "noname");
    }
    
    public URL getURL() {
        return call(url, null);
    }
    
    public void reset() throws Exception {
        if (reset != null) {
            reset.call();
        }
    }
    
    public void shutdown() throws Exception {
        if (shutdown != null) {
            shutdown.call();
        }
    }

    //
    // factories
    //

    // BEGIN: aserverinfo.create
    public interface NameProvider {
        public String getName();
    }
    public interface URLProvider {
        public URL getURL();
    }
    public interface ResetHandler {
        public void reset();
    }
    
    public static AServerInfo create(final Lookup interfaces) 
    // END: aserverinfo.create
    {
        Callable<String> nameP = new Callable<String>() {
            public String call() throws Exception {
                NameProvider p = interfaces.lookup(NameProvider.class);
                return p == null ? "noname" : p.getName();
            }
        };
        Callable<URL> urlP = new Callable<URL>() {
            public URL call() throws Exception {
                URLProvider p = interfaces.lookup(URLProvider.class);
                return p == null ? null : p.getURL();
            }
        };
        Callable<Void> resetP = new Callable<Void>() {
            public Void call() throws Exception {
                ResetHandler h = interfaces.lookup(ResetHandler.class);
                if (h != null) {
                    h.reset();
                }
                return null;
            }
        };
        
        return new AServerInfo(nameP, urlP, resetP, null);
    }
    
    // BEGIN: aserverinfo.regularcreate
    public static AServerInfo create(NameProvider nameProvider, URLProvider urlProvider, ResetHandler reset)
    // END: aserverinfo.regularcreate
    {
        return create(nameProvider, urlProvider, reset, null);
    }
    
    // BEGIN: aserverinfo.regularcreate.withshutdown
    /** @since 2.0 */
    public interface ShutdownHandler {
        public void shutdown();
    }
    
    /** @since 2.0 */
    public static AServerInfo create(NameProvider nameProvider, URLProvider urlProvider, ResetHandler reset, ShutdownHandler shutdown)
    // END: aserverinfo.regularcreate.withshutdown
    {
        final NameProvider np = nameProvider;
        final URLProvider up = urlProvider;
        final ResetHandler h = reset;
        final ShutdownHandler s = shutdown;
        
        Callable<String> nameP = new Callable<String>() {
            public String call() throws Exception {
                return np == null ? "noname" : np.getName();
            }
        };
        Callable<URL> urlP = new Callable<URL>() {
            public URL call() throws Exception {
                return up == null ? null : up.getURL();
            }
        };
        Callable<Void> resetP = new Callable<Void>() {
            public Void call() throws Exception {
                if (h != null) {
                    h.reset();
                }
                return null;
            }
        };
        Callable<Void> shutP = new Callable<Void>() {
            public Void call() throws Exception {
                if (s != null) {
                    s.shutdown();
                }
                return null;
            }
        };
        
        return new AServerInfo(nameP, urlP, resetP, shutP);
    }

    //
    // cumulative factory methods
    //
    
    // BEGIN: aserverinfo.cumulative.factory
    public static AServerInfo empty() {
        return new AServerInfo(null, null, null, null);
    }
    
    public final AServerInfo nameProvider(final NameProvider np) {
        Callable<String> nameP = new Callable<String>() {
            public String call() throws Exception {
                return np == null ? "noname" : np.getName();
            }
        };
        return new AServerInfo(nameP, this.url, this.reset, this.shutdown);
    }
    // END: aserverinfo.cumulative.empty

    public final AServerInfo urlProvider(final URLProvider up) {
        Callable<URL> urlP = new Callable<URL>() {
            public URL call() throws Exception {
                return up == null ? null : up.getURL();
            }
        };
        return new AServerInfo(this.name, urlP, this.reset, this.shutdown);
    }
    public final AServerInfo reset(final ResetHandler h) {
        Callable<Void> resetP = new Callable<Void>() {
            public Void call() throws Exception {
                if (h != null) {
                    h.reset();
                }
                return null;
            }
        };
        return new AServerInfo(this.name, this.url, resetP, this.shutdown);
    }
    public final AServerInfo shutdown(final ShutdownHandler s) {
        Callable<Void> shutP = new Callable<Void>() {
            public Void call() throws Exception {
                if (s != null) {
                    s.shutdown();
                }
                return null;
            }
        };
        return new AServerInfo(this.name, this.url, this.reset, shutP);
    }
    
    //
    // Support implementations
    //
    
    private static <T> T call(Callable<T> name, T defValue) {
        try {
            return name.call();
        } catch (Exception ex) {
            return defValue;
        }
    }

    
}

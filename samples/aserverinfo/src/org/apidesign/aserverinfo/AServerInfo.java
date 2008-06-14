package org.apidesign.aserverinfo;

import java.net.URL;
import org.openide.util.Lookup;

public final class AServerInfo {
    private final NameProvider name;
    private final URLProvider url;
    private final ResetHandler reset;
    private final ShutdownHandler shutdown;

    private AServerInfo(NameProvider name, URLProvider url, ResetHandler reset, ShutdownHandler shutdown) {
        this.name = name;
        this.url = url;
        this.reset = reset;
        this.shutdown = shutdown;
    }

    
    //
    // API for clients
    //
    
    public String getName() {
        return name == null ? "noname" : name.getName();
    }
    
    public URL getURL() {
        return url == null ? null : url.getURL();
    }
    
    public void reset() {
        if (reset != null) {
            reset.reset();
        }
    }
    
    public void shutdown() {
        if (shutdown != null) {
            shutdown.shutdown();
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
        NameProvider nameP = new NameProvider() {
            public String getName() {
                NameProvider p = interfaces.lookup(NameProvider.class);
                return p == null ? "noname" : p.getName();
            }
        };
        URLProvider urlP = new URLProvider() {
            public URL getURL() {
                URLProvider p = interfaces.lookup(URLProvider.class);
                return p == null ? null : p.getURL();
            }
        };
        ResetHandler resetP = new ResetHandler() {
            public void reset() {
                ResetHandler h = interfaces.lookup(ResetHandler.class);
                if (h != null) {
                    h.reset();
                }
            }
        };
        
        return new AServerInfo(nameP, urlP, resetP, null);
    }
    
    // BEGIN: aserverinfo.regularcreate
    public static AServerInfo create(NameProvider nameProvider, URLProvider urlProvider, ResetHandler reset)
    // END: aserverinfo.regularcreate
    {
        return new AServerInfo(nameProvider, urlProvider, reset, null);
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
        return new AServerInfo(nameProvider, urlProvider, reset, shutdown);
    }

    //
    // cumulative factory methods
    //
    
    // BEGIN: aserverinfo.cumulative.factory
    public static AServerInfo empty() {
        return new AServerInfo(null, null, null, null);
    }
    
    public final AServerInfo nameProvider(final NameProvider np) {
        return new AServerInfo(np, this.url, this.reset, this.shutdown);
    }
    // END: aserverinfo.cumulative.factory

    public final AServerInfo urlProvider(final URLProvider up) {
        return new AServerInfo(this.name, up, this.reset, this.shutdown);
    }
    public final AServerInfo reset(final ResetHandler h) {
        return new AServerInfo(this.name, this.url, h, this.shutdown);
    }
    public final AServerInfo shutdown(final ShutdownHandler s) {
        return new AServerInfo(this.name, this.url, this.reset, s);
    }
}

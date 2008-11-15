package org.apidesign.aserverinfo.builder;

import org.apidesign.aserverinfo.spi.NameProvider;
import org.apidesign.aserverinfo.spi.ResetHandler;
import org.apidesign.aserverinfo.spi.ShutdownHandler;
import org.apidesign.aserverinfo.spi.URLProvider;

/**
 *
 * @author Jaroslav Tulach <jtulach@netbeans.org>
 */
public class ServerInfo {

    //
    // cumulative factory methods for the builder pattern
    //

    // BEGIN: aserverinfo.builder.factory
    public static ServerInfo empty() {
        return new ServerInfo(null, null, null, null);
    }

    public final ServerInfo nameProvider(NameProvider np) {
        return new ServerInfo(np, this.url, this.reset, this.shutdown);
    }

    public final ServerInfo urlProvider(URLProvider up) {
        return new ServerInfo(this.name, up, this.reset, this.shutdown);
    }
    public final ServerInfo reset(ResetHandler h) {
        return new ServerInfo(this.name, this.url, h, this.shutdown);
    }
    /** All one needs to do when there is a need to add new
     * style of creation is to add new method for a builder.
     * @param handler
     * @return
     * @since 2.0
     */
    public final ServerInfo shutdown(ShutdownHandler handler) {
        return new ServerInfo(this.name, this.url, this.reset, handler);
    }
    
    /** Creates the server connector based on current values in the 
     * info. Builder factory method.
     * @return server connector
     */
    public final ServerConnector connect() {
        return new ServerConnector(name, url, reset, shutdown);
    }
    // END: aserverinfo.builder.factory

    private final NameProvider name;
    private final URLProvider url;
    private final ResetHandler reset;
    private final ShutdownHandler shutdown;

    private ServerInfo(
        NameProvider name, URLProvider url,
        ResetHandler reset, ShutdownHandler shutdown
    ) {
        this.name = name;
        this.url = url;
        this.reset = reset;
        this.shutdown = shutdown;
    }

}

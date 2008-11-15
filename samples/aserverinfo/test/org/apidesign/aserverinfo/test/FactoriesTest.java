package org.apidesign.aserverinfo.test;

import java.net.MalformedURLException;
import java.net.URL;
import org.apidesign.aserverinfo.factories.ServerConnector;
import org.apidesign.aserverinfo.spi.NameProvider;
import org.apidesign.aserverinfo.spi.ResetHandler;
import org.apidesign.aserverinfo.spi.ShutdownHandler;
import org.apidesign.aserverinfo.spi.URLProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openide.util.Exceptions;

public class FactoriesTest {

    public FactoriesTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void showUseOfFactoryVersion10() throws Exception {
        Prov p = new Prov();
        NameProvider np = p;
        URLProvider up = p;
        ResetHandler res = p;
        ServerConnector inf;
        
        // BEGIN: ServerConnector.factory.creation
        inf = ServerConnector.create(np, up, res);
        // END: ServerConnector.factory.creation
        
        assertEquals("API Design Server", inf.getName());
        assertEquals("http://www.apidesign.org", inf.getURL().toExternalForm());
        inf.reset();
        assertEquals("Once reset", 1, p.resets);
        
    }
    @Test
    public void showUseOfFactoryVersion20() throws Exception {
        Prov p = new Prov();
        NameProvider np = p;
        URLProvider up = p;
        ResetHandler res = p;
        ShutdownHandler shutdown = new ShutdownHandler() {
            public void shutdown() {
                // OK
            }
        };
        ServerConnector inf;

        // BEGIN: ServerConnector.factory.creation
        inf = ServerConnector.create(np, up, res, shutdown);
        // END: ServerConnector.factory.creation

        assertEquals("API Design Server", inf.getName());
        assertEquals("http://www.apidesign.org", inf.getURL().toExternalForm());
        inf.reset();
        assertEquals("Once reset", 1, p.resets);

    }
    
    private static class Prov implements NameProvider, URLProvider, ResetHandler {
        int resets;

        public String getName() {
            return "API Design Server";
        }

        public URL getURL() {
            try {
                return new URL("http://www.apidesign.org");
            } catch (MalformedURLException ex) {
                Exceptions.printStackTrace(ex);
                return null;
            }
        }

        public void reset() {
            resets++;
        }

    }
        
}
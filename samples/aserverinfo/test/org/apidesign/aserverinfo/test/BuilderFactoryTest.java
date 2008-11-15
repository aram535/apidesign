package org.apidesign.aserverinfo.test;

import java.net.MalformedURLException;
import java.net.URL;
import org.apidesign.aserverinfo.builder.ServerConnector;
import org.apidesign.aserverinfo.builder.ServerInfo;
import org.apidesign.aserverinfo.spi.NameProvider;
import org.apidesign.aserverinfo.spi.ResetHandler;
import org.apidesign.aserverinfo.spi.URLProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openide.util.Exceptions;

public class BuilderFactoryTest {

    public BuilderFactoryTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void showUseOfCumulativeFactory() throws Exception {
        Prov p = new Prov();
        NameProvider np = p;
        URLProvider up = p;
        ResetHandler res = p;
        ServerConnector inf;
        
        // BEGIN: ServerConnector.cumulative.creation
        inf = ServerInfo.empty()
                .nameProvider(np).urlProvider(up).reset(res).connect();
        // END: ServerConnector.cumulative.creation
        
        assertEquals("API Design Server", inf.getName());
        assertEquals("http://www.apidesign.org", inf.getURL().toExternalForm());
        inf.reset();
        assertEquals("Once reset", 1, p.resets);
        
    }
    
    @Test
    public void showVerboseUseOfCumulativeFactory() throws Exception {
        Prov prov = new Prov();
        ServerConnector info;
        
        // BEGIN: ServerConnector.builder.creation.verbose
        ServerInfo empty = ServerInfo.empty();
        ServerInfo name = empty.nameProvider(prov);
        ServerInfo urlAndName = name.urlProvider(prov);
        ServerInfo all = urlAndName.reset(prov);
        info = all.connect();
        // END: ServerConnector.builder.creation.verbose
        
        assertEquals("API Design Server", info.getName());
        assertEquals("http://www.apidesign.org", info.getURL().toExternalForm());
        info.reset();
        assertEquals("Once reset", 1, prov.resets);
        
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
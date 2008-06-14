package org.apidesign.aserverinfo;

import java.net.MalformedURLException;
import java.net.URL;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openide.util.Exceptions;

public class AServerInfoTest {

    public AServerInfoTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void showUseOfCumulativeFactory() throws Exception {
        Prov prov = new Prov();
        AServerInfo info;
        
        // BEGIN: aserverinfo.cumulative.creation
        info = AServerInfo.empty().nameProvider(prov).urlProvider(prov).reset(prov);
        // END: aserverinfo.cumulative.creation
        
        assertEquals("API Design Server", info.getName());
        assertEquals("http://www.apidesign.org", info.getURL().toExternalForm());
        info.reset();
        assertEquals("Once reset", 1, prov.resets);
        
    }
    
    @Test
    public void showVerboseUseOfCumulativeFactory() throws Exception {
        Prov prov = new Prov();
        AServerInfo info;
        
        // BEGIN: aserverinfo.cumulative.creation.verbose
        AServerInfo empty = AServerInfo.empty();
        AServerInfo name = empty.nameProvider(prov);
        AServerInfo urlAndName = name.urlProvider(prov);
        info = urlAndName.reset(prov);
        // END: aserverinfo.cumulative.creation.verbose
        
        assertEquals("API Design Server", info.getName());
        assertEquals("http://www.apidesign.org", info.getURL().toExternalForm());
        info.reset();
        assertEquals("Once reset", 1, prov.resets);
        
    }
    
    
    private static class Prov implements AServerInfo.NameProvider, AServerInfo.URLProvider, AServerInfo.ResetHandler {
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
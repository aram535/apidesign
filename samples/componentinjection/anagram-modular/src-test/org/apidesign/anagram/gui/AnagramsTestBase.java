package org.apidesign.anagram.gui;

import org.apidesign.anagram.api.Scrambler;
import org.apidesign.anagram.api.WordLibrary;
import static org.junit.Assert.*;
import org.junit.Test;
import org.netbeans.junit.MockServices;

public abstract class AnagramsTestBase {

    protected abstract Anagrams create();
    
    @Test public void testInjectionOfServices() throws Exception {
        Anagrams ui = create();
        
        assertNull("No scrambler injected yet", ui.getScrambler());
        assertNull("No scrambler injected yet", ui.getWordLibrary());
        
        MockServices.setServices(
            ReversingMockScrambler.class, SingleMockLibrary.class
        );
        
        Scrambler s = ui.getScrambler();
        assertNotNull("Now we have scrambler", s);
        assertEquals(
            "It is the mock one", ReversingMockScrambler.class, s.getClass()
        );
        WordLibrary l = ui.getWordLibrary();
        assertNotNull("Now we have library", l);
        assertEquals(
            "It is the mock one", SingleMockLibrary.class, l.getClass()
        );
    }
    
    public static final class ReversingMockScrambler {
        
    }
    
    public static final class SingleMockLibrary {
        
    }
}

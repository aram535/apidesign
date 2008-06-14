package org.apidesign.anagram.app.lookup;

import org.apidesign.anagram.gui.*;
import org.apidesign.anagram.app.lookup.AnagramsWithLookup;

public class AnagramsWithLookupTest extends AnagramsTestBase {
    @Override
    protected Anagrams create() {
        return new AnagramsWithLookup();
    }
}

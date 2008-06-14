package org.apidesign.anagram.app.lookup;

import org.apidesign.anagram.api.Scrambler;
import org.apidesign.anagram.api.WordLibrary;
import org.apidesign.anagram.gui.Anagrams;
import org.openide.util.Lookup;

class AnagramsWithLookup extends Anagrams {

    public AnagramsWithLookup() {
    }

    @Override
    protected WordLibrary getWordLibrary() {
        return Lookup.getDefault().lookup(WordLibrary.class);
    }

    @Override
    protected Scrambler getScrambler() {
        return Lookup.getDefault().lookup(Scrambler.class);
    }

}

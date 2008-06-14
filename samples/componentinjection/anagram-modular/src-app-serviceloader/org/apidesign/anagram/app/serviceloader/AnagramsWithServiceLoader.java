package org.apidesign.anagram.app.serviceloader;

import org.apidesign.anagram.api.Scrambler;
import org.apidesign.anagram.api.WordLibrary;
import org.apidesign.anagram.gui.Anagrams;

// BEGIN: anagram.serviceloader.Anagrams
class AnagramsWithServiceLoader extends Anagrams {

    public AnagramsWithServiceLoader() {
    }

    @Override
    protected WordLibrary getWordLibrary() {
        return java.util.ServiceLoader.load(WordLibrary.class).iterator().next();
    }

    @Override
    protected Scrambler getScrambler() {
        return java.util.ServiceLoader.load(Scrambler.class).iterator().next();
    }

}
// END: anagram.serviceloader.Anagrams

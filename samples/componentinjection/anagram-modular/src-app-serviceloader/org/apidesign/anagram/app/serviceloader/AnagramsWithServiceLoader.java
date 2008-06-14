package org.apidesign.anagram.app.serviceloader;

import org.apidesign.anagram.api.Scrambler;
import org.apidesign.anagram.api.WordLibrary;
import org.apidesign.anagram.gui.Anagrams;
import java.util.ServiceLoader;

// BEGIN: anagram.serviceloader.Anagrams
class AnagramsWithServiceLoader extends Anagrams {

    public AnagramsWithServiceLoader() {
    }

    @Override
    protected WordLibrary getWordLibrary() {
        return ServiceLoader.load(WordLibrary.class).iterator().next();
    }

    @Override
    protected Scrambler getScrambler() {
        return ServiceLoader.load(Scrambler.class).iterator().next();
    }

}
// END: anagram.serviceloader.Anagrams

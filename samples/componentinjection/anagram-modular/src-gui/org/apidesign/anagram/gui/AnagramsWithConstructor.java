package org.apidesign.anagram.gui;

import org.apidesign.anagram.api.Scrambler;
import org.apidesign.anagram.api.WordLibrary;

public final class AnagramsWithConstructor extends Anagrams {
    
    private final WordLibrary library;
    private final Scrambler scrambler;

    public AnagramsWithConstructor(WordLibrary library, Scrambler scrambler) {
        this.library = library;
        this.scrambler = scrambler;
    }

    @Override
    protected WordLibrary getWordLibrary() {
        return library;
    }

    @Override
    protected Scrambler getScrambler() {
        return scrambler;
    }

    public void display() {
        setVisible(true);
    }
}

package org.apidesign.anagram.app.lookup;

import org.apidesign.anagram.api.Scrambler;
import org.apidesign.anagram.api.UI;
import org.apidesign.anagram.api.WordLibrary;
import org.apidesign.anagram.gui.Anagrams;

public final class Main {
    private Main() { }
    
    public static void main(String[] args) throws Exception {
        UI ui = new AnagramsWithLookup();
        ui.display();
    }
}

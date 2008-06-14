package org.apidesign.anagram.app.register;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apidesign.anagram.api.Scrambler;
import org.apidesign.anagram.api.WordLibrary;
import org.apidesign.anagram.gui.Anagrams;

public final class AnagramsWithProperties extends Anagrams {
    private WordLibrary wordLibrary;
    private Scrambler scrambler;
    
    public AnagramsWithProperties() {
    }
    
    private static <T> Class<? extends T> loadImpl(Class<T> clazz) throws ClassNotFoundException {
        String implName = System.getProperty(clazz.getName());
        assert implName != null;
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        if (loader == null) {
            loader = AnagramsWithProperties.class.getClassLoader();
        }
        Class<?> impl = Class.forName(implName, false, loader);
        return impl.asSubclass(clazz);
    }
    

    @Override
    protected WordLibrary getWordLibrary() {
        try {
            if (wordLibrary == null) {
                wordLibrary = loadImpl(WordLibrary.class).newInstance();
            }
            return wordLibrary;
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    @Override
    protected Scrambler getScrambler() {
        try {
            if (scrambler == null) {
                scrambler = loadImpl(Scrambler.class).newInstance();
            }
            return scrambler;
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }
}

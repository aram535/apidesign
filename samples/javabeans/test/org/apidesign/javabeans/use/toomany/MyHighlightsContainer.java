package org.apidesign.javabeans.use.toomany;

import org.apidesign.javabeans.toomany.*;
import java.util.TooManyListenersException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

// BEGIN: javabeans.with.MyHighlightsContainer
final class MyHighlightsContainer implements HighlightsContainer {
    private HighlightsChangeListener listener;

    public synchronized void addHighlightsChangeListener(
        HighlightsChangeListener l
    ) throws TooManyListenersException {
        if (listener != null) throw new TooManyListenersException();
        listener = l;
    }
    public synchronized void removeHighlightsChangeListener(
        HighlightsChangeListener l
    ) {
        if (listener == l) listener = null;
    }
    public HighlightsSequence getHighlights(int startOffset, int endOffset) {
        return null; // implement
    }
// END: javabeans.with.MyHighlightsContainer
}
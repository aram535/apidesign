package org.apidesign.javabeans.with;

import java.util.EventObject;

public class HighlightsChangeEvent extends EventObject {
    public HighlightsChangeEvent(HighlightsContainer source, int changeStartOffset, int changeEndOffset) {
        super(source);
    }
}

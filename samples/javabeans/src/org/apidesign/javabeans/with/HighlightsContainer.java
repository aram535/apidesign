package org.apidesign.javabeans.with;

// BEGIN: javabeans.with.HighlightsContainer
public interface HighlightsContainer {        
  public void addHighlightsChangeListener(HighlightsChangeListener listener);
  public HighlightsSequence getHighlights(int startOffset, int endOffset);
  public void removeHighlightsChangeListener(HighlightsChangeListener listener);
}
// END: javabeans.with.HighlightsContainer

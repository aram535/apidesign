 package org.apidesign.wildimport;

// BEGIN: theory.wildimport
import java.awt.*;
import java.util.*;

/** Could be compiled on JDK 1.1, before java.util.List was created */
public class MyPanel extends Panel {
    List visualComponent = new List();
    Vector items = new Vector();
}        
// END: theory.wildimport

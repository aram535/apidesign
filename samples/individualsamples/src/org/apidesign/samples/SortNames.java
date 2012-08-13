package org.apidesign.samples;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortNames {
    public static void main(String... args) {
        // BEGIN: petr.tom.sort
        List<String> names = new ArrayList<String>();
        names.add("Tom");
        names.add("Petr");
        Collections.sort(names);
        // END: petr.tom.sort
        
        System.err.println("Names: " + names);
    }
}

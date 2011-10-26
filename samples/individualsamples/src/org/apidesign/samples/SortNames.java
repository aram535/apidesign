package org.apidesign.samples;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortNames {
    public static void main(String... args) {
        // BEGIN: petr.tom.sort
        List<String> arr = new ArrayList<String>();
        arr.add("Tom");
        arr.add("Petr");
        Collections.sort(arr);
        // END: petr.tom.sort
        
        System.err.println("Names: " + arr);
    }
}

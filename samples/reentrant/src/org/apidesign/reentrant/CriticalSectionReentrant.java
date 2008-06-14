package org.apidesign.reentrant;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

public class CriticalSectionReentrant<T extends Comparable<T>> implements CriticalSection<T> {
    private T pilot;
    private AtomicInteger cnt = new AtomicInteger();
    
    public void assignPilot(T pilot) {
        this.pilot = pilot;
    }

    public int sumBigger(Collection<T> args) {
        T pilotCopy = this.pilot;
        int own = 0;
        for (T cmp : args) {
            if (pilotCopy.compareTo(cmp) < 0) {
                own++;
            }
        }
        cnt.addAndGet(own);
        return own;
        
        
    }

    public int getCount() {
        return cnt.get();
    }
}

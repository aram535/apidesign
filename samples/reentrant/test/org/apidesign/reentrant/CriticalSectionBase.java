package org.apidesign.reentrant;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public abstract class CriticalSectionBase {
    protected abstract CriticalSection<Integer> create();

    @Test
    public void testCriticalSectionWith15() {
        final CriticalSection<Integer> cs = create();
        testFor15(cs);
    }
    
    final void testFor15(CriticalSection<Integer> cs) {
        cs.assignPilot(15);

        List<Integer> ints = new ArrayList<Integer>();
        ints.add(8);
        ints.add(11);
        ints.add(10);
        ints.add(5);
        ints.add(12);
        ints.add(18);
        ints.add(13);
        ints.add(7);

        int cnt = cs.sumBigger(ints);

        assertEquals("18 is bigger than 15", 1, cnt);
        
    }
    
    @Test 
    public void teaseCriticalSectionWithReentrantCalls() {
        final CriticalSection<Integer> cs = create();
        
        cs.assignPilot(10);
        
        class ChangePilotTo15 implements Runnable {
            public void run() {
                testFor15(cs);
            }
        }
        
        List<Integer> ints = new MyCollection(new ChangePilotTo15(), 3);
        ints.add(8);
        ints.add(11);
        ints.add(10);
        ints.add(5);
        ints.add(12);
        ints.add(18);
        ints.add(13);
        ints.add(7);
        
        int cnt = cs.sumBigger(ints);
        
        assertEquals("11, 12, 18, 13 are bigger than 10", 4, cnt);
        
        assertEquals("Global count is sum of ints(e.g. 4) plus result of testFor15(e.g. 1)", 5, cs.getCount());
    }

    class MyCollection extends ArrayList<Integer> {
        private final Runnable callback;
        private final int callbackBeforeIndex;

        public MyCollection(Runnable callback, int callbackAtIndex) {
            this.callback = callback;
            this.callbackBeforeIndex = callbackAtIndex;
        }

        @Override
        public Iterator<Integer> iterator() {
            final Iterator<Integer> delegate = super.iterator();
            class It implements Iterator<Integer> {
                private int index;
                
                public boolean hasNext() {
                    return delegate.hasNext();
                }

                public Integer next() {
                    if (index++ == callbackBeforeIndex) {
                        callback.run();
                    }
                    
                    return delegate.next();
                }

                public void remove() {
                    delegate.remove();
                }
            } // end of It
            
            return new It();
        }
    }
}
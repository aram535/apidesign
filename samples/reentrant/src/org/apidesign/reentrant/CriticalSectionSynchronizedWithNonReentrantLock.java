package org.apidesign.reentrant;

import java.nio.channels.NonReadableChannelException;
import java.util.Collection;
import java.util.concurrent.locks.Lock;

public class CriticalSectionSynchronizedWithNonReentrantLock<T extends Comparable<T>> implements CriticalSection<T> {
    private T pilot;
    private int cnt;
    private Lock lock = new NonReentrantLock();
    
    public void assignPilot(T pilot) {
        lock.lock();
        try {
            this.pilot = pilot;
        } finally {
            lock.unlock();
        }
    }

    public int sumBigger(Collection<T> args) {
        lock.lock();
        try {
            for (T cmp : args) {
                if (pilot.compareTo(cmp) < 0) {
                    cnt++;
                }
            }
            return cnt;
        } finally {
            lock.unlock();
        }
    }
    
    public int getCount() {
        lock.lock();
        try {
            return cnt;
        } finally {
            lock.unlock();
        }
    }
}

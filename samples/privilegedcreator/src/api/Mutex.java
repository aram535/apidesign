/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package api;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Jaroslav Tulach <jaroslav.tulach@netbeans.org>
 */
public class Mutex {
    Lock lock = new ReentrantLock();
    
    public Mutex() {
    }

    
    public void readAccess(Runnable r) {
        try {
            lock.lock();
            r.run();
        } finally {
            lock.unlock();
        }
    }
    

    
    public Mutex(Privileged privileged) {
        if (privileged.mutex != null) {
            throw new IllegalStateException();
        }
        privileged.mutex = this;
    }
    
    public static final class Privileged {
        private Mutex mutex;
        
        public void enterReadAccess() {
            mutex.lock.lock();
        }
        
        public void exitReadAccess() {
            mutex.lock.unlock();
        }
    }
}


package api;

import java.util.concurrent.Executor;

/**
 *
 * @author Jaroslav Tulach <jtulach@netbeans.org>
 */
public final class Locks {
    /** let's prefer factory methods */
    private Locks() {
    }
    
    
    public static Executor create() {
        return new Simple();
    }
    
    public static Executor create(boolean fair) {
        return new Fair(fair);
    }

    // BEGIN: design.less.privileged
    public static Executor create(Configuration config) {
        return new Fair(config.fair);
    }
    
    public static final class Configuration {
        boolean fair;
        int maxWaiters = -1;
        
        public void setFair(boolean fair) {
            this.fair = fair;
        }
        public void setMaxWaiters(int max) {
            this.maxWaiters = max;
        }
    }
    // END: design.less.privileged
    
    private static final class Simple implements Executor {
        public synchronized void execute(Runnable command) {
            command.run();
        }
    }
    private static final class Fair implements Executor {
        private boolean fair;
        
        public Fair(boolean fair) {
            this.fair = fair;
        }
        
        public void execute(Runnable command) {
            // TBD
        }
    }
}

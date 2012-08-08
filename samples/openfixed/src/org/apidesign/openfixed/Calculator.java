package org.apidesign.openfixed;

/** Sample bean using the {@link ModificationListener}
 * to <b>add</b> numbers.
 *
 * @author Jaroslav Tulach <jtulach@netbeans.org>
 */
// BEGIN: openfixed.bean
public final class Calculator {
    private final EventSupport listeners;
    private int sum;

    private Calculator(EventSupport listeners) {
        this.listeners = listeners;
    }

    /** An abstraction over various types of event delivery
     * to listeners. Comes with four different implementations.
     * A trivial one, asynchronous one, one with support for
     * pending events and one for a batch events delivery.
     */
    interface EventSupport {
        public void fireModificationEvent(ModificationEvent ev);
        public void add(ModificationListener l);
        public void remove(ModificationListener l);
    }
    
    public static Calculator create() {
        return new Calculator(new TrivialEventSupport());
    }

    public static Calculator createAsynch() {
        return new Calculator(new AsyncEventSupport());
    }

    /** @since 2.0 */
    public static Calculator createPending() {
        return new Calculator(new PendingEventSupport());
    }

    /** @since 3.0 */
    public static Calculator createBatch() {
        return new Calculator(new PostEventSupport());
    }

    public synchronized void add(int add) {
        sum += add;
        listeners.fireModificationEvent(new ModificationEvent(this, add));
    }
    
    public synchronized int getSum() {
        return sum;
    }
    
    public void addModificationListener(ModificationListener l) {
        listeners.add(l);
    }
    public void removeModificationListener(ModificationListener l) {
        listeners.remove(l);
    }
}
// END: openfixed.bean

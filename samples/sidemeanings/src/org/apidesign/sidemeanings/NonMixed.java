package org.apidesign.sidemeanings;

// BEGIN: sidemeanings.Mixed.Clean
public final class NonMixed {
    private int counter;
    private int sum;
    private final Provider impl;
    
    private NonMixed(Provider impl) {
        this.impl = impl;
    }
    public static NonMixed create(Provider impl) {
        NonMixed api = new NonMixed(impl);
        Callback callback = new Callback(api);
        impl.initialize(callback);
        return api;
    }

    public final int apiForClients() {
        int subclass = impl.toBeImplementedBySubclass();
        sum += subclass;
        return sum / counter;
    }

    public interface Provider {
        public void initialize(Callback c);
        public int toBeImplementedBySubclass();
    }

    public static final class Callback {
        NonMixed api;
        
        Callback(NonMixed api) {
            this.api = api;
        }
        public final void toBeCalledBySubclass() {
            api.counter++;
        }
    }
}
// END: sidemeanings.Mixed.Clean



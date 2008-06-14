package org.apidesign.sidemeanings;

public final class Mixed {
    private Mixed() {}
    
    
    // BEGIN: sidemeanings.Mixed.Dirty
    public static abstract class MixedClass {
      protected MixedClass() { }

      public final void apiForClients() { /*...*/ }
      protected abstract void toBeImplementedBySubclass();
      protected final void toBeCalledBySubclass() { /* ... */ }
    }
    // END: sidemeanings.Mixed.Dirty
    
    // BEGIN: sidemeanings.Mixed.Clean
    public static final class NonMixed {
        private NonMixed() {}
        public static NonMixed create(NonMixedImpl impl) {
            NonMixed api = new NonMixed();
            Callback callback = new Callback(api);
            impl.initialize(callback);
            return api;
        }

        public final void apiForClients() { /*...*/ }
    }
    
    public static interface NonMixedImpl {
        public void initialize(Callback c);
        public void toBeImplementedBySubclass();
    }

    public static final class Callback {
        Callback(NonMixed nomMixed) {
        }
        public final void toBeCalledBySubclass() {
            /*...*/
        }
    }
    // END: sidemeanings.Mixed.Clean
    
    
}



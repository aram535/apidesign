package org.apidesign.sidemeanings;

public final class Public {
    private Public() {}

    
    public abstract class Dirty {
        // BEGIN: sidemeanings.Public.Dirty
        public void method() {
            someCode();
        }
        // END: sidemeanings.Public.Dirty
        private void someCode() {
        }
    }
    
    
    public abstract class Clean {
        // BEGIN: sidemeanings.Public.Clean
        public final void method() {
            methodImpl();
        }
        protected abstract void methodImpl();
        protected final void someCode() {
        }
        // END: sidemeanings.Public.Clean
    }
}

package org.apidesign.sidemeanings;

public final class Protected {
    private Protected() {}

    
    public class Dirty {
        // BEGIN: sidemeanings.Protected.Dirty
        protected void method() {
            someCode();
        }
        // END: sidemeanings.Protected.Dirty
        private void someCode() {
        }
    }
    
    
    public abstract class Clean {
        // BEGIN: sidemeanings.Protected.Clean
        protected abstract void method();
        protected final void someCode() {
        }
        // END: sidemeanings.Protected.Clean
    }
}

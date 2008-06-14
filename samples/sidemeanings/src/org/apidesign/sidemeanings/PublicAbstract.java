package org.apidesign.sidemeanings;

public final class PublicAbstract {
    private PublicAbstract() {}

    
    public abstract class Dirty {
        // BEGIN: sidemeanings.PublicAbstract.Dirty
        public abstract void method();
        // END: sidemeanings.PublicAbstract.Dirty
    }
    
    
    public abstract class Clean {
        // BEGIN: sidemeanings.PublicAbstract.Clean
        public final void method() {
            methodImpl();
        }
        protected abstract void methodImpl();
        // END: sidemeanings.PublicAbstract.Clean
    }
}

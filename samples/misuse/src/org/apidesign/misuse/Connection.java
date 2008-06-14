package org.apidesign.misuse;

// BEGIN: misuse.Connection
public interface Connection {
    Savepoint setSavepoint();
    
    public interface Savepoint {
        void rollback();
        // and other useful operations
    }
}
// END: misuse.Connection

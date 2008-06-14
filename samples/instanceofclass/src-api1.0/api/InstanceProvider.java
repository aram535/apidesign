
package api;

// BEGIN: instanceof.class.InstanceProvider1
public final class InstanceProvider {
    private final Object instance;

    public InstanceProvider(Object instance) {
        this.instance = instance;
    }
    
    public Class<?> instanceClass() {
        return instance.getClass();
    }
    public Object instanceCreate() {
        return instance;
    }
}
// END: instanceof.class.InstanceProvider1

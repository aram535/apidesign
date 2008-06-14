
package api;

// BEGIN: instanceof.class.InstanceProvider2
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
    
    /** @since 2.0 */
    public boolean isInstanceOf(Class<?> c) {
        if (Helper.knownHowToDoItBetter()) {
            return Helper.computeTheResultOfIsInstanceOfInSomeBetterWay(c);
        } else {
            // fallback
            return c.isAssignableFrom(instanceClass());
        }
    }

    
}
// END: instanceof.class.InstanceProvider2

class Helper {
    static boolean computeTheResultOfIsInstanceOfInSomeBetterWay(Class<?> c) {
        return false;
    }
    static boolean knownHowToDoItBetter() {
        return false;
    }
}
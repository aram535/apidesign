
package api;

// BEGIN: instanceof.InstanceProvider
public interface InstanceProvider {
    public Class<?> instanceClass();
    public Object instanceCreate();
}
// END: instanceof.InstanceProvider

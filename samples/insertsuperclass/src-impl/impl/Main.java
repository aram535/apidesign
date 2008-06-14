
package impl;

import api.HelloClass;
import api.HelloInterface;

/**
 *
 * @author Jaroslav Tulach <jtulach@netbeans.org>
 */
public class Main {
    public static void main(String[] args) throws Exception {
        HelloClass clazz = new ImplClass();
        assert "Hello Unknown!".equals(clazz.sayHello());
        assert "Hello Jaroslav!".equals(clazz.sayHelloTo("Jaroslav"));

        HelloInterface iface = new ImplInterface();
        assert "Hello Unknown!".equals(iface.sayHello());
        assert "Hello Jaroslav!".equals(iface.sayHelloTo("Jaroslav"));
        
        System.err.println("Who defines sayHello(): " + clazz.getClass().getSuperclass().getMethod("sayHello"));
        System.err.println("Who defines sayHello(): " + iface.getClass().getInterfaces()[0].getMethod("sayHello"));
    }
}

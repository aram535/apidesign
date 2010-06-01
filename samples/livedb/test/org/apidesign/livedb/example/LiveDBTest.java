/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.apidesign.livedb.example;

import junit.framework.TestCase;

/**
 *
 * @author Jaroslav Tulach <jtulach@netbeans.org>
 */
public class LiveDBTest extends TestCase {
    
    public LiveDBTest(String testName) {
        super(testName);
    }

    public void testSomeMethod() {
        DBAccess db = new DBAccess();
        db.jarda = "Ahoj";
        assertEquals("Ahoj", db.jarda);
    }

}

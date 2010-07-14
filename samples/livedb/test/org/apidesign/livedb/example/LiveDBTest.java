/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.apidesign.livedb.example;

import java.sql.SQLException;
import java.util.List;
import junit.framework.TestCase;

/**
 *
 * @author Jaroslav Tulach <jtulach@netbeans.org>
 */
public class LiveDBTest extends TestCase {
    
    public LiveDBTest(String testName) {
        super(testName);
    }

    public void testSomeMethod() throws SQLException {
        List<Age> ages = Age.query();
        for (Age age : ages) {
            System.out.printf("%s is %s years old\n", age.NAME, age.AGE);
        }
    }

}

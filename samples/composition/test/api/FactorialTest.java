/*
 * Žluťoučký kůň je naše hříbátko.
 * and open the template in the editor.
 */

package api;

import junit.framework.TestCase;

/**
 *
 * @author Jaroslav Tulach <jtulach@netbeans.org>
 */
public class FactorialTest extends TestCase {
    
    public FactorialTest(String testName) {
        super(testName);
    }            

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testFactorial3() {
        assertEquals(6, Factorial.factorial(3));
    }
    
    public void testFactorial5() {
        assertEquals(120, Factorial.factorial(5));
    }

    /** Class showing inventive, non-expected use of 
     * Arithmetica methods to do multiplication instead of
     * addition.
     */
    private static class Factorial extends Arithmetica {
        public static int factorial(int n) {
            return new Factorial().sumRange(1, n);
        }
        @Override
        public int sumTwo(int one, int second) {
            return one * second;
        }
    }

    
}
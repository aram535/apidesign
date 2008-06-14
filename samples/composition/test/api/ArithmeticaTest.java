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
public class ArithmeticaTest extends TestCase {
    
    public ArithmeticaTest(String testName) {
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

    public void testSumTwo() {
        Arithmetica instance = new Arithmetica();
        assertEquals("+", 5, instance.sumTwo(3, 2));
    }

    public void testSumAll() {
        Arithmetica instance = new Arithmetica();
        assertEquals("+", 6, instance.sumAll(3, 2, 1));
    }

    public void testSumRange() {
        Arithmetica instance = new Arithmetica();
        assertEquals("+", 6, instance.sumRange(1, 3));
        assertEquals("10", 55, instance.sumRange(1, 10));
    }

}

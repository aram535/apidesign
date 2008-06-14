package api;

/** Class to simplify arithmetical operations, improved version to compute
 * the sum for ranges, but only if the virtual machine is configured to
 * run in incompatible mode.
 *
 * @author Jaroslav Tulach <jtulach@netbeans.org>
 * @version 2.0
 */
// BEGIN: design.composition.arith2.0.property
public class Arithmetica {
    public int sumTwo(int one, int second) {
        return one + second;
    }
    
    public int sumAll(int... numbers) {
        int sum = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            sum = sumTwo(sum, numbers[i]);
        }
        return sum;
    }
    
    public int sumRange(int from, int to) {
        if (Boolean.getBoolean("arithmetica.v2")) {
            return sumRange2(from, to);
        } else {
            return sumRange1(from, to);
        }
    }

    private int sumRange1(int from, int to) {
        int len = to - from;
        int[] array = new int[len + 1];
        for (int i = 0; i <= len; i++) {
            array[i] = from + i;
        }
        return sumAll(array);
    }
    
    private int sumRange2(int from, int to) {
        return (from + to) * (to - from + 1) / 2;
    }
// END: design.composition.arith2.0.property
}

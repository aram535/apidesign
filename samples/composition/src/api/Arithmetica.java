package api;

/** Class to simplify arithmetical operations.
 *
 * @author Jaroslav Tulach <jtulach@netbeans.org>
 */
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
        int len = to - from;
        int[] array = new int[len + 1];
        for (int i = 0; i <= len; i++) {
            array[i] = from + i;
        }
        return sumAll(array);
    }
}
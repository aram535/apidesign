package api;

/** @since 2.0 */
public final class StringBuilder {
    private char[] current;
            
    public StringBuilder() {
        current = new char[0];
    }
    
    public StringBuilder append(String s) {
        char[] arr = new char[current.length + s.chars.length];
        System.arraycopy(current, 0, arr, 0, current.length);
        System.arraycopy(s.chars, 0, arr, current.length, s.chars.length);
        return this;
    }
    
    public String getString() {
        return new String(current);
    }
}

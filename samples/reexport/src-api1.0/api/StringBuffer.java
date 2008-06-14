package api;

public final class StringBuffer {
    private char[] current;
            
    public StringBuffer() {
        current = new char[0];
    }
    
    public synchronized StringBuffer append(String s) {
        char[] arr = new char[current.length + s.chars.length];
        System.arraycopy(current, 0, arr, 0, current.length);
        System.arraycopy(s.chars, 0, arr, current.length, s.chars.length);
        return this;
    }
    
    public synchronized String getString() {
        return new String(current);
    }
}
